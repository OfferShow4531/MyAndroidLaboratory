package com.example.lab6done.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Students(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Name: String,
    val age: Int,
    val AboutSelf: String,
    val rating: Int
): Parcelable