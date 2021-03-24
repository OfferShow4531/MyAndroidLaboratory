package com.example.lab6done.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab6done.data.StudentsDatabase
import com.example.lab6done.repository.StudentsRepository
import com.example.lab6done.model.Students
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsViewModel(application: Application): AndroidViewModel(application) {

    val readData:LiveData<List<Students>>
    private val repository: StudentsRepository



    init{
       val studentsDao = StudentsDatabase.getDataBase(
               application
       ).studentsDao()
        repository = StudentsRepository(studentsDao)
        readData = repository.readData
    }

    fun addStudent(students: Students){
        viewModelScope.launch(Dispatchers.IO){
            repository.addStudent(students)
        }
    }

    fun updateStudent(students: Students){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateStudent(students)
        }
    }

    fun deleteStudent(students: Students){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteStudent(students)
        }
    }

    fun deleteAllStudent(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllStudent()
        }
    }



}