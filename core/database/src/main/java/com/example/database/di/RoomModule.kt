package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.dao.CandidateDao
import com.example.database.database.CandidateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RoomModule {


    @ViewModelScoped
    @Provides
    fun provideDB(@ApplicationContext context: Context) : CandidateDatabase {
        return Room.databaseBuilder(context, CandidateDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @ViewModelScoped
    @Provides
    fun provideDao(database : CandidateDatabase) : CandidateDao {
        return database.candidateDao()
    }

}
