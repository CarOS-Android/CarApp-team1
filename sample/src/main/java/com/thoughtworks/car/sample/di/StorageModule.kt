package com.thoughtworks.car.sample.di

import com.thoughtworks.car.core.storage.FileManager
import com.thoughtworks.car.core.storage.StorageInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providerFileManager(): StorageInterface =
        FileManager()
}