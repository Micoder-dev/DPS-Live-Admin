package com.micoder.dpsadmin.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.micoder.dpsadmin.databinding.FragmentAttendanceBinding
import com.micoder.dpsadmin.ui.UserInfoActivity
import com.micoder.dpsadmin.utils.InputFilterMinMax

class AttendanceFragment : Fragment() {

    private lateinit var binding: FragmentAttendanceBinding

    private lateinit var attendanceET: EditText
    private lateinit var updateAttendanceBtn: Button

    private lateinit var dbRef: DatabaseReference

    lateinit var userUid: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAttendanceBinding.inflate(inflater,container,false)


        val userInfo = (activity as UserInfoActivity)
        userUid = userInfo.userUid


        dbRef = FirebaseDatabase.getInstance().getReference("users")

        attendanceET = binding.attendanceET
        updateAttendanceBtn = binding.updateAttendanceBtn
        attendanceET.filters = arrayOf(InputFilterMinMax(0, 100))

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
                    Toast.makeText(context, "Percentage Updated", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                attendanceET.setText(percent)
            }
        }



        return binding.root
    }
}