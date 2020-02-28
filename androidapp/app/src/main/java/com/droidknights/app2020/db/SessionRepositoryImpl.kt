package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SessionRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val prePackagedDb: PrePackagedDb
) : SessionRepository {
    private val TAG = this::class.java.simpleName

    override suspend fun get(): Flow<List<Session>> = flow {
        val snapshot = db.collection("Session").toCacheFirstFlow()
        emit(snapshot.map { it.toObject(Session::class.java) })
    }.catch {
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

private suspend fun CollectionReference.toCacheFirstFlow() =
    suspendCancellableCoroutine<QuerySnapshot> { con ->
        get(Source.CACHE).toComplete { cacheSnapshot, _ ->
            if (cacheSnapshot != null && !cacheSnapshot.isEmpty) {
                con.resume(cacheSnapshot)
            } else {
                get(Source.SERVER).toComplete { serverSnapshot, error ->
                    if (serverSnapshot != null && !serverSnapshot.isEmpty) {
                        con.resume(serverSnapshot)
                    } else {
                        con.resumeWithException(error ?: IllegalStateException("Error"))
                    }
                }
            }
        }
    }

private inline fun Task<QuerySnapshot>.toComplete(
    crossinline block: (snapshot: QuerySnapshot?, error: Throwable?) -> Unit
) = addOnCompleteListener { task ->
    block(task.result, task.exception)
}
