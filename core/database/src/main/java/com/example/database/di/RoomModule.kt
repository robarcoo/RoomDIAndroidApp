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
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(ViewModelComponent::class)
//object ContextModule {
//
//    @Singleton
//    @Provides
//    fun provideApplicationContext(@ApplicationContext context: Context): Context {
//        return context
//    }
//}

@Module
@InstallIn(ViewModelComponent::class)
class RoomModule() {

    @Singleton
    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun getRoom (context: Context) : CandidateDatabase {
        return Room.databaseBuilder(context, CandidateDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun getDao(database: CandidateDatabase) : CandidateDao {
        return database.CandidateDao()
    }

}
