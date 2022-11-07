package com.micoder.dpsadmin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.adapters.TabFragmentAdapter
import com.micoder.dpsadmin.databinding.ActivityUserInfoBinding
import com.micoder.dpsadmin.fragments.AttendanceFragment

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var userName: String
    lateinit var userEmail: String
    var userUid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val bundle: Bundle? = intent.extras
        userName = bundle!!.getString("userName").toString()
        userEmail = bundle.getString("userEmail").toString()
        userUid = bundle.getString("userUid").toString()


        setUpResults()

        val attendanceFragment = AttendanceFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,attendanceFragment).disallowAddToBackStack().commit()

        showAD()

        binding.showAdBtn.setOnClickListener {
            showAD()
        }

    }

    private fun showAD() {
        var selectedClassItemIndex = 0
        val classes = arrayOf("Attendance", "Results")
        var selectedClass = classes[selectedClassItemIndex]

        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background)
            .setTitle("Select your choice")
            .setCancelable(false)
            .setSingleChoiceItems(classes, selectedClassItemIndex) { _, which ->
                selectedClassItemIndex = which
                selectedClass = classes[which]
            }
            .setPositiveButton("Select") { _, _ ->
                Toast.makeText(this, "$selectedClass Selected", Toast.LENGTH_SHORT).show()
                when(selectedClass) {
                    "Attendance" -> {
                        binding.fragmentContainer.visibility = View.VISIBLE
                        binding.tablayout.visibility = View.GONE
                        binding.viewPager.visibility = View.GONE
                    }
                    "Results" -> {
                        binding.fragmentContainer.visibility = View.GONE
                        binding.tablayout.visibility = View.VISIBLE
                        binding.viewPager.visibility = View.VISIBLE
                    }
                    else -> Toast.makeText(this, "Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
            .show()
    }

    private fun setUpResults() {
        val name = binding.userName
        val email = binding.userEmail
        name.setText(userName)
        email.setText(userEmail)

        //  title = "User Details"
        tabLayout = binding.tablayout
        viewPager = binding.viewPager
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