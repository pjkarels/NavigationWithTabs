package com.playground.navigationwithtabs.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TaskTypeDao {

    @get: Query("SELECT * FROM task_type_table")
    val taskTypes: LiveData<List<TaskType>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTaskTypes(vararg types: TaskType)

    @Query("DELETE FROM task_type_table WHERE :type LIKE name")
    fun deleteTaskType(type: String)

    @Delete
    suspend fun deleteCategories(categories: List<TaskType>)
}