package com.groundzero.camw.data

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class FirebaseDatabase {

    init {
        initializeFirebase()
    }

    val firestore: Firestore = FirestoreClient.getFirestore()

    private fun initializeFirebase() = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(getFileInput()))
            .setDatabaseUrl(DATABASE_URL)
            .build().also {
                FirebaseApp.initializeApp(it)
            }

    private fun getFileInput() = FileInputStream(KEY)

    companion object {
        private const val DATABASE_URL = "https://catholic-prayerbook.firebaseio.com"
        private const val KEY = "catholic-prayerbook-firebase-adminsdk-vh3gk-2f5e943dcd.json"
    }
}