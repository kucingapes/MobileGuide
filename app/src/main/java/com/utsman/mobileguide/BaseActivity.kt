/*
 * BaseActivity.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/20/18 9:18 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = window.decorView
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }
    }
}