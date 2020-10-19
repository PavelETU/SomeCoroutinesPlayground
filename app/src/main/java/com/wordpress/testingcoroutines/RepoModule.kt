package com.wordpress.testingcoroutines

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepoModule {
    @Singleton
    @Binds
    abstract fun bindRepo(repoImpl: RepositoryImpl): Repository
}