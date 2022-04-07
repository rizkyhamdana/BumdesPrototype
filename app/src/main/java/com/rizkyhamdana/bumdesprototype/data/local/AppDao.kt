package com.rizkyhamdana.bumdesprototype.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {

    @Insert(entity = Checkout::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckout(checkout: Checkout)

    @Query("SELECT * FROM tb_checkout")
    fun getAllCheckout(): LiveData<List<Checkout>>

    @Query("SELECT * FROM tb_checkout WHERE id = :id")
    fun getCheckoutbyId(id: String) : LiveData<Checkout>

    @Query("SELECT * FROM tb_checkout WHERE idUser = :idUser")
    fun getCheckoutbyUser(idUser: String) : LiveData<List<Checkout>>

    @Delete(entity = Checkout::class)
    suspend fun deleteCheckout(checkout: Checkout)

    @Query("DELETE FROM tb_checkout")
    suspend fun clearCheckout()

}