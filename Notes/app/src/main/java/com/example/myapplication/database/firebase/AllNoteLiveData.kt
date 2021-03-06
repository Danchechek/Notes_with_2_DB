package com.example.myapplication.database.firebase

import androidx.lifecycle.LiveData
import com.example.myapplication.model.AppNote
import com.example.myapplication.utilits.REF_FIREBASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNoteLiveData: LiveData<List<AppNote>>() {

    private val listener = object : ValueEventListener{
        override fun onDataChange(p0: DataSnapshot) {
            value = p0.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }

        override fun onCancelled(p0: DatabaseError) {

        }
    }

    override fun onActive() {
        REF_FIREBASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_FIREBASE.removeEventListener(listener)
        super.onInactive()
    }
}