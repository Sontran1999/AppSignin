package com.example.appsignin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.appsignin.Database.User
import com.example.appsignin.Database.UserRoomDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    View.OnClickListener
    val validateEmail: String= "[a-zA-Z0-9]+@[a-z]+\\.[a-z]+"
    val validatePass: String= "[a-zA-Z0-9]{0,18}"
    val LAUNCH_SECOND_ACTIVITY=1
    var userDB: UserRoomDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
        setContentView(R.layout.activity_main)

        val ac: ActionBar? = supportActionBar
        if (ac != null) {
            ac.hide()
        }
        userDB= UserRoomDatabase.getDatabase(this)
        tv_signup.setOnClickListener(this)
        btn_signin.setOnClickListener(this)
        tv_forgot.setOnClickListener(this)


        edt_pass.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if(edt_pass?.text.toString().length>17){
                        edt_pass.setText(edt_pass?.text.toString().substring(0,edt_pass?.text.toString().length-1))
                        edt_pass.setSelection(edt_pass?.text.toString().length)
                        edt_pass.error="không vượt 17"
                    }
//                    val content = edt_pass?.text.toString()
//                    edt_pass?.error = if (content.length <18 ) null else "Không được vượt quá 18 ký tự"
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== LAUNCH_SECOND_ACTIVITY){
            if (resultCode== Activity.RESULT_OK){
                edt_email.setText(data?.getStringExtra("1"))
                edt_pass.setText(data?.getStringExtra("2"))
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_signin->{
                val pass: String = edt_pass.text.toString()
                val email: String = edt_email.text.toString()

                if (email.isEmpty()) {
                    edt_email.error = "Email không được để trông"
                }
                else if(!email.matches(validateEmail.toRegex())){
                    edt_email.error = "Email sai"
                }
                else if(pass.isEmpty()){
                    edt_pass.error ="Password không được để trống"
                }
                else if(!pass.matches(validatePass.toRegex()))
                {
                    edt_pass.error="Password không được vượt quá 18 ký tự"
                }
                else{
                    var userEmail:User?= userDB?.getDao()?.findUserByEmail(edt_email.text.toString())
                    println(userEmail.toString()+", "+edt_email.text.toString())

                    if (userEmail!=null){
                        var userPass:User?= userDB?.getDao()?.findUserByPass(edt_pass.text.toString())
                        if (userPass!=null){
                            val intent: Intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                        }
                        else{
                            edt_pass.setText("")
                            Toast.makeText(this,"Sai mật khẩu", Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        edt_email.setText("")
                        Toast.makeText(this,"Sai email", Toast.LENGTH_LONG).show()
                    }

                }
            }
            R.id.tv_signup->{
                val intent: Intent = Intent(this, SignupActivity::class.java)
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY)
            }
            R.id.tv_forgot->{
                val intent3= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
                startActivity(intent3)
            }
        }
    }
    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
    fun println(msg: String){
        Log.d("Activity State ",msg)
    }
}
