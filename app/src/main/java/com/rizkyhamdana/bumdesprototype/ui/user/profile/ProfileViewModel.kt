package com.rizkyhamdana.bumdesprototype.ui.user.profile


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class ProfileViewModel : ViewModel() {

    private val appRepository: AppRepository = AppRepository()

    fun getAllUser(): LiveData<List<UserResponse>> = appRepository.getAllUser()
}