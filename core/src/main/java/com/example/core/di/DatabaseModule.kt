package com.example.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.example.core.data.source.local.room.FavoriteMovieTvDao
import com.example.core.data.source.local.room.MovieTvDao
import com.example.core.data.source.local.room.MovieTvDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieTvDatabase = Room.databaseBuilder(
        context,
        MovieTvDatabase::class.java, "MovieTvLocal.db"
    ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideMovieTvDao(database: MovieTvDatabase): MovieTvDao = database.movieTvDao()

    @Provides
    fun provideFavoriteMovieTvDao(database: MovieTvDatabase): FavoriteMovieTvDao = database.favoriteMovieTvDao()
}