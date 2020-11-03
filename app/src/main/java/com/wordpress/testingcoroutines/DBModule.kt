package com.wordpress.testingcoroutines

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DBModule {
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): RoomDB = Room.databaseBuilder(context, RoomDB::class.java, "countries").build()
}