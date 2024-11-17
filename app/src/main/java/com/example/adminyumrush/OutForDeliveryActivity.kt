package com.example.adminyumrush

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminyumrush.adapter.DeliveryAdapter
import com.example.adminyumrush.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy{
        ActivityOutForDeliveryBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val customerName = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Mike Johnson",
        )
        val moneyStatus = arrayListOf(
            "Received",
            "NotReceived",
            "Pending",
        )
        val adapter= DeliveryAdapter (customerName, moneyStatus)
        binding.deliveryRecyclerView.adapter = adapter
        binding.deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.outForDelBackButton.setOnClickListener{
            finish()
        }

    }
}