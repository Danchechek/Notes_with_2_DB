package com.example.myapplication.database.firebase

import androidx.lifecycle.LiveData
import com.example.myapplication.R
import com.example.myapplication.database.DatabaseRepository
import com.example.myapplication.model.AppNote
import com.example.myapplication.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository: DatabaseRepository {

    init{
        AUTH_FIREBASE = FirebaseAuth.getInstance()

    }

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()



    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_FIREBASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_FIREBASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_FIREBASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun change(note: AppNote, onSuccess: () -> Unit) {
        val mapNote = hashMapOf<String, Any>()
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        REF_FIREBASE.child(note.idFirebase).updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onUnSuccess: (String) -> Unit) {
        if(AppPreference.getInitUser()){
            initRefs(LOGIN)
            onSuccess()
        } else{
            AUTH_FIREBASE.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    FIREBASE_CONNECTION_TYPE = LOGIN
                    initRefs(LOGIN)
                    onSuccess()
                }
                .addOnFailureListener {
                    AUTH_FIREBASE.createUserWithEmailAndPassword(EMAIL, PASSWORD).
                    addOnSuccessListener {
                        initRefs(REGISTRATION)
                        onSuccess()
                    }
                        .addOnFailureListener { onUnSuccess(it.message.toString()) }
                }
        }

    }

    fun initRefs(typeConnection: String){
        FIREBASE_CONNECTION_TYPE = typeConnection
        CURRENT_ID_FIREBASE = AUTH_FIREBASE.currentUser?.uid.toString()
        REF_FIREBASE = FirebaseDatabase
            .getInstance(URL_FIREBASE)
            .reference
            .child(CURRENT_ID_FIREBASE)
    }

    override fun signOut() {
        AUTH_FIREBASE.signOut()
    }
}