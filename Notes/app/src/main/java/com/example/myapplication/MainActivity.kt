package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utilits.APP_ACTIVITY
import com.example.myapplication.utilits.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding:ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_hots_fragment)
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)
        AppPreference.getPreference(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        //Исключаем возможность утечки памяти
        _binding = null
    }

}