package com.example.noteyapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    //@ColumnInfo(name = "note_title")
    val title: String,
    val description: String,
    val color: Int // Store color as an ARGB Integer
    // Room Doesn't directly support complex types like 'Color'
)