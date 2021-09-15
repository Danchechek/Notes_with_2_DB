package com.example.myapplication.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.utilits.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
    fun signOut(){
        REPOSITORY.signOut()
    }
}