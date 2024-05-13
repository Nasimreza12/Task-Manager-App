package com.creativeitlimited.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.creativeitlimited.taskmanagerapp.database.User
import com.creativeitlimited.taskmanagerapp.database.UserDao
import com.creativeitlimited.taskmanagerapp.database.UserDatabase
import com.creativeitlimited.taskmanagerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),TaskAdapter.HandleUserClick {
    private lateinit var binding: ActivityMainBinding

    val love="No love "

    private lateinit var dao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= Room.databaseBuilder(applicationContext, UserDatabase::class.java,"Userdb")
            .allowMainThreadQueries().build()


        dao=db.getUserDao()

        dao.getAllUser().apply {
            val taskAdapter=TaskAdapter(this@MainActivity,this)
            binding.rvUser.adapter=taskAdapter
        }

        binding.btnAddTask.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onEditClick(user: User) {
        val editIntent=Intent(this@MainActivity,AddTaskActivity::class.java)
        startActivity(editIntent)
    }

}