package com.rizkyhamdana.bumdesprototype.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.PopularResponse
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.util.Const.BASE_URL

class AppRepository {

    private val listOwner = MutableLiveData<List<OwnerResponse>>()
    private val listUser = MutableLiveData<List<UserResponse>>()
    private val listStand = MutableLiveData<List<StandResponse>>()
    private val listFoodPopular = MutableLiveData<List<PopularResponse>>()
    private val listDrinkPopular = MutableLiveData<List<PopularResponse>>()
    private val listSnackPopular = MutableLiveData<List<PopularResponse>>()

    fun getAllOwner(): LiveData<List<OwnerResponse>> {
        val owners = ArrayList<OwnerResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("account")
            .child("owner")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val accountOwner = i.getValue(OwnerResponse::class.java) as OwnerResponse
                        owners.add(accountOwner)
                        listOwner.postValue(owners)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listOwner
    }

    fun getAllUser(): LiveData<List<UserResponse>> {
        val users = ArrayList<UserResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("account")
            .child("user")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val accountUser = i.getValue(UserResponse::class.java) as UserResponse
                        users.add(accountUser)
                        listUser.postValue(users)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listUser

    }

    fun getFoodPopular(): LiveData<List<PopularResponse>>{
        val foods = ArrayList<PopularResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("food")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val food = i.getValue(PopularResponse::class.java) as PopularResponse
                        foods.add(food)
                        listFoodPopular.postValue(foods)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listFoodPopular
    }

    fun getDrinkPopular(): LiveData<List<PopularResponse>>{
        val drinks = ArrayList<PopularResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("drink")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val drink = i.getValue(PopularResponse::class.java) as PopularResponse
                        drinks.add(drink)
                        listDrinkPopular.postValue(drinks)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listDrinkPopular
    }

    fun getSnackPopular(): LiveData<List<PopularResponse>>{
        val snacks = ArrayList<PopularResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("snack")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val snack = i.getValue(PopularResponse::class.java) as PopularResponse
                        snacks.add(snack)
                        listSnackPopular.postValue(snacks)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listSnackPopular
    }

    fun getAllStand(): LiveData<List<StandResponse>>{
        val stands = ArrayList<StandResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("stand")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val stand = i.getValue(StandResponse::class.java) as StandResponse
                        stands.add(stand)
                        listStand.postValue(stands)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listStand
    }
}