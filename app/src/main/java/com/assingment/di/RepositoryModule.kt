package com.assingment.di

import com.assingment.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get(), get())
    }
}