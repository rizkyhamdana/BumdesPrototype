package com.rizkyhamdana.bumdesprototype.ui.user.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizkyhamdana.bumdesprototype.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        title =  "Checkout"

    }
}