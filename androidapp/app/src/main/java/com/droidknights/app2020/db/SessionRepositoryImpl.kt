package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.firebase.firestore.*
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val prePackagedDb: PrePackagedDb,
    private val gson: Gson
) : SessionRepository {
    private val TAG = this::class.java.simpleName

    override suspend fun get(
        isCacheFirstLoad: Boolean
    ): Flow<List<Session>> = flow {
        val snapshot = db.collection("Session").let {
            if (isCacheFirstLoad) {
                it.cacheFirstGet()
            } else {
                it.get(Source.SERVER).await()
            }
        }
        Timber.d("Loaded ${if (snapshot.metadata.isFromCache) "Cache" else "Server"} ")
        emit(snapshot.map {
            val json = gson.toJsonTree(it.data)
            gson.fromJson(json, Session::class.java)
        })
    }.catch {
        Timber.e(it)
        emit(prePackagedDb.getSessionList())
    }.map {
        it.toSortedSessions()
    }

    override suspend fun getById(id: String): Flow<Session> {
        val collectionRefFlow = flow {
            emit(db.collection("Session"))
        }
        val snapshotFlow = collectionRefFlow.flatMapLatest { snapshot ->
            snapshot.whereEqualTo("id", id).toFlow()
        }
        return snapshotFlow.mapLatest { snapshot ->
            if (snapshot.isEmpty) {
                // 사전 처리 DB로 전환하기 위한 에러 반환
                throw IllegalStateException("Not Found")
            }
            snapshot.mapNotNull {
                val json = gson.toJsonTree(it.data)
                gson.fromJson(json, Session::class.java) }[0]
        }.catch {
            Timber.e(it)
            prePackagedDb.getSessionById(id)?.let { session ->
                emit(session)
            }
        }
    }
}

private suspend fun Query.fastGet(): QuerySnapshot {
    return try {
        get(Source.CACHE).await()
    } catch (e: Exception) {
        get(Source.SERVER).await()
    }
}

private suspend fun DocumentReference.fastGet(): DocumentSnapshot {
    return try {
        get(Source.CACHE).await()
    } catch (e: Exception) {
        get(Source.SERVER).await()
    }
}

private suspend fun CollectionReference.fastGet(): QuerySnapshot {
    return try {
        get(Source.CACHE).await()
    } catch (e: Exception) {
        get(Source.SERVER).await()
    }
}

private fun Query.toFlow() = callbackFlow {
    val listener = addSnapshotListener { snapshot, exception ->
        if (exception != null) close(exception)
        if (snapshot != null) {
            offer(snapshot)
        }
    }
    awaitClose { listener.remove() }
}

private suspend fun CollectionReference.cacheFirstGet(): QuerySnapshot {
    val cacheSnapshot = get(Source.CACHE).await()
    if (cacheSnapshot != null && !cacheSnapshot.isEmpty) {
        return cacheSnapshot
    }
    return get(Source.SERVER).await()
}

/**
 * 1차: time 순서
 * 2차: track 순서
 */
private fun List<Session>.toSortedSessions(): List<Session> =
    sortedWith(
        compareBy { it.id }
    )