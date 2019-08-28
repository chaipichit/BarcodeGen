package com.example.barcodegender.four

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcodegender.R
import com.example.barcodegender.base.BaseActivity

class FourActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four2)
        replaceFragment(FourFragment.newInstance())
    }
}
