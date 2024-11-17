package com.example.adminyumrush

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.adminyumrush.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.addMenu.setOnClickListener{
            val intent=Intent(this,AddItemActivity::class.java)
            startActivity(intent)
        }
        binding.alItemMenu.setOnClickListener{
            val intent=Intent(this,AllItemActivity::class.java)
            startActivity(intent)
        }
        binding.outForDeliveryButton.setOnClickListener{
            val intent=Intent(this,OutForDeliveryActivity::class.java)
            startActivity(intent)
        }
        binding.cardView6.setOnClickListener{
            val intent=Intent(this,AdminProfileActivity::class.java)
            startActivity(intent)
        }
        binding.cardView3.setOnClickListener{
            val intent=Intent(this,CreateUserActivity::class.java)
            startActivity(intent)
        }
        binding.pendingorder.setOnClickListener{
            val intent=Intent(this,PendingOrderActivity::class.java)
            startActivity(intent)
        }
    }
}