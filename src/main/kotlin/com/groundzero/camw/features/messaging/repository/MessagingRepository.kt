package com.groundzero.camw.features.messaging.repository

import com.google.firebase.database.DatabaseReference
import com.groundzero.camw.core.data.providers.FirebaseRealtimeDatabaseProvider
import com.groundzero.camw.core.data.providers.FirestoreProvider
import com.groundzero.camw.features.thoughts.data.Thought
import org.springframework.stereotype.Component

@Component
class MessagingRepository(
        private val firestoreProvider: FirestoreProvider,
        private val realtimeDatabase: FirebaseRealtimeDatabaseProvider
) {

    fun addMessageToFirestore(collectionKey: String, thought: Thought) =
            firestoreProvider.firestore.collection(collectionKey).document(thought.itemId!!).set(thought)

    fun updateRealTimeDatabaseThought(collectionKey: String, thought: Thought, listener: DatabaseReference.CompletionListener) =
            realtimeDatabase.realtimeDatabase.child(collectionKey).child(THOUGHT_LATEST_KEY).setValue(thought, listener)

    private companion object {
        const val THOUGHT_LATEST_KEY = "latest"
    }
}