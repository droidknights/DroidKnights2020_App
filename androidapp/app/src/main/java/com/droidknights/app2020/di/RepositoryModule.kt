package com.droidknights.app2020.di

import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.db.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun bindSessionDataRepository(localRepository: SessionRepositoryImpl): SessionRepository
}