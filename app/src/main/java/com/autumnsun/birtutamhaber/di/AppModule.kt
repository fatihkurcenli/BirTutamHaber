package com.autumnsun.birtutamhaber.di

import android.content.Context
import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.ui.home.HomeRepository
import com.autumnsun.birtutamhaber.utils.Constants.BASE_URL
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideNewsApi(@ApplicationContext context: Context): NewsApi {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        builder.addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context).apply {
                    showNotification = false
                })
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        builder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepo(newsApi: NewsApi): HomeRepository {
        return HomeRepository(newsApi)
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