package com.micoder.dpsadmin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.adapters.UsersAdapter
import com.micoder.dpsadmin.models.Users

class MainActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<Users>
    private lateinit var myAdapter: UsersAdapter

    lateinit var addFAB: FloatingActionButton
    lateinit var timeTable: FloatingActionButton
    var fabVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<Users>()
        getUserData()


        addFAB = findViewById(R.id.idFABAdd)
        timeTable = findViewById(R.id.idFABTimeTable)
        fabVisible = false
        addFAB.setOnClickListener {
            if (!fabVisible) {
                timeTable.show()
                timeTable.visibility = View.VISIBLE
                addFAB.setImageResource(R.drawable.ic_close)
                fabVisible = true
            } else {
                timeTable.hide()
                timeTable.visibility = View.GONE
                addFAB.setImageResource(R.drawable.ic_add)
                fabVisible = false
            }
        }
        timeTable.setOnClickListener {
            Toast.makeText(this@MainActivity, "Time Table clicked..", Toast.LENGTH_LONG).show()
        }

    }


    private fun getUserData() {

        myAdapter = UsersAdapter(this@MainActivity, userArrayList)

        dbref = FirebaseDatabase.getInstance().getReference("users")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    userArrayList.clear()
                    for (userSnapshot in snapshot.children){
                        val users = userSnapshot.getValue(Users::class.java)
                        userArrayList.add(users!!)
                    }
                    userRecyclerview.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.getActionView() as SearchView
        searchView.setQueryHint("Search email")
        searchView.setFocusable(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Users> = ArrayList()

        for (item in userArrayList) {
            if (item.name!!.lowercase().contains(text.lowercase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            myAdapter.filterList(filteredlist)
        }
    }

}