package com.assingment.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.assingment.R
import com.assingment.listeners.FragmentSelectionListener
import com.assingment.ui.fragments.UserListFragment
import com.assingment.utils.FRAG_USER_LIST

abstract class BaseActivity : AppCompatActivity(), FragmentSelectionListener {

    private val TAG = BaseActivity::class.simpleName
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    var fragReqCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        fragmentManager = supportFragmentManager
    }

    override fun onBackPressed() {
        navigateBack()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right)
    }

    fun navigateBack() {
        if (::fragmentManager.isInitialized && fragmentManager.backStackEntryCount > 1) fragmentManager.popBackStack() else finish()
    }

    //Fragments
    fun getFragment(reqCode: Int, data: Bundle?): Fragment? {
        when (reqCode) {
            FRAG_USER_LIST -> return UserListFragment.newInstance(data)
            /*FRAG_USER_POST_DETAIL -> return UserPostDetailFragment.newInstance(data)*/
        }
        return null
    }
}