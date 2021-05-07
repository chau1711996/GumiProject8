package com.example.gumiproject8.di

import androidx.room.Room
import com.example.gumiproject8.data.ApiService
import com.example.gumiproject8.data.RequestInterceptor
import com.example.gumiproject8.data.room.BaseDatabase
import com.example.gumiproject8.data.room.FavoriteDAO
import com.example.gumiproject8.repository.BaseRepository
import com.example.gumiproject8.repository.FavoriteRepository
import com.example.gumiproject8.ui.MyViewModel
import com.example.gumiproject8.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val applicationModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            BaseDatabase::class.java,
            Constants.FAVORITE_DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
    single { get<BaseDatabase>().favoriteDAO() }

    single { FavoriteRepository(get()) }
    single { BaseRepository(get()) }

    viewModel { MyViewModel(get(), get()) }

}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttp(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder().addInterceptor(RequestInterceptor())
    return okHttpClient.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}