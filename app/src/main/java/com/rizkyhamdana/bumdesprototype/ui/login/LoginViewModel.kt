package com.rizkyhamdana.bumdesprototype.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class LoginViewModel: ViewModel() {

    private val appRepository: AppRepository = AppRepository()

    fun getAllOwner(): List<OwnerResponse> = appRepository.getAllOwner()

}