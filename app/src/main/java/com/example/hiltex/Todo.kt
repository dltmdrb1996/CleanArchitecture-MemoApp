package com.example.roomex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(  @ColumnInfo(name = "title") var title: String = ""){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}