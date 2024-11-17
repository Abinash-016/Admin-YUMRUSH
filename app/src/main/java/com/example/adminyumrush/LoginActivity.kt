package com.example.adminyumrush

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminyumrush.databinding.ActivityLoginBinding
import com.example.adminyumrush.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var userName:String?=null
    private var nameOfRestaurant:String?=null
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        auth= Firebase.auth
        database= Firebase.database.reference
        //google signin
        googleSignInClient=GoogleSignIn.getClient(this, googleSignInOptions)

        binding.loginbutton.setOnClickListener {

            email=binding.emailAddress.text.toString().trim()
            password=binding.editTextTextPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }
        }
        binding.googleButton.setOnClickListener{
            val signIntent=googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }
        binding.donthavebutton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun createAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val user=auth.currentUser
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                updateUi(user)
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        Toast.makeText(this,"Create User & Login Successful",Toast.LENGTH_SHORT).show()
                        saveUserData()
                        updateUi(user)
                    }else{
                        Toast.makeText( this, "Authentication failed", Toast.LENGTH_SHORT).show()
                        Log.d("Account","CreateUserAccount:Authentication failed",task.exception)
                    }
                }
                }
         }


    }

    private fun saveUserData() {
        email=binding.emailAddress.text.toString().trim()
        password=binding.editTextTextPassword.text.toString().trim()

        val user = UserModel(userName,nameOfRestaurant,email, password)
        val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {  String
            database.child("user").child(it).setValue(user)
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful) {
                val account = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken,  null)
                auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        // successfully sign in with Google
                        Toast.makeText( this,"Successfully sign-in with Google", Toast.LENGTH_SHORT).show()
                        updateUi(null)
                    } else {
                        Toast.makeText( this, "Google Sign-in failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else {
            Toast.makeText( this, "Google Sign-in failed", Toast.LENGTH_SHORT).show()
        }
    }
}