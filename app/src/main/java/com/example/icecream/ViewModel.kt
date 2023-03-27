package com.example.icecream
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icecream.models.api.Chat
import com.example.icecream.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewModel(private val repo : Repository) : ViewModel() {
    private var url =  MutableLiveData("")
    private var uid  = MutableLiveData("")
    private val chat_live : MutableLiveData<Chat> by lazy{ MutableLiveData<Chat>() }

    fun getUrl(){
        repo.getAPIUrl(comp)
    }

    fun getChat(text : String,url: String){
        val chat  = viewModelScope.async {
            repo.getChat(text,url).body()
        }
        viewModelScope.launch(Dispatchers.Main) {
                 chat_live.value = chat.await()

        }
    }

    fun signIn(){
        val user =  viewModelScope.async {
            repo.signIn()?.user
        }
        viewModelScope.launch(Dispatchers.Main) {
            uid.value = user.await()?.uid
        }

    }

    fun observeChat():LiveData<Chat>{
        return chat_live
    }

    fun observeUrl():LiveData<String>{
        return url
    }

    fun observeUid():LiveData<String>{
        return uid
    }

    private val comp = object : Completion {
        override fun onComplete(result: Any) {
            if(result is String){
                url.value = result
            }
            else if(result is Chat){
                chat_live.value = result
            }
        }

        override fun onFail(name: String, mgs: String) {
            e(name,mgs)
        }
    }
}