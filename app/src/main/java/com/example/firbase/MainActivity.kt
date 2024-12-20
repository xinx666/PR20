package com.example.firbase

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        setContent {
            MainScreen(onAddData = { userId, name, email ->
                saveUserData(userId, name, email)
            })
        }
    }

    private fun saveUserData(userId: String, name: String, email: String) {
        val user = User(name, email)
        databaseReference.child(userId).setValue(user)
            .addOnSuccessListener {
                // Data saved successfully
            }
            .addOnFailureListener {
                // Error while saving data
            }
    }
}

data class User(val name: String = "", val email: String = "")
