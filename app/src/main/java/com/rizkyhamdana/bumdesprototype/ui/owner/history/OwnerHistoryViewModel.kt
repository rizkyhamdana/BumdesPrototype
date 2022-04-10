package com.rizkyhamdana.bumdesprototype.ui.owner.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class OwnerHistoryViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }

    fun getAllOrderbyStand(idStand: String): LiveData<List<OrderResponse>> = appRepository.getOrderbyStand(idStand)

    fun getOwnerById(id: String) : LiveData<OwnerResponse> = appRepository.getOwnerById(id)
}