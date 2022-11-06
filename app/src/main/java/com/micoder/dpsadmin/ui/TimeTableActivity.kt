package com.micoder.dpsadmin.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import android.widget.TextView
import android.os.Bundle
import android.view.WindowManager
import com.micoder.dpsadmin.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.view.ViewGroup
import android.app.TimePickerDialog
import android.widget.Button
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class TimeTableActivity : AppCompatActivity() {
    private var dialog: Dialog? = null
    private var editText: EditText? = null
    private var editText2: EditText? = null
    private var delete_subject: Button? = null
    private var add_subject: Button? = null
    private var timetable_subject: String? = null
    private var timetable_staff: String? = null
    private var databaseStudents: DatabaseReference? = null
    private lateinit var listener: ValueEventListener
    private lateinit var Sun1: TextView
    private lateinit var Sun2: TextView
    private lateinit var Sun3: TextView
    private lateinit var Sun4: TextView
    private lateinit var Sun5: TextView
    private lateinit var Sun6: TextView
    private lateinit var Sun7: TextView
    private lateinit var Mon1: TextView
    private lateinit var Mon2: TextView
    private lateinit var Mon3: TextView
    private lateinit var Mon4: TextView
    private lateinit var Mon5: TextView
    private lateinit var Mon6: TextView
    private lateinit var Mon7: TextView
    private lateinit var Tue1: TextView
    private lateinit var Tue2: TextView
    private lateinit var Tue3: TextView
    private lateinit var Tue4: TextView
    private lateinit var Tue5: TextView
    private lateinit var Tue6: TextView
    private lateinit var Tue7: TextView
    private lateinit var Wed1: TextView
    private lateinit var Wed2: TextView
    private lateinit var Wed3: TextView
    private lateinit var Wed4: TextView
    private lateinit var Wed5: TextView
    private lateinit var Wed6: TextView
    private lateinit var Wed7: TextView
    private lateinit var Thu1: TextView
    private lateinit var Thu2: TextView
    private lateinit var Thu3: TextView
    private lateinit var Thu4: TextView
    private lateinit var Thu5: TextView
    private lateinit var Thu6: TextView
    private lateinit var Thu7: TextView
    private lateinit var Fri1: TextView
    private lateinit var Fri2: TextView
    private lateinit var Fri3: TextView
    private lateinit var Fri4: TextView
    private lateinit var Fri5: TextView
    private lateinit var Fri6: TextView
    private lateinit var Fri7: TextView
    private lateinit var Sat1: TextView
    private lateinit var Sat2: TextView
    private lateinit var Sat3: TextView
    private lateinit var Sat4: TextView
    private lateinit var Sat5: TextView
    private lateinit var Sat6: TextView
    private lateinit var Sat7: TextView
    private lateinit var timeslot1: TextView
    private lateinit var timeslot2: TextView
    private lateinit var timeslot3: TextView
    private lateinit var timeslot4: TextView
    private lateinit var timeslot5: TextView
    private lateinit var timeslot6: TextView
    private lateinit var timeslot7: TextView
    private lateinit var timeslot8: TextView
    private lateinit var timeslot9: TextView
    private lateinit var timeslot10: TextView
    private lateinit var timeslot11: TextView
    private lateinit var timeslot12: TextView
    private lateinit var timeslot13: TextView
    private lateinit var timeslot14: TextView

    var getClass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_time_table)

        showAD()

    }

    private fun showAD() {
        var selectedClassItemIndex = 0
        val classes = arrayOf("Class 1","Class 2","Class 3","Class 4","Class 5","Class 6","Class 7","Class 8","Class 9","Class 10","Class 11","Class 12")
        var selectedClass = classes[selectedClassItemIndex]

        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background)
            .setTitle("Languages")
            .setSingleChoiceItems(classes, selectedClassItemIndex) {dialog, which ->
                selectedClassItemIndex = which
                selectedClass = classes[which]
            }
            .setPositiveButton("OK") {dialog, which ->
                getData()
                Toast.makeText(this,selectedClass,Toast.LENGTH_LONG).show()
                getClass = selectedClass
            }
            .show()
    }

    private fun getData() {
        databaseStudents = FirebaseDatabase.getInstance().getReference("timetable")
        listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val snapshot = dataSnapshot.child(getClass)
                if (snapshot.child("timeslot1").getValue(String::class.java) != null) timeslot1.text = snapshot.child("timeslot1").getValue(String::class.java)
                if (snapshot.child("timeslot2").getValue(String::class.java) != null) timeslot2.text = snapshot.child("timeslot2").getValue(String::class.java)
                if (snapshot.child("timeslot3").getValue(String::class.java) != null) timeslot3.text = snapshot.child("timeslot3").getValue(String::class.java)
                if (snapshot.child("timeslot4").getValue(String::class.java) != null) timeslot4.text = snapshot.child("timeslot4").getValue(String::class.java)
                if (snapshot.child("timeslot5").getValue(String::class.java) != null) timeslot5.text = snapshot.child("timeslot5").getValue(String::class.java)
                if (snapshot.child("timeslot6").getValue(String::class.java) != null) timeslot6.text = snapshot.child("timeslot6").getValue(String::class.java)
                if (snapshot.child("timeslot7").getValue(String::class.java) != null) timeslot7.text = snapshot.child("timeslot7").getValue(String::class.java)
                if (snapshot.child("timeslot8").getValue(String::class.java) != null) timeslot8.text = snapshot.child("timeslot8").getValue(String::class.java)
                if (snapshot.child("timeslot9").getValue(String::class.java) != null) timeslot9.text = snapshot.child("timeslot9").getValue(String::class.java)
                if (snapshot.child("timeslot10").getValue(String::class.java) != null) timeslot10.text = snapshot.child("timeslot10").getValue(String::class.java)
                if (snapshot.child("timeslot11").getValue(String::class.java) != null) timeslot11.text = snapshot.child("timeslot11").getValue(String::class.java)
                if (snapshot.child("timeslot12").getValue(String::class.java) != null) timeslot12.text = snapshot.child("timeslot12").getValue(String::class.java)
                if (snapshot.child("timeslot13").getValue(String::class.java) != null) timeslot13.text = snapshot.child("timeslot13").getValue(String::class.java)
                if (snapshot.child("timeslot14").getValue(String::class.java) != null) timeslot14.text = snapshot.child("timeslot14").getValue(String::class.java)
                if (snapshot.child("Sunday1").getValue(String::class.java) != null) Sun1.text = snapshot.child("Sunday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday2").getValue(String::class.java) != null) Sun2.text = snapshot.child("Sunday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday3").getValue(String::class.java) != null) Sun3.text = snapshot.child("Sunday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday4").getValue(String::class.java) != null) Sun4.text = snapshot.child("Sunday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday5").getValue(String::class.java) != null) Sun5.text = snapshot.child("Sunday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday6").getValue(String::class.java) != null) Sun6.text = snapshot.child("Sunday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Sunday7").getValue(String::class.java) != null) Sun7.text = snapshot.child("Sunday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Sunday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday1").getValue(String::class.java) != null) Mon1.text = snapshot.child("Monday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday2").getValue(String::class.java) != null) Mon2.text = snapshot.child("Monday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday3").getValue(String::class.java) != null) Mon3.text = snapshot.child("Monday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday4").getValue(String::class.java) != null) Mon4.text = snapshot.child("Monday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday5").getValue(String::class.java) != null) Mon5.text = snapshot.child("Monday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday6").getValue(String::class.java) != null) Mon6.text = snapshot.child("Monday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Monday7").getValue(String::class.java) != null) Mon7.text = snapshot.child("Monday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Monday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday1").getValue(String::class.java) != null) Tue1.text = snapshot.child("Tuesday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday2").getValue(String::class.java) != null) Tue2.text = snapshot.child("Tuesday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday3").getValue(String::class.java) != null) Tue3.text = snapshot.child("Tuesday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday4").getValue(String::class.java) != null) Tue4.text = snapshot.child("Tuesday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday5").getValue(String::class.java) != null) Tue5.text = snapshot.child("Tuesday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday6").getValue(String::class.java) != null) Tue6.text = snapshot.child("Tuesday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Tuesday7").getValue(String::class.java) != null) Tue7.text = snapshot.child("Tuesday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Tuesday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday1").getValue(String::class.java) != null) Wed1.text = snapshot.child("Wednesday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday2").getValue(String::class.java) != null) Wed2.text = snapshot.child("Wednesday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday3").getValue(String::class.java) != null) Wed3.text = snapshot.child("Wednesday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday4").getValue(String::class.java) != null) Wed4.text = snapshot.child("Wednesday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday5").getValue(String::class.java) != null) Wed5.text = snapshot.child("Wednesday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday6").getValue(String::class.java) != null) Wed6.text = snapshot.child("Wednesday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Wednesday7").getValue(String::class.java) != null) Wed7.text = snapshot.child("Wednesday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Wednesday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday1").getValue(String::class.java) != null) Thu1.text = snapshot.child("Thursday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday2").getValue(String::class.java) != null) Thu2.text = snapshot.child("Thursday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday3").getValue(String::class.java) != null) Thu3.text = snapshot.child("Thursday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday4").getValue(String::class.java) != null) Thu4.text = snapshot.child("Thursday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday5").getValue(String::class.java) != null) Thu5.text = snapshot.child("Thursday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday6").getValue(String::class.java) != null) Thu6.text = snapshot.child("Thursday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Thursday7").getValue(String::class.java) != null) Thu7.text = snapshot.child("Thursday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Thursday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday1").getValue(String::class.java) != null) Fri1.text = snapshot.child("Friday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday2").getValue(String::class.java) != null) Fri2.text =snapshot.child("Friday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday3").getValue(String::class.java) != null) Fri3.text = snapshot.child("Friday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday4").getValue(String::class.java) != null) Fri4.text = snapshot.child("Friday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday5").getValue(String::class.java) != null) Fri5.text = snapshot.child("Friday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday6").getValue(String::class.java) != null) Fri6.text = snapshot.child("Friday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Friday7").getValue(String::class.java) != null) Fri7.text = snapshot.child("Friday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Friday7s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday1").getValue(String::class.java) != null) Sat1.text = snapshot.child("Saturday1").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday1s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday2").getValue(String::class.java) != null) Sat2.text = snapshot.child("Saturday2").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday2s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday3").getValue(String::class.java) != null) Sat3.text = snapshot.child("Saturday3").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday3s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday4").getValue(String::class.java) != null) Sat4.text = snapshot.child("Saturday4").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday4s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday5").getValue(String::class.java) != null) Sat5.text = snapshot.child("Saturday5").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday5s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday6").getValue(String::class.java) != null) Sat6.text = snapshot.child("Saturday6").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday6s").getValue(String::class.java) + ")"
                if (snapshot.child("Saturday7").getValue(String::class.java) != null) Sat7.text = snapshot.child("Saturday7").getValue(String::class.java).toString() + "\n(" + snapshot.child("Saturday7s").getValue(String::class.java) + ")"
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        dialog = Dialog(this@TimeTableActivity)
        dialog!!.setContentView(R.layout.custom_popup_dialog)
        //dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.ic_launcher_background));
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        editText = dialog!!.findViewById(R.id.po)
        editText2 = dialog!!.findViewById(R.id.staffName)
        add_subject = dialog!!.findViewById(R.id.add_subject)
        delete_subject = dialog!!.findViewById(R.id.delete_subject)


        //--------timeslots-------------------------------------
        timeslot1 = findViewById(R.id.timeslot1)
        timeslot2 = findViewById(R.id.timeslot2)
        timeslot3 = findViewById(R.id.timeslot3)
        timeslot4 = findViewById(R.id.timeslot4)
        timeslot5 = findViewById(R.id.timeslot5)
        timeslot6 = findViewById(R.id.timeslot6)
        timeslot7 = findViewById(R.id.timeslot7)
        timeslot8 = findViewById(R.id.timeslot8)
        timeslot9 = findViewById(R.id.timeslot9)
        timeslot10 = findViewById(R.id.timeslot10)
        timeslot11 = findViewById(R.id.timeslot11)
        timeslot12 = findViewById(R.id.timeslot12)
        timeslot13 = findViewById(R.id.timeslot13)
        timeslot14 = findViewById(R.id.timeslot14)
        timeslot1.setOnClickListener { pickTime(timeslot1, "timeslot1") }
        timeslot2.setOnClickListener { pickTime(timeslot2, "timeslot2") }
        timeslot3.setOnClickListener { pickTime(timeslot3, "timeslot3") }
        timeslot4.setOnClickListener { pickTime(timeslot4, "timeslot4") }
        timeslot5.setOnClickListener { pickTime(timeslot5, "timeslot5") }
        timeslot6.setOnClickListener { pickTime(timeslot6, "timeslot6") }
        timeslot7.setOnClickListener { pickTime(timeslot7, "timeslot7") }
        timeslot8.setOnClickListener { pickTime(timeslot8, "timeslot8") }
        timeslot9.setOnClickListener { pickTime(timeslot9, "timeslot9") }
        timeslot10.setOnClickListener { pickTime(timeslot10, "timeslot10") }
        timeslot11.setOnClickListener { pickTime(timeslot11, "timeslot11") }
        timeslot12.setOnClickListener { pickTime(timeslot12, "timeslot12") }
        timeslot13.setOnClickListener { pickTime(timeslot13, "timeslot13") }
        timeslot14.setOnClickListener { pickTime(timeslot14, "timeslot14") }
        Sun1 = findViewById(R.id.Sunday1)
        Sun2 = findViewById(R.id.Sunday2)
        Sun3 = findViewById(R.id.Sunday3)
        Sun4 = findViewById(R.id.Sunday4)
        Sun5 = findViewById(R.id.Sunday5)
        Sun6 = findViewById(R.id.Sunday6)
        Sun7 = findViewById(R.id.Sunday7)
        Mon1 = findViewById(R.id.Monday1)
        Mon2 = findViewById(R.id.Monday2)
        Mon3 = findViewById(R.id.Monday3)
        Mon4 = findViewById(R.id.Monday4)
        Mon5 = findViewById(R.id.Monday5)
        Mon6 = findViewById(R.id.Monday6)
        Mon7 = findViewById(R.id.Monday7)
        Tue1 = findViewById(R.id.Tuesday1)
        Tue2 = findViewById(R.id.Tuesday2)
        Tue3 = findViewById(R.id.Tuesday3)
        Tue4 = findViewById(R.id.Tuesday4)
        Tue5 = findViewById(R.id.Tuesday5)
        Tue6 = findViewById(R.id.Tuesday6)
        Tue7 = findViewById(R.id.Tuesday7)
        Wed1 = findViewById(R.id.Wednesday1)
        Wed2 = findViewById(R.id.Wednesday2)
        Wed3 = findViewById(R.id.Wednesday3)
        Wed4 = findViewById(R.id.Wednesday4)
        Wed5 = findViewById(R.id.Wednesday5)
        Wed6 = findViewById(R.id.Wednesday6)
        Wed7 = findViewById(R.id.Wednesday7)
        Thu1 = findViewById(R.id.Thursday1)
        Thu2 = findViewById(R.id.Thursday2)
        Thu3 = findViewById(R.id.Thursday3)
        Thu4 = findViewById(R.id.Thursday4)
        Thu5 = findViewById(R.id.Thursday5)
        Thu6 = findViewById(R.id.Thursday6)
        Thu7 = findViewById(R.id.Thursday7)
        Fri1 = findViewById(R.id.Friday1)
        Fri2 = findViewById(R.id.Friday2)
        Fri3 = findViewById(R.id.Friday3)
        Fri4 = findViewById(R.id.Friday4)
        Fri5 = findViewById(R.id.Friday5)
        Fri6 = findViewById(R.id.Friday6)
        Fri7 = findViewById(R.id.Friday7)
        Sat1 = findViewById(R.id.Saturday1)
        Sat2 = findViewById(R.id.Saturday2)
        Sat3 = findViewById(R.id.Saturday3)
        Sat4 = findViewById(R.id.Saturday4)
        Sat5 = findViewById(R.id.Saturday5)
        Sat6 = findViewById(R.id.Saturday6)
        Sat7 = findViewById(R.id.Saturday7)
        databaseStudents!!.addListenerForSingleValueEvent(listener)

        //click listerns -----------------------------------------------------------------
        Sun1.setOnClickListener { show(Sun1, "Sunday1") }
        Sun2.setOnClickListener { show(Sun2, "Sunday2") }
        Sun3.setOnClickListener { show(Sun3, "Sunday3") }
        Sun4.setOnClickListener { show(Sun4, "Sunday4") }
        Sun5.setOnClickListener { show(Sun5, "Sunday5") }
        Sun6.setOnClickListener { show(Sun6, "Sunday6") }
        Sun7.setOnClickListener { show(Sun7, "Sunday7") }
        Mon1.setOnClickListener { show(Mon1, "Monday1") }
        Mon2.setOnClickListener { show(Mon2, "Monday2") }
        Mon3.setOnClickListener { show(Mon3, "Monday3") }
        Mon4.setOnClickListener { show(Mon4, "Monday4") }
        Mon5.setOnClickListener { show(Mon5, "Monday5") }
        Mon6.setOnClickListener { show(Mon6, "Monday6") }
        Mon7.setOnClickListener { show(Mon7, "Monday7") }
        Tue1.setOnClickListener { show(Tue1, "Tuesday1") }
        Tue2.setOnClickListener { show(Tue2, "Tuesday2") }
        Tue3.setOnClickListener { show(Tue3, "Tuesday3") }
        Tue4.setOnClickListener { show(Tue4, "Tuesday4") }
        Tue5.setOnClickListener { show(Tue5, "Tuesday5") }
        Tue6.setOnClickListener { show(Tue6, "Tuesday6") }
        Tue7.setOnClickListener { show(Tue7, "Tuesday7") }
        Wed1.setOnClickListener { show(Wed1, "Wednesday1") }
        Wed2.setOnClickListener { show(Wed2, "Wednesday2") }
        Wed3.setOnClickListener { show(Wed3, "Wednesday3") }
        Wed4.setOnClickListener { show(Wed4, "Wednesday4") }
        Wed5.setOnClickListener { show(Wed5, "Wednesday5") }
        Wed6.setOnClickListener { show(Wed6, "Wednesday6") }
        Wed7.setOnClickListener { show(Wed7, "Wednesday7") }
        Thu1.setOnClickListener { show(Thu1, "Thursday1") }
        Thu2.setOnClickListener { show(Thu2, "Thursday2") }
        Thu3.setOnClickListener { show(Thu3, "Thursday3") }
        Thu4.setOnClickListener { show(Thu4, "Thursday4") }
        Thu5.setOnClickListener { show(Thu5, "Thursday5") }
        Thu6.setOnClickListener { show(Thu6, "Thursday6") }
        Thu7.setOnClickListener { show(Thu7, "Thursday7") }
        Fri1.setOnClickListener { show(Fri1, "Friday1") }
        Fri2.setOnClickListener { show(Fri2, "Friday2") }
        Fri3.setOnClickListener { show(Fri3, "Friday3") }
        Fri4.setOnClickListener { show(Fri4, "Friday4") }
        Fri5.setOnClickListener { show(Fri5, "Friday5") }
        Fri6.setOnClickListener { show(Fri6, "Friday6") }
        Fri7.setOnClickListener { show(Fri7, "Friday7") }
        Sat1.setOnClickListener { show(Sat1, "Saturday1") }
        Sat2.setOnClickListener { show(Sat2, "Saturday2") }
        Sat3.setOnClickListener { show(Sat3, "Saturday3") }
        Sat4.setOnClickListener { show(Sat4, "Saturday4") }
        Sat5.setOnClickListener { show(Sat5, "Saturday5") }
        Sat6.setOnClickListener { show(Sat6, "Saturday6") }
        Sat7.setOnClickListener { show(Sat7, "Saturday7") }
    }

    fun show(textView: TextView?, dayname: String) {
        timetable_subject = null
        timetable_staff = null
        dialog!!.show()
        add_subject!!.setOnClickListener {
            timetable_subject = editText!!.text.toString()
            timetable_staff = editText2!!.text.toString()
            if (timetable_subject!!.isEmpty()) {
                editText!!.requestFocus()
                editText!!.error = "subject name required"
            } else if (timetable_staff!!.isEmpty()) {
                editText2!!.requestFocus()
                editText2!!.error = "staff name required"
            } else {
                val values = HashMap<String, Any>()
                values[dayname] = timetable_subject!!
                values[dayname + "s"] = timetable_staff!!
                databaseStudents!!.child(getClass).updateChildren(values)
                textView!!.text = "$timetable_subject\n($timetable_staff)"
                editText!!.setText("")
                editText2!!.setText("")
                dialog!!.dismiss()
            }
        }
        delete_subject!!.setOnClickListener {
            databaseStudents!!.child(getClass).child(dayname).removeValue()
            databaseStudents!!.child(getClass).child(dayname + "s").removeValue()
            textView!!.text = ""
            dialog!!.dismiss()
        }
    }

    fun pickTime(textView: TextView?, slotname: String) {
        val timePickerDialog = TimePickerDialog(this@TimeTableActivity, { _, h, m ->
            val time = String.format(Locale.getDefault(), "%02d:%02d", h, m)
            val values = HashMap<String, Any>()
            values[slotname] = time
            databaseStudents!!.child(getClass).updateChildren(values)
            textView!!.text = time
        }, 0, 0, true)
        timePickerDialog.show()
    }

    override fun onPause() {
        databaseStudents!!.child(getClass).removeEventListener(listener)
        super.onPause()
    }
}