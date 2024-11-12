package com.example.infoapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infoapp.untils.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("SELECT * FROM list_items WHERE category LIKE :category")
    fun getAllItemsByCategory(category: String): Flow<List<ListItem>>

    @Query("SELECT * FROM list_items WHERE isFav = 1")
    fun getFavorites(): Flow<List<ListItem>>
}