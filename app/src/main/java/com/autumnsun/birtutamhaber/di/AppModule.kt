package com.autumnsun.birtutamhaber.di

import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.utils.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 Created by Fatih Kurcenli on 12/3/2021
*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


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