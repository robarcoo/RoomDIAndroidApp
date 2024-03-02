package com.example.common.di
import com.example.common.viewmodel.CandidateViewModel
import com.example.database.dao.CandidateDao
import com.example.database.di.RoomModule
import com.example.database.local.LocalDataSource
import com.example.di.NetworkModule
import com.example.model.Network
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkModuleComponent {
    fun provideNetwork() : Network
}

@Singleton
@Component(modules = [RoomModule::class])
interface RoomModuleComponent {
    fun provideRoom() : CandidateViewModel

}

