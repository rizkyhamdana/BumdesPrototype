package com.rizkyhamdana.bumdesprototype.ui.user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.bumdesprototype.data.ProdukEntity
import com.rizkyhamdana.bumdesprototype.util.DummyData

class HomeViewModel : ViewModel() {

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

}