package com.example.myapplication.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.AppNote
import com.example.myapplication.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application) {
    fun delete(note: AppNote, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note){
                onSuccess()
            }
        }

    fun change(note: AppNote, onSuccess:() -> Unit)=
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.change(note){
                onSuccess()
            }
        }
}