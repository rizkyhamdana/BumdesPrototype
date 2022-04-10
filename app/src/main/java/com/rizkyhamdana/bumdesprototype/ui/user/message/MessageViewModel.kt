package com.rizkyhamdana.bumdesprototype.ui.user.message

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class MessageViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }

    fun getAllOwner(): LiveData<List<OwnerResponse>> = appRepository.getAllOwner()
}