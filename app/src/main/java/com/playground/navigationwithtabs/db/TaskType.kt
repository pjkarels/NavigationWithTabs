package com.playground.navigationwithtabs.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_type_table")
data class TaskType (
    @PrimaryKey @ColumnInfo(name = "name") val name: String)