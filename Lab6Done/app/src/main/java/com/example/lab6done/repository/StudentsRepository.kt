package com.example.lab6done.repository

import androidx.lifecycle.LiveData
import com.example.lab6done.data.StudentsDao
import com.example.lab6done.model.Students

class StudentsRepository(private val studentsDao: StudentsDao) {

    val readData: LiveData<List<Students>> = studentsDao.readData()

    suspend fun addStudent(students: Students){
        studentsDao.addStudent(students)
    }

    suspend fun updateStudent(students: Students){
        studentsDao.updateStudent(students)
    }
    suspend fun deleteStudent(students: Students){
        studentsDao.deleteStudent(students)
    }

    suspend fun deleteAllStudent(){
        studentsDao.deleteAllStudent()
    }
}