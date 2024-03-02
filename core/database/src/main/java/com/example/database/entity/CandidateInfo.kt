package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CandidateInfo (
    var candidateId: Int,
    val name: String,
    val profession: String,
    val sex: String,
    val birthDate: String,
    val contacts: Contact,
    val relocation: String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)