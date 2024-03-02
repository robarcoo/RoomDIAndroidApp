package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Education(
    val candidateId: Int,
    val type: String,
    val yearStart: String,
    val yearFinish: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)