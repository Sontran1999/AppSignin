package com.example.appsignin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.appsignin.Database.User
import com.example.appsignin.Database.UserRoomDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class SignupActivity() : AppCompatActivity(), CoroutineScope {
    private var mUserDB: UserRoomDatabase? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        var ac: ActionBar? = supportActionBar
        if (ac != null) {
            ac.hide()
        }

        var txt_signin = findViewById(R.id.txt_signin) as TextView
        txt_signin.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        mUserDB = UserRoomDatabase.getDatabase(this)


        btn_signup.setOnClickListener {
            val name1 = edt_name.text.toString()
            val email1 = edt_email2.text.toString()
            val password1 = edt_password.text.toString()
            launch {
                mUserDB?.getDao()?.insert(User(name = name1, email = email1, password = password1))

            }

            var data = Intent()
            data.putExtra("1", email1)
            data.putExtra("2", password1)
            setResult(RESULT_OK, data)
            finish()
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()


        }

    }
}
