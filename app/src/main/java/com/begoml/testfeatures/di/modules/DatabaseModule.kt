package com.begoml.testfeatures.di.modules

import android.content.Context
import androidx.room.Room
import com.begoml.datasource_impl.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Charity.db"
        ).allowMainThreadQueries().build()
    }

}