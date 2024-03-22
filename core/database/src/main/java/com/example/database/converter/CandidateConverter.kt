package com.example.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import entity.CandidateInfo
import entity.Contact
import entity.Education
import entity.Experience

class CandidateConverter {
    @TypeConverter
    fun fromCandidateInfoObject(candidateInfo: CandidateInfo?) : String {
        val type = object : TypeToken<CandidateInfo?>() {}.type
        return Gson().toJson(candidateInfo, type)
    }
    @TypeConverter
    fun toCandidateInfoObject(candidateInfo: String?) : CandidateInfo? {
        val type = object : TypeToken<CandidateInfo?>() {}.type
        return Gson().fromJson(candidateInfo, type)
    }

    @TypeConverter
    fun fromEducationObject(education: Education?) : String {
        val type = object : TypeToken<Education?>() {}.type
        return Gson().toJson(education, type)
    }
    @TypeConverter
    fun fromEducationList(education: List<Education>?) : String {
        val type = object : TypeToken<List<Education>?>() {}.type
        return Gson().toJson(education, type)
    }
    @TypeConverter
    fun toEducationObject(education: String?) : Education? {
        val type = object : TypeToken<Education?>() {}.type
        return Gson().fromJson(education, type)
    }
    @TypeConverter
    fun toEducationList(education: String?) : List<Education>? {
        val type = object : TypeToken<List<Education>?>() {}.type
        return Gson().fromJson(education, type)
    }
    @TypeConverter
    fun fromExperienceObject(experience: Experience?) : String {
        val type = object : TypeToken<Experience?>() {}.type
        return Gson().toJson(experience, type)
    }
    @TypeConverter
    fun fromExperienceList(experience: List<Experience>?) : String {
        val type = object : TypeToken<List<Experience>?>() {}.type
        return Gson().toJson(experience, type)
    }
    @TypeConverter
    fun toExperienceObject(experience: String?) : Experience? {
        val type = object : TypeToken<Experience?>() {}.type
        return Gson().fromJson(experience, type)
    }
    @TypeConverter
    fun toExperienceList(experience: String?) : List<Experience>? {
        val type = object : TypeToken<List<Experience>?>() {}.type
        return Gson().fromJson(experience, type)
    }
    @TypeConverter
    fun fromContactObject(contact: Contact?) : String {
        val type = object : TypeToken<Contact?>() {}.type
        return Gson().toJson(contact, type)
    }
    @TypeConverter
    fun toContactObject(contact: String?) : Contact? {
        val type = object : TypeToken<Contact?>() {}.type
        return Gson().fromJson(contact, type)
    }
}