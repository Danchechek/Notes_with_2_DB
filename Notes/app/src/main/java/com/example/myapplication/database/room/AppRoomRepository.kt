package com.example.myapplication.database.room

import androidx.lifecycle.LiveData
import com.example.myapplication.database.DatabaseRepository
import com.example.myapplication.model.AppNote
import com.example.myapplication.utilits.APP_ACTIVITY

class AppRoomRepository(private val appRoomDao: AppRoomDao): DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override suspend fun change(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.update(note)
        onSuccess()
    }

    override fun signOut() {

    }

}