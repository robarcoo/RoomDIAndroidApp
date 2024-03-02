package com.example.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.converter.CandidateConverter
import com.example.database.dao.CandidateDao
import com.example.database.entity.Candidate
import com.example.database.entity.CandidateInfo
import com.example.database.entity.Contact
import com.example.database.entity.Education
import com.example.database.entity.Experience
import dagger.Component
import javax.inject.Singleton

@Database(entities = [Candidate::class,
    CandidateInfo::class,
    Contact::class,
    Education::class,
    Experience::class], version = 1)
@TypeConverters(CandidateConverter::class)
abstract class CandidateDatabase : RoomDatabase() {
    abstract fun CandidateDao() : CandidateDao
}