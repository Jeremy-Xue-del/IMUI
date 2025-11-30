package com.example.imui.friend

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imui.R
import com.example.imui.databinding.ActivityFriendBinding

class FriendActivity : AppCompatActivity() {
    lateinit var binding : ActivityFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}