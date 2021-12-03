package com.autumnsun.birtutamhaber.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


/*
 Created by Fatih Kurcenli on 12/3/2021
*/



object AppModule {

/*    @Singleton
    @Provides
    fun provideGson() = Gson()*/


/*    @Singleton
    @Provides
    fun provideTabuDatabase(
        @ApplicationContext context: Context
    ): TabuDataBase = Room.databaseBuilder(
        context,
        TabuDataBase::class.java,
        "note_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideTabuDao(
        tabuDb: TabuDataBase
    ) = tabuDb.getTabuDao()

    @Singleton
    @Provides
    fun provideTabuRepo(
        @ApplicationContext context: Context,
        tabuDao: TabuDao,
        gson: Gson
    ): TabuRepo {
        return TabuRepository(
            context,
            tabuDao,
            gson
        )
    }*/
}