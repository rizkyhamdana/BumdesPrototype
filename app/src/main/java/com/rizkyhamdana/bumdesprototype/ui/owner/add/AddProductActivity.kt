package com.rizkyhamdana.bumdesprototype.ui.owner.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizkyhamdana.bumdesprototype.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}