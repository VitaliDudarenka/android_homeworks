package com.gmail.vitaliklancer.mytestapp.Homework7

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.gmail.vitaliklancer.mytestapp.R

class StudentInfoActivity : FragmentActivity() {
    companion object {
        val STUDENT_ID = "STUDENT_ID"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentinfo)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getLong(STUDENT_ID)
            val fragmentManager = supportFragmentManager
            val transactionFragment = fragmentManager.beginTransaction()
            transactionFragment.replace(R.id.containerDetails, StudentInfoFragment.getInstance(id), "")
            transactionFragment.commit()

        }

    }
}