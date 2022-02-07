package com.example.kotlin1lesson5.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin1lesson5.BuildConfig
import com.example.kotlin1lesson5.`object`.Constant
import com.example.kotlin1lesson5.base.BaseViewModel
import com.example.kotlin1lesson5.models.PlayList
import com.example.kotlin1lesson5.remote.ApiService
import com.example.kotlin1lesson5.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<PlayList> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<PlayList> {

        val data = MutableLiveData<PlayList>()

        apiService.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 30)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    Log.e("TAG", "onFailure: " + t.message)
                }
            })
        return data
    }
}