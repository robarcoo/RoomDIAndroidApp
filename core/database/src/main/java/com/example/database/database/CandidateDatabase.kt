package com.example.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.converter.CandidateConverter
import dao.CandidateDao
import entity.Candidate
import entity.CandidateInfo
import entity.Contact
import entity.Education
import entity.Experience

@Database(entities = [Candidate::class,
    CandidateInfo::class,
    Contact::class,
    Education::class,
    Experience::class], version = 1)
@TypeConverters(CandidateConverter::class)
abstract class CandidateDatabase : RoomDatabase() {

    abstract fun candidateDao() : CandidateDao
}