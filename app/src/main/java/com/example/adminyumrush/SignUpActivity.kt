package com.example.adminyumrush

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminyumrush.databinding.ActivitySignUpBinding
import com.example.adminyumrush.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var userName:String
    private lateinit var nameOfRestaurant:String
    private lateinit var database:DatabaseReference

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth= Firebase.auth
        database=Firebase.database.reference

        val locationList = arrayOf("Jaipur", "Odisha", "Bundi", "Sikar")
        val adapter = ArrayAdapter( this, R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)

        binding.createbutton.setOnClickListener {

            userName=binding.name.text.toString().trim()
            nameOfRestaurant=binding.restaurantName.text.toString().trim()
            email=binding.emailOrPhone.text.toString().trim()
            password=binding.signpass.text.toString().trim()
            if (userName.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }


        }
        binding.alreadyhavebutton.setOnClickListener {
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText( this,  "Account created successfully", Toast.LENGTH_SHORT).show()
                    saveUserData()
                    val intent = Intent( this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText( this,  "Account Creation Failed", Toast.LENGTH_SHORT).show()
                    Log.d( "Account",  "createAccount: Failure", task.exception)
                }
            }
        }

    // save data into database

    private fun saveUserData() {
        userName=binding.name.text.toString().trim()
        nameOfRestaurant=binding.restaurantName.text.toString().trim()
        email=binding.emailOrPhone.text.toString().trim()
        password=binding.signpass.text.toString().trim()

        val user=UserModel(userName,nameOfRestaurant,email,password)
        val userId=FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }
}