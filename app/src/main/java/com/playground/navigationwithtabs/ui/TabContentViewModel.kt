package com.playground.navigationwithtabs.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.playground.navigationwithtabs.db.AppDatabase
import com.playground.navigationwithtabs.db.Task
import com.playground.navigationwithtabs.repository.TaskRepository
import kotlinx.coroutines.launch

class TabContentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = TaskRepository(db.taskDao(), db.taskTypeDao())
    }

    fun getTasks(taskType: String) = repository.tasksForType(taskType)

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun updateTaskCompleteness(task: Task) {
        task.completed = !task.completed
        viewModelScope.launch {
            repository.upsertTask(task)
        }
    }
}