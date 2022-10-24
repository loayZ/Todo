package com.example.mvvmtodo.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mvvmtodo.data.TaskDao

//hold the task here to not lose the session data and to not fetch the data again form TasksFragment
class TasksViewModel @ViewModelInject constructor(

    private val taskDao: TaskDao

) : ViewModel() {

    val tasks = taskDao.getTasks().asLiveData()

}