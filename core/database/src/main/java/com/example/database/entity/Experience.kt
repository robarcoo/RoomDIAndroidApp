package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Experience(
    val candidateId: Int,
    val dateStart: String,
    val dateEnd: String,
    val companyName: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)