package com.rizkyhamdana.bumdesprototype.ui.owner.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class OwnerProfileViewModel: ViewModel(){
    private val appRepository: AppRepository = AppRepository()

    fun getAllOwner(): LiveData<List<OwnerResponse>> = appRepository.getAllOwner()
}