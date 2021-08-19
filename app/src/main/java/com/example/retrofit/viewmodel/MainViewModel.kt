package com.example.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.MyModel
import com.example.retrofit.repository.MyRepository
import kotlinx.coroutines.launch
import java.io.IOException


class MainViewModel(private val repository: MyRepository):ViewModel() {

    val data:MutableLiveData<List<MyModel>> = MutableLiveData()
    var internet:Boolean = false

    init {
        getDataViewModel()
    }

    fun getDataViewModel(){
        internet = isConnected()
        if (internet){
            viewModelScope.launch {
                val response = repository.getData()
                if(response.isSuccessful){
                    data.postValue(response.body())
                }
            }
        }
    }

    @Throws(InterruptedException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}