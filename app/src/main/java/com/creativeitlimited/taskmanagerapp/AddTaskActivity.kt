package com.creativeitlimited.taskmanagerapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.creativeitlimited.taskmanagerapp.database.User
import com.creativeitlimited.taskmanagerapp.database.UserDao
import com.creativeitlimited.taskmanagerapp.database.UserDatabase
import com.creativeitlimited.taskmanagerapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddTaskBinding
    private lateinit var dao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tv=findViewById<TextView>(R.id.user_date)
        val cal=Calendar.getInstance()
        val  myYear=cal.get(Calendar.YEAR)
        val myMonth=cal.get(Calendar.MONTH)
        val day=cal.get(Calendar.DAY_OF_MONTH)

        tv.setOnClickListener{
            val datePickerDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                tv.text="Date:" + dayOfMonth +"/"+(month+1) +"/"+year
            },myYear,myMonth,day)
            datePickerDialog.show()
        }

        val time=findViewById<TextView>(R.id.user_time)
        val tim=Calendar.getInstance()
        val hour=tim.get(Calendar.HOUR_OF_DAY)
        val min=tim.get(Calendar.MINUTE)

        time.setOnClickListener{
            val timePickerDialog=TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{view, hourOfDay, minute ->
                time.text="Time:"+hourOfDay+":"+minute
            },hour,min,false)
            timePickerDialog.show()
        }


        val db= Room.databaseBuilder(applicationContext, UserDatabase::class.java,"Userdb")
            .allowMainThreadQueries().build()

        dao=db.getUserDao()

        binding.btnAdd.setOnClickListener{
            val title=binding.userTitle.text.toString()
            val description=binding.userDescription.text.toString()
            val date=binding.userDate.text.toString()
            val time=binding.userTime.text.toString()

           addUser(title,description,date,time)
        }
    }

    private fun addUser(title:String,description:String,date:String,time:String){
        val user= User(0,title,description,date,time)
        dao.addUser(user)
    }

}

