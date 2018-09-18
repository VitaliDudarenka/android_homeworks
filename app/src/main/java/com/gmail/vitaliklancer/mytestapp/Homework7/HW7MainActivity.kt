package com.gmail.vitaliklancer.mytestapp.Homework7

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.gmail.vitaliklancer.mytestapp.R
import android.widget.FrameLayout


class HW7MainActivity : FragmentActivity(), StudentListFragment.OnFragmentInteractionListener {
    private var portRotate: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw7main)
        if (findViewById<FrameLayout>(R.id.containerDetails) != null) {
            portRotate = true
        }
        val fragmentManager = supportFragmentManager
        val transactionFragment = fragmentManager.beginTransaction()
        transactionFragment.replace(R.id.containerList, StudentListFragment.getInstance(), "")
        transactionFragment.addToBackStack(null)
        transactionFragment.commit()
    }

    override fun onFragmentInteraction(id: Long) {
        if (portRotate) {
            val fragmentManager = supportFragmentManager
            val transactionFragment = fragmentManager.beginTransaction()
            transactionFragment.replace(R.id.containerDetails, StudentInfoFragment.getInstance(id), "")
            transactionFragment.addToBackStack(null)
            transactionFragment.commit()
        } else {
            val intent = Intent(applicationContext, StudentInfoActivity::class.java)
            intent.putExtra(StudentInfoActivity.STUDENT_ID, id)
            Log.e("CCC", id.toString())
            startActivity(intent)
        }

    }

}
