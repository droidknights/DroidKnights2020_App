package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore,
    private val prePackagedDb: PrePackagedDb
) : SessionRepository {
    private val TAG = this::class.java.simpleName

    override fun get(): Flow<List<Session>> = flow {
        val snapshot = db.collection("Session").fastGet()
        if (snapshot.isEmpty) {
            emit(prePackagedDb.getSessionList())
        } else {
            emit(snapshot.map {
                it.toObject(Session::class.java)
            })
        }
    }

    override fun getById(id: String): Flow<Session> = flow {
        val snapshot = db.collection("Session")
            .whereEqualTo("id", id)
            .fastGet()
        if (snapshot.isEmpty) {
            prePackagedDb.getSessionById(id)?.let {
                emit(it)
            }
        } else {
            emit(snapshot.map { it.toObject(Session::class.java) }[0])
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

private fun Query.toFlow(): Flow<QuerySnapshot> {
    return callbackFlow<QuerySnapshot> {
        val listenerRegistration = addSnapshotListener { snapshot, exception ->
            if (exception != null) close(exception)
            else if (snapshot != null) {
                offer(snapshot)
            }
        }
        awaitClose { listenerRegistration.remove() }
    }
}
