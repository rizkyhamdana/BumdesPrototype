package com.rizkyhamdana.bumdesprototype.ui.owner.message

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.ChatResponse
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class OwnerMessageViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }

    fun getAllChat(stand: String): LiveData<List<String>> = appRepository.getAllChat(stand)

    fun getAllUser(): LiveData<List<UserResponse>> = appRepository.getAllUser()


    fun getOwnerById(id: String) : LiveData<OwnerResponse> = appRepository.getOwnerById(id)

    fun getChatbyStand(stand: String, myId: String): LiveData<List<ChatResponse>> = appRepository.getChatbyStand(stand, myId)
}
