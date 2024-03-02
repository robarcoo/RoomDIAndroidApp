package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Candidate (
    val candidateInfo: CandidateInfo,
    val education: List<Education>,
    val experience: List<Experience>,
    val freeForm : String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)