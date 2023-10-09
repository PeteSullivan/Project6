package com.example.project6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0,
    @ColumnInfo(name = "note_title")
    var noteTitle: String = "",
    @ColumnInfo(name = "note_description")
    var noteDescription: String = ""
)