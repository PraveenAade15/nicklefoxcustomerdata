package com.example.nicketfoxcustomerlist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.nicketfoxcustomerlist.MainActivity
import com.example.nicketfoxcustomerlist.R

import com.example.nicketfoxcustomerlist.model.User
import com.example.nicketfoxcustomerlist.utils.SnackBarUtil
import com.example.nicketfoxcustomerlist.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_details.*


@AndroidEntryPoint
class AddDetailsActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)
        buttonClick()
        if (intent.getSerializableExtra("user") != null) {
            editUser()
        } else {
            btn_save.setOnClickListener {
                if (checkCredentials()) {
                    val userList = User(
                        name = et_Name.text.toString(),
                        email = et_Email.text.toString(),
                        phone = et_Phone.text.toString(),
                        rating = rating_bar.numStars
                    )
                    userViewModel.addUser(userList)
                    finish()
                }
            }
        }

    }

    private fun buttonClick() {
        back.setOnClickListener {
            finish()
        }
    }

    private fun checkCredentials(): Boolean {
        if (et_Name.text.toString().isEmpty() && et_Email.text.toString()
                .isEmpty() && et_Phone.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()

            return false
        }
        return true
    }

    private fun editUser() {
        val userList: User = intent.getSerializableExtra("user") as User
        et_Name.setText(userList.name.toString())
        et_Email.setText(userList.email.toString())
        et_Phone.setText(userList.phone.toString())

        btn_save.setOnClickListener {
            if (checkCredentials()) {
                userList.name = et_Name.text.toString()
                userList.email = et_Email.text.toString()
                userList.phone = et_Phone.text.toString()
                userViewModel.updateUser(userList)
                finishAffinity()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}