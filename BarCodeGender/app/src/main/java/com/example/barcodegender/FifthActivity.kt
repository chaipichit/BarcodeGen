package com.example.barcodegender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcodegender.base.BaseActivity

class FifthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        replaceFragment(FifthFragment.newInstance())
    }
}
