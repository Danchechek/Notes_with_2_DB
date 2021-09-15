package com.example.myapplication.utilits

import com.example.myapplication.MainActivity
import com.example.myapplication.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var AUTH_FIREBASE: FirebaseAuth
lateinit var CURRENT_ID_FIREBASE: String
const val URL_FIREBASE = "https://notes-e8f85-default-rtdb.europe-west1.firebasedatabase.app"
lateinit var REF_FIREBASE: DatabaseReference
lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY:DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
lateinit var FIREBASE_CONNECTION_TYPE: String
const val REGISTRATION = "new_user"
const val LOGIN = "login"
lateinit var EMAIL: String
lateinit var PASSWORD: String
const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"