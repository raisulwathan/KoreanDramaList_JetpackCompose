package com.example.submission.di

import com.example.submission.data.SerialRepository

object Injection {
    fun provideRepository(): SerialRepository {
        return SerialRepository.getInstance()
    }
}