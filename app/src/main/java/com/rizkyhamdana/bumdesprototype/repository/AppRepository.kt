package com.rizkyhamdana.bumdesprototype.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.bumdesprototype.data.*
import com.rizkyhamdana.bumdesprototype.data.local.AppDao
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.util.Const.BASE_URL

class AppRepository(private val appDao: AppDao) {


    private val listOwner = MutableLiveData<List<OwnerResponse>>()
    private val listUser = MutableLiveData<List<UserResponse>>()
    private val listStand = MutableLiveData<List<StandResponse>>()
    private val listFoodPopular = MutableLiveData<List<ProdukResponse>>()
    private val listDrinkPopular = MutableLiveData<List<ProdukResponse>>()
    private val listSnackPopular = MutableLiveData<List<ProdukResponse>>()
    private val listFood = MutableLiveData<List<ProdukResponse>>()
    private val listDrink = MutableLiveData<List<ProdukResponse>>()
    private val listSnack = MutableLiveData<List<ProdukResponse>>()
    private val listOrder = MutableLiveData<List<OrderResponse>>()
    private val listChat = MutableLiveData<List<ChatResponse>>()
    private val user = MutableLiveData<UserResponse>()

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

    fun getUserbyEmail(email: String): LiveData<UserResponse> {
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("account")
            .child("user")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val accountUser = i.getValue(UserResponse::class.java) as UserResponse
                        if (email == accountUser.email){
                            user.postValue(accountUser)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return user

    }

    fun getFoodPopular(): LiveData<List<ProdukResponse>>{
        val foods = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("food")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val food = i.getValue(ProdukResponse::class.java) as ProdukResponse
                        foods.add(food)
                        listFoodPopular.postValue(foods)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listFoodPopular
    }

    fun getDrinkPopular(): LiveData<List<ProdukResponse>>{
        val drinks = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("drink")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val drink = i.getValue(ProdukResponse::class.java) as ProdukResponse
                        drinks.add(drink)
                        listDrinkPopular.postValue(drinks)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listDrinkPopular
    }

    fun getSnackPopular(): LiveData<List<ProdukResponse>>{
        val snacks = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("popular")
            .child("snack")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val snack = i.getValue(ProdukResponse::class.java) as ProdukResponse
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

    fun getFoodbyStand(stand: String): LiveData<List<ProdukResponse>>{
        val foods = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("stand")
            .child(stand)
            .child("food")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val food = i.getValue(ProdukResponse::class.java) as ProdukResponse
                        foods.add(food)
                        listFood.postValue(foods)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listFood
    }


    fun getDrinkbyStand(stand: String): LiveData<List<ProdukResponse>>{
        val drinks = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("stand")
            .child(stand)
            .child("drink")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val drink = i.getValue(ProdukResponse::class.java) as ProdukResponse
                        drinks.add(drink)
                        listDrink.postValue(drinks)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listDrink
    }

    fun getSnackbyStand(stand: String): LiveData<List<ProdukResponse>>{
        val snacks = ArrayList<ProdukResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("stand")
            .child(stand)
            .child("snack")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children) {
                        val snack = i.getValue(ProdukResponse::class.java) as ProdukResponse
                        snacks.add(snack)
                        listSnack.postValue(snacks)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        return listSnack
    }

    fun getAllOrder(id: String): LiveData<List<OrderResponse>>{
        val orders = ArrayList<OrderResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(BASE_URL)
        firebaseDb.getReference("order")
            .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val order = i.getValue(OrderResponse::class.java) as OrderResponse
                    if (id == order.idUser){
                        orders.add(order)
                        listOrder.postValue(orders)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return listOrder
    }

    fun getChatbyStand(stand: String, myId: String): LiveData<List<ChatResponse>>{
        val chats = ArrayList<ChatResponse>()
        val firebasDb = FirebaseDatabase.getInstance(BASE_URL)
        firebasDb.getReference("chat")
            .child(stand)
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        val chat = i.getValue(ChatResponse::class.java) as ChatResponse
                        if (chat.idReceiver == myId && chat.idSender == stand
                            || chat.idReceiver == stand && chat.idSender == myId){
                            chats.add(chat)
                            listChat.postValue(chats)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        return listChat
    }

    suspend fun insertCheckout(checkout: Checkout) = appDao.insertCheckout(checkout)
    suspend fun deleteCheckout(checkout: Checkout) = appDao.deleteCheckout(checkout)
    fun getAllCheckout(): LiveData<List<Checkout>> = appDao.getAllCheckout()
    fun getCheckoutbyId(id: String) : LiveData<Checkout> = appDao.getCheckoutbyId(id)
    fun getCheckoutbyUser(idUser: String) : LiveData<List<Checkout>> = appDao.getCheckoutbyUser(idUser)
    suspend fun clearCheckout() = appDao.clearCheckout()


}