package com.example.icecream

interface Completion {
    fun onComplete(result : Any)
    fun onFail(name: String, mgs: String)
}