package com.micoder.dpsadmin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.adapters.TabFragmentAdapter

class UserInfoActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var userName: String
    lateinit var userEmail: String
    lateinit var userUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val bundle :Bundle? =intent.extras
        userName = bundle!!.getString("userName").toString()
        userEmail = bundle!!.getString("userEmail").toString()
        userUid = bundle!!.getString("userUid").toString()

        val name = findViewById<TextView>(R.id.userName)
        val email = findViewById<TextView>(R.id.userEmail)
        //Toast.makeText(this, "$userName $userEmail $userCoins $userImage", Toast.LENGTH_LONG).show()
        name.setText(userName)
        email.setText(userEmail)

        title = "User Details"
        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Term 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Term 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Term 3"))
        tabLayout.addTab(tabLayout.newTab().setText("Term 4"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabFragmentAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}