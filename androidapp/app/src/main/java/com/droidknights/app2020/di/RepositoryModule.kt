package com.droidknights.app2020.di

import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.db.SessionRepositoryImpl
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideSessionRepository(
        db: FirebaseFirestore,
        prePackagedDb: PrePackagedDb,
        gson: Gson
    ): SessionRepository {
        return SessionRepositoryImpl(db, prePackagedDb, gson)
    }
}