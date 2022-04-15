package com.rizkyhamdana.bumdesprototype.ui.owner.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class OwnerHomeViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }

    fun getFoodbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getFoodbyStand(stand)
    fun getDrinkbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getDrinkbyStand(stand)
    fun getSnackbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getSnackbyStand(stand)

    fun getOwnerById(id: String) : LiveData<OwnerResponse> = appRepository.getOwnerById(id)

}