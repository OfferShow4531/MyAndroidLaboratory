package com.example.lab6done.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lab6done.model.Students


@Dao
interface StudentsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(students: Students)

    @Update
    suspend fun updateStudent(students: Students)

    @Delete
    suspend fun deleteStudent(students: Students)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudent()

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readData(): LiveData<List<Students>>

}