package com.example.icecream.repo

import com.example.icecream.Completion
import com.example.icecream.api.ChatApi
import com.example.icecream.constants.COLLECTION
import com.example.icecream.constants.DOCS
import com.example.icecream.models.api.Chat
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import retrofit2.Response

class Repository {
    private val cloud = Firebase.firestore
    private val auth = Firebase.auth
     suspend fun getChat(text : String,url : String) : Response<Chat>{
         return ChatApi.getInstance(url).getChat(text)
    }

    fun getAPIUrl(comp : Completion){

        cloud.collection(COLLECTION).document(DOCS).get().addOnSuccessListener {
            comp.onComplete(it.data?.get("url").toString())

        }.addOnFailureListener { it.message?.let { s -> comp.onFail("store",s)}}

    }
    suspend fun signIn(): AuthResult? {
        return auth.signInWithEmailAndPassword("banana@gmail.com","123456").await()
    }


}