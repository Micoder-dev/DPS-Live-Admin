package com.micoder.dpsadmin.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.adapters.TabFragmentAdapter
import com.micoder.dpsadmin.databinding.ActivityUserInfoBinding
import com.micoder.dpsadmin.utils.InputFilterMinMax

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var userName: String
    lateinit var userEmail: String
    lateinit var userUid: String

    private lateinit var dialog: Dialog
    private lateinit var attendanceET: EditText
    private lateinit var updateAttendanceBtn: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        dbRef = FirebaseDatabase.getInstance().getReference("users")

        val bundle: Bundle? = intent.extras
        userName = bundle!!.getString("userName").toString()
        userEmail = bundle.getString("userEmail").toString()
        userUid = bundle.getString("userUid").toString()

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

        // Attendance
        binding.attendanceBtn.setOnClickListener {
            showAttendanceDialog()
        }

    }
    fun showAttendanceDialog() {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.attendance_dialog)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        attendanceET = dialog.findViewById(R.id.attendanceET)
        attendanceET.filters = arrayOf(InputFilterMinMax(0, 100))
        updateAttendanceBtn = dialog.findViewById(R.id.updateAttendanceBtn)

        dialog.show()

        dbRef.child(userUid).get().addOnSuccessListener {
            if (it.exists()) {
                if (it.child("attendance").value.toString() == "null") {
                    attendanceET.setText("")
                }else {
                    val percentage = it.child("attendance").value.toString()
                    attendanceET.setText(percentage)
                }
            }
            else {
                attendanceET.setText("")
            }
        }

        updateAttendanceBtn.setOnClickListener {
            val percent = attendanceET.text.toString()
            if (TextUtils.isEmpty(percent)) {
                attendanceET.requestFocus()
                attendanceET.error = "percentage required"
            }else {
                val values = HashMap<String, Any>()
                values["attendance"] = percent
                dbRef.child(userUid).updateChildren(values).addOnSuccessListener {
                    Toast.makeText(this, "Percentage Updated",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()
                }
                attendanceET.setText(percent)
                dialog.dismiss()
            }
        }
    }

}