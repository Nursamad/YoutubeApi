package com.example.kotlin1lesson5.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1lesson5.databinding.ActivityDetailsBinding
import com.example.kotlin1lesson5.extentions.showToast

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("key")
        binding.tvTest.text = id
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
        showToast("Id: $id")
    }
}