package com.vitor238.covid19brasil.di

import android.content.Context
import androidx.room.Room
import com.vitor238.covid19brasil.data.api.BrazilService
import com.vitor238.covid19brasil.data.api.BrazilianStatesService
import com.vitor238.covid19brasil.data.database.CasesDatabase
import com.vitor238.covid19brasil.data.datasource.DataSource
import com.vitor238.covid19brasil.data.datasource.StatisticsLocalDataSource
import com.vitor238.covid19brasil.data.datasource.StatisticsRemoteDataSource
import com.vitor238.covid19brasil.data.repository.CasesRepositoryImpl
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import com.vitor238.covid19brasil.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { provideGsonConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideBrazilService(get()) }
    single { provideBrazilianStatesService(get()) }
    single { provideDatabase(androidContext()) }
    single { provideLocalDataSource(get()) }
    single { provideRemoteDataSource(get(), get()) }
    single { provideCasesRepository(get(), get()) }
}

fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun provideRetrofit(gson: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gson)
        .build()
}

fun provideBrazilService(retrofit: Retrofit): BrazilService {
    return retrofit.create(BrazilService::class.java)
}

fun provideBrazilianStatesService(retrofit: Retrofit): BrazilianStatesService {
    return retrofit.create(BrazilianStatesService::class.java)
}

fun provideDatabase(context: Context): CasesDatabase {
    synchronized(CasesDatabase::class.java) {
        return Room.databaseBuilder(
            context.applicationContext,
            CasesDatabase::class.java,
            "cases"
        ).build()
    }
}

fun provideLocalDataSource(casesDatabase: CasesDatabase): DataSource.Local {
    return StatisticsLocalDataSource(casesDatabase)
}

fun provideRemoteDataSource(
    brazilianStatesService: BrazilianStatesService,
    brazilService: BrazilService
): DataSource.Remote {
    return StatisticsRemoteDataSource(brazilianStatesService, brazilService)
}

fun provideCasesRepository(
    localDataSource: DataSource.Local,
    remoteDataSource: DataSource.Remote
): CasesRepository {
    return CasesRepositoryImpl(localDataSource, remoteDataSource)
}