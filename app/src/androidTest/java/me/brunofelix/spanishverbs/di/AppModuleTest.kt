package me.brunofelix.spanishverbs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brunofelix.spanishverbs.data.AppDatabase
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Named("test_db")
    fun provideInMymoryDb(@ApplicationContext context: Context): AppDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
}
