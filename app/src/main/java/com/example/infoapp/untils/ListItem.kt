package com.example.infoapp.untils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_items")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val imageName: String,
    val htmlName: String,
    val category: String,
    val isFav: Boolean
)
