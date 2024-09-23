package com.example.yemekvaktii.di

import com.example.yemekvaktii.data.datasource.YemeklerDataSource
import com.example.yemekvaktii.data.repo.YemeklerRepository
import com.example.yemekvaktii.retrofit.ApiUtils
import com.example.yemekvaktii.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yds : YemeklerDataSource) : YemeklerRepository {
        return YemeklerRepository(yds)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao : YemeklerDao) : YemeklerDataSource{
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }


}