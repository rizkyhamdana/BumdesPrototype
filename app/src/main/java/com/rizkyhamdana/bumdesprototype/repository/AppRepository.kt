package com.rizkyhamdana.bumdesprototype.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.util.Const.BASE_URL

class AppRepository {

    private val listOwner = MutableLiveData<List<OwnerResponse>>()
    private val listUser = MutableLiveData<List<UserResponse>>()

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
}