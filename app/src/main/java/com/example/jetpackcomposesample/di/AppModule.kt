package com.example.jetpackcomposesample.di

import android.content.Context
import com.example.jetpackcomposesample.presentation.RecipeApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): RecipeApplication {
        return app as RecipeApplication
    }
}