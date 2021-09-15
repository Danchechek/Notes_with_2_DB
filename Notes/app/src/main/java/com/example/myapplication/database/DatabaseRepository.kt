package com.example.myapplication.database

import androidx.lifecycle.LiveData
import com.example.myapplication.model.AppNote

interface DatabaseRepository {
    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess:() -> Unit)
    suspend fun delete (note: AppNote, onSuccess:() -> Unit)
    suspend fun change(note: AppNote, onSuccess:() -> Unit)

    fun connectToDatabase(
        onSuccess: () -> Unit,
        onUnSuccess: (String) -> Unit) {}

    fun signOut() {}
}