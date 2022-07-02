package me.brunofelix.spanishverbs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.brunofelix.spanishverbs.data.AppDatabase
import me.brunofelix.spanishverbs.data.VerbDao
import me.brunofelix.spanishverbs.data.VerbRepository
import me.brunofelix.spanishverbs.data.VerbRepositoryImpl
import me.brunofelix.spanishverbs.utils.AppProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

    @Singleton
    @Provides
    fun provideVerbDao(db: AppDatabase): VerbDao = db.verbDao()

    @Singleton
    @Provides
    fun provideVerbRepository(dao: VerbDao): VerbRepository = VerbRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideAppProvider(@ApplicationContext context: Context) = AppProvider(context)
}