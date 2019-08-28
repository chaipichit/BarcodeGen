package com.example.barcodegender.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcodegender.R
import com.example.barcodegender.base.BaseActivity

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        replaceFragment(SecondFragment.newInstance())
    }
}
