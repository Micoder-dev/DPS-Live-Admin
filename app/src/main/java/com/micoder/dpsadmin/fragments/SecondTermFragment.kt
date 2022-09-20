package com.micoder.dpsadmin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.ui.UserInfoActivity

class SecondTermFragment : Fragment() {

    lateinit var uid: String

    lateinit var edtEnglish: EditText
    lateinit var edtMaths: EditText
    lateinit var edtCS: EditText
    lateinit var edtPractical: EditText

    lateinit var updateMarksBtn: Button

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second_term, container, false)

        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference

        val userInfo = (activity as UserInfoActivity)
        uid = userInfo.userUid

        edtEnglish = view.findViewById(R.id.edtEnglish)
        edtMaths = view.findViewById(R.id.edtMaths)
        edtCS = view.findViewById(R.id.edtCS)
        edtPractical = view.findViewById(R.id.edtPractical)
        updateMarksBtn = view.findViewById(R.id.updateMarksBtn)

        updateMarksBtn.setOnClickListener {
            updateMarks()
        }

        return view
    }

    private fun updateMarks() {
        // 👇 create a map of value
        val profileMap: HashMap<String, Any> = HashMap()
        profileMap["english"] = edtEnglish.text.toString()
        profileMap["maths"] = edtMaths.text.toString()
        profileMap["cs"] = edtCS.text.toString()
        profileMap["practical"] = edtPractical.text.toString()
        // 👇 write to database
        databaseReference.child("users").child(uid).child("results").child("Term2").updateChildren(profileMap).addOnSuccessListener {
            Toast.makeText(context, "Marks Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            val error = it.toString()
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }
}