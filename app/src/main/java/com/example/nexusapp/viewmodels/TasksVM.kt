package com.example.nexusapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexusapp.Repo.Repository
import com.example.nexusapp.Repo.Resource
import com.example.nexusapp.models.TaskResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksVM @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    var tasks by mutableStateOf<Resource<List<TaskResponse>>>(Resource.Loading())

    fun getAllTasks(){
        viewModelScope.launch {
            repo.getAllTasks().collect{
                tasks=it
            }
        }
    }

    fun addTasks(taskResponse: TaskResponse): Resource<TaskResponse> {
        var result by  mutableStateOf<Resource<TaskResponse>>(Resource.Loading())
        viewModelScope.launch {
            repo.addTask(taskResponse).collect{
                result=it
            }
        }
        return result
    }

    fun updateTask(taskResponse: TaskResponse): Resource<TaskResponse> {
        var result by  mutableStateOf<Resource<TaskResponse>>(Resource.Loading())
        viewModelScope.launch {
            repo.updateTask(taskResponse).collect{
                result=it
            }
        }
        return result
    }

    fun deleteTask(id:Int): Resource<String> {
        var result by  mutableStateOf<Resource<String>>(Resource.Loading())
        viewModelScope.launch {
            repo.deleteTask(id).collect{
                result=it
            }
        }
        return result
    }


    init {
        getAllTasks()
    }
}