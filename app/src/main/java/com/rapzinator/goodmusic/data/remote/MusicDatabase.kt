package com.rapzinator.goodmusic.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.rapzinator.goodmusic.data.entities.Song
import com.rapzinator.goodmusic.utils.Constants.MIXTAPES_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firebase = FirebaseFirestore.getInstance()
    private val  songCollection = firebase.collection(MIXTAPES_COLLECTION)

    suspend fun getAllMixTapes(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}