package com.thoughtworks.car.sample.di

import android.content.Context
import com.thoughtworks.car.media.sound.alert.AlertPlayer
import com.thoughtworks.car.media.sound.alert.AlertPlayerImpl
import com.thoughtworks.car.media.sound.media.MediaPlayer
import com.thoughtworks.car.media.sound.media.MediaPlayerImpl
import com.thoughtworks.car.media.sound.tts.TTSSpeaker
import com.thoughtworks.car.media.sound.tts.TTSSpeakerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SoundModule {

    @Provides
    fun providerAlertPlayer(@ApplicationContext context: Context): AlertPlayer = AlertPlayerImpl(context)

    @Provides
    fun providerMediaPlayer(@ApplicationContext context: Context): MediaPlayer = MediaPlayerImpl(context)

    @Provides
    fun providerTTSSpeaker(@ApplicationContext context: Context): TTSSpeaker = TTSSpeakerImpl(context)
}