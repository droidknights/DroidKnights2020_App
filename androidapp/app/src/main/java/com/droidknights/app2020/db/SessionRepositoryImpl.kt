package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val prePackagedDb: PrePackagedDb
) : SessionRepository {
    private val TAG = this::class.java.simpleName

    override suspend fun get(): Flow<List<Session>> {
        return db.collection("Session").toFlow()
            .map { snapshot ->
                snapshot.map { it.toObject(Session::class.java) }
            }
    }

    override suspend fun getById(id: String): Flow<Session> {
        return db.collection("Session")
            .whereEqualTo("id", id)
            .toFlow()
            .map { snapshot ->
                snapshot.map {
                    it.toObject(Session::class.java)
                }[0]
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

private suspend fun DocumentReference.toFlow() = callbackFlow {
    val listener = addSnapshotListener { snapshot, exception ->
        if (exception != null) close(exception)
        if (snapshot != null && !snapshot.exists()) {
            runCatching {
                offer(snapshot!!)
            }
        }
    }
    awaitClose { listener.remove() }
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
