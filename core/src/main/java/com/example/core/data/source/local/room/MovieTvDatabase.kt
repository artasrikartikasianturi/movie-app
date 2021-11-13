package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.FavoriteMovieTvEntity
import com.example.core.data.source.local.entity.MovieTvEntity
import com.example.core.data.source.local.room.FavoriteMovieTvDao
import com.example.core.data.source.local.room.MovieTvDao

@Database(
    entities = [MovieTvEntity::class, FavoriteMovieTvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvDatabase : RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao
    abstract fun favoriteMovieTvDao(): FavoriteMovieTvDao
}