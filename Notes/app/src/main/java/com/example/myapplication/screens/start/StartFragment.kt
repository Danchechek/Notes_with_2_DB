package com.example.myapplication.screens.start

import StartFragmentViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStartBinding
import com.example.myapplication.utilits.*
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    private var _binding:FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)


        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        if(AppPreference.getInitUser()){
            mViewModel.initDatabase(AppPreference.getTypeDB()){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else{
            initialization()
        }
    }

    private fun initialization() {
        if(mViewModel.liveData.value == true) visibilityFirebaseTrue()
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM){
                AppPreference.setInitUser(true)
                AppPreference.setTypeDB(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        mBinding.btnFirebase.setOnClickListener {
            if(mViewModel.liveData.value != true){
                visibilityFirebaseTrue()
                mViewModel.liveData.value = true
            } else{
                visibilityFirebaseFalse()
                mViewModel.liveData.value = false
            }
        }
        mBinding.btnLogin.setOnClickListener {
            val inputEmail = mBinding.inputEmail.text.toString()
            val inputPassword = mBinding.inputPassword.text.toString()
            if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                EMAIL = inputEmail
                PASSWORD = inputPassword
                mViewModel.initDatabase(TYPE_FIREBASE){
                    AppPreference.setInitUser(true)
                    AppPreference.setTypeDB(TYPE_FIREBASE)
                    when(FIREBASE_CONNECTION_TYPE){
                        REGISTRATION -> showToast(getString(R.string.showtoast_created_new_user))
                        LOGIN -> showToast(getString(R.string.show_toast_initialization_successful))
                    }
                    APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                }
            } else {
                showToast(getString(R.string.toast_wrong_enter_email))
            }
        }
    }

    fun visibilityFirebaseTrue(){
        mBinding.inputEmail.visibility = View.VISIBLE
        mBinding.inputPassword.visibility = View.VISIBLE
        mBinding.btnLogin.visibility = View.VISIBLE
    }

    fun visibilityFirebaseFalse(){
        mBinding.inputEmail.visibility = View.GONE
        mBinding.inputPassword.visibility = View.GONE
        mBinding.btnLogin.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}