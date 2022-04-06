package com.rizkyhamdana.bumdesprototype.ui.user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.bumdesprototype.data.PopularResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukEntity
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.repository.AppRepository
import com.rizkyhamdana.bumdesprototype.util.DummyData

class HomeViewModel : ViewModel() {


    private val appRepository: AppRepository = AppRepository()


    private var listProduk = ArrayList<ProdukEntity>()

    fun setProduk(category : Int): List<ProdukEntity>{
        val dataDummy = DummyData.generateDummyProduk()
        for(i in dataDummy){
            if (category == i.kategori){
                listProduk.add(i)
            }
        }
        return listProduk
    }

    fun getFoodPopular(): LiveData<List<PopularResponse>> = appRepository.getFoodPopular()
    fun getDrinkPopular(): LiveData<List<PopularResponse>> = appRepository.getDrinkPopular()
    fun getSnackPopular(): LiveData<List<PopularResponse>> = appRepository.getSnackPopular()

    fun getAllStand() : LiveData<List<StandResponse>> = appRepository.getAllStand()


}