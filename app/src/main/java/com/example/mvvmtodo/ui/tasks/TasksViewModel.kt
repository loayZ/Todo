package com.example.mvvmtodo.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mvvmtodo.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

//hold the task here to not lose the session data and to not fetch the data again form TasksFragment
class TasksViewModel @ViewModelInject constructor(

    private val taskDao: TaskDao

) : ViewModel() {

// MutableStateFlow it can hold a single value and we can use it as flow
    val searchQuery = MutableStateFlow("")

    private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

    val tasks = tasksFlow.asLiveData()

}