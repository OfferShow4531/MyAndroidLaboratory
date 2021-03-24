package com.example.lab6done.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab6done.model.Students

@Database(entities = [Students::class], version = 1, exportSchema = false)

abstract class StudentsDatabase: RoomDatabase() {

    abstract fun studentsDao(): StudentsDao

    companion object {
        @Volatile
        private var INSTANCE: StudentsDatabase? = null

        fun getDataBase(context: Context): StudentsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentsDatabase::class.java,
                    "students_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

