package com.example.adminyumrush

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminyumrush.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    private val binding:ActivityAdminProfileBinding by lazy{
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.backButton.setOnClickListener{
            finish()
        }


        binding.name.isEnabled=false
        binding.email.isEnabled=false
        binding.password.isEnabled=false
        binding.address.isEnabled=false
        binding.mobno.isEnabled=false

        var isEnable=false

        binding.editButton.setOnClickListener{
            isEnable =! isEnable
            binding.name.isEnabled=isEnable
            binding.email.isEnabled=isEnable
            binding.password.isEnabled=isEnable
            binding.address.isEnabled=isEnable
            binding.mobno.isEnabled=isEnable

            if (isEnable){
                binding.name.requestFocus()
            }
        }

    }
}