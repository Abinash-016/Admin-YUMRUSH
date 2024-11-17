package com.example.adminyumrush

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminyumrush.adapter.PendingOrderAdapter
import com.example.adminyumrush.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding: ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val orderedCustomerName = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Mike Johnson"
        )
        val orderQuantity = arrayListOf(
            "Quantity",
            "Quantity",
            "Quantity"
        )
        val orderedImage = arrayListOf(
            R.drawable.menu1, R.drawable.menu2, R.drawable.menu3
        )
        val adapter = PendingOrderAdapter(orderedCustomerName, orderQuantity, orderedImage, this)
        binding.pendingRecyclerView.adapter = adapter
        binding.pendingRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.allItemBackButton.setOnClickListener {
            finish()
        }


    }

}