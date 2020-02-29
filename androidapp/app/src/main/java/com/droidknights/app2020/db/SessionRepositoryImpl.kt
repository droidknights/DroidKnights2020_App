package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val prePackagedDb: PrePackagedDb
) : SessionRepository {
    private val TAG = this::class.java.simpleName

    override suspend fun get(): Flow<List<Session>> = flow {
        val snapshot = db.collection("Session").cacheFirstGet()
        emit(snapshot.map { it.toObject(Session::class.java) })
    }.catch {
        Timber.e(it)
        emit(prePackagedDb.getSessionList())
    }

    override suspend fun getById(id: String): Flow<Session> {
        return db.collection("Session")
            .whereEqualTo("id", id)
            .toFlow()
            .map { snapshot ->
                snapshot.map {
                    it.toObject(Session::class.java)
                }[0]
            }.catch {
                prePackagedDb.getSessionById(id)?.let {
                    emit(it)
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
        if (snapshot != null && !snapshot.isEmpty) {
            runCatching {
                offer(snapshot!!)
            }
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
