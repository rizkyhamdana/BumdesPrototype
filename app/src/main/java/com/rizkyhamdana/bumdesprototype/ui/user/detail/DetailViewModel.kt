package com.rizkyhamdana.bumdesprototype.ui.user.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.AppDatabase
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        appRepository = AppRepository(appDao)
    }


    fun getUserbyId(id: String): LiveData<UserResponse> = appRepository.getUserbyid(id)

    fun getOwnerbyStand(stand: String): LiveData<OwnerResponse> = appRepository.getOwnerByStand(stand)

    fun getFoodbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getFoodbyStand(stand)
    fun getDrinkbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getDrinkbyStand(stand)
    fun getSnackbyStand(stand: String): LiveData<List<ProdukResponse>> = appRepository.getSnackbyStand(stand)

    fun getFoodbyQuery(query: String): LiveData<List<ProdukResponse>> = appRepository.getFoodbyQuery(query)
    fun getDrinkbyQuery(query: String): LiveData<List<ProdukResponse>> = appRepository.getDrinkbyQuery(query)
    fun getSnackbyQuery(query: String): LiveData<List<ProdukResponse>> = appRepository.getSnackbyQuery(query)

    fun insertCheckout(checkout: Checkout){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertCheckout(checkout)

        }
    }

    fun getCheckoutbyId(id: String) : LiveData<Checkout> = appRepository.getCheckoutbyId(id)


    fun clearCheckout() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.clearCheckout()
        }
    }

    fun deleteCheckout(checkout: Checkout) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteCheckout(checkout)
        }
    }


    fun getCheckoutbyUser(idUser: String) : LiveData<List<Checkout>> = appRepository.getCheckoutbyUser(idUser)

}