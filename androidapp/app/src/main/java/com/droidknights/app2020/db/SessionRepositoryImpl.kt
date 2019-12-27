package com.droidknights.app2020.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.droidknights.app2020.ui.data.SessionData
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(val db : FirebaseFirestore) : SessionRepository {
    private val TAG = this::class.java.simpleName

    private val sessionDataList = MutableLiveData<List<SessionData>>()
    private val sessionData = MutableLiveData<SessionData>()

    override fun get(): LiveData<List<SessionData>> {
        db.collection("Session")
            .get()
            .addOnSuccessListener { result ->
                val list = arrayListOf<SessionData>()
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    list.add(document.toObject(SessionData::class.java))
                }
                sessionDataList.postValue(list)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return sessionDataList
    }

    override fun getById(id: String): LiveData<SessionData> {
        db.collection("Session").document(id)
            .get()
            .addOnSuccessListener { result ->
                sessionData.postValue(result.toObject(SessionData::class.java))
            }
            .addOnFailureListener {
                Log.w(TAG, "Error getting documents.", it)
            }
        return sessionData
    }
}