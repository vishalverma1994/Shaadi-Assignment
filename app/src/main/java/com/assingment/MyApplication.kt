package com.assingment

import android.app.Application
import com.assingment.di.appModule
import com.assingment.di.databaseModule
import com.assingment.di.repoModule
import com.assingment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, repoModule, viewModelModule, databaseModule))
        }
    }
}