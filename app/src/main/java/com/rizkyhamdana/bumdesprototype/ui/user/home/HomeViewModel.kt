package com.rizkyhamdana.bumdesprototype.ui.user.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.repository.AppRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }
    fun getFoodPopular(): LiveData<List<ProdukResponse>> = appRepository.getFoodPopular()
    fun getDrinkPopular(): LiveData<List<ProdukResponse>> = appRepository.getDrinkPopular()
    fun getSnackPopular(): LiveData<List<ProdukResponse>> = appRepository.getSnackPopular()

    fun getAllStand() : LiveData<List<StandResponse>> = appRepository.getAllStand()


    fun getAllUser(): LiveData<List<UserResponse>> = appRepository.getAllUser()


}