package com.example.barcodegender.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcodegender.R
import com.example.barcodegender.base.BaseActivity

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        replaceFragment(DetailFragment.newInstance())
    }
}
