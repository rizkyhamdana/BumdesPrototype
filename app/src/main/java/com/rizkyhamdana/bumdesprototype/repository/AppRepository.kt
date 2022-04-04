package com.rizkyhamdana.bumdesprototype.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.util.Const.BASE_URL

class AppRepository {

    private val listOwner =  ArrayList<OwnerResponse>()

    fun getAllOwner() : List<OwnerResponse>{
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("account")
            .child("owner")
            .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val accountOwner = i.getValue(OwnerResponse::class.java) as OwnerResponse
                    listOwner.add(accountOwner)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listOwner
    }

}