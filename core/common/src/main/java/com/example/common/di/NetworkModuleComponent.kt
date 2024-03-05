package com.example.common.di
import com.example.di.NetworkModule
import com.example.model.Network
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkModuleComponent {
    fun provideNetwork() : Network
}



