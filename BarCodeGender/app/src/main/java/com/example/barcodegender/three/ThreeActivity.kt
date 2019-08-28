package com.example.barcodegender.three

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcodegender.R
import com.example.barcodegender.base.BaseActivity

class ThreeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        replaceFragment(ThreeFragment.newInstance())
    }
}
