package com.example.adminyumrush

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminyumrush.adapter.AddItemAdapter
import com.example.adminyumrush.databinding.ActivityAllItemBinding
import java.util.ArrayList

class AllItemActivity : AppCompatActivity() {
    private val binding:ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val menuFoodName = listOf("Burger", "sandwich", "momo", "item", "sandwich", "momo")
        val menuItemPrice = listOf("$5", "$6", "$8", "$9", "$10", "$10")
        val menuImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu3
        )
        val adapter=AddItemAdapter(ArrayList(menuFoodName),
            ArrayList(menuItemPrice), ArrayList
        (menuImage)
        )
        binding.menuRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.menuRecyclerView.adapter=adapter
        binding.allItemBackButton.setOnClickListener{
            finish()
        }
    }
}