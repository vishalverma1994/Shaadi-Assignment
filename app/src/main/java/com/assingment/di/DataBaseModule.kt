package com.assingment.di

import com.assingment.data.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().userDao() }
}