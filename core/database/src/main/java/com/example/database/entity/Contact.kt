package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (
    val candidateId: Int,
    val phone: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)