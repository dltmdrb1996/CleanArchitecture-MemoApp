package com.example.hiltex.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.NonCancellable.isCompleted

@Entity
data class Todo(  @ColumnInfo(name = "title") var title: String = ""){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val titleForList: String
        get() = if (title.isNotEmpty()) title else "공백"

//    val isActive
//        get() = !isCompleted
//
//    val isEmpty
//        get() = title.isEmpty() || description.isEmpty()
}