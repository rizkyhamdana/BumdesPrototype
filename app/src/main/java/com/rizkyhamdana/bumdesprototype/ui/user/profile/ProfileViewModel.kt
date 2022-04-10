package com.rizkyhamdana.bumdesprototype.ui.user.profile


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }


    fun getUserbyId(id: String): LiveData<UserResponse> = appRepository.getUserbyid(id)
}