package com.micoder.dpsadmin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.micoder.dpsadmin.R
import com.micoder.dpsadmin.models.Users
import com.micoder.dpsadmin.ui.UserInfoActivity

class UsersAdapter(private var context: Context, private var userList : ArrayList<Users>) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent,false)
        return UsersViewHolder(itemView)
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<Users>) {
        userList = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.name.text = currentitem.name
        holder.email.text = currentitem.email

        holder.cardView.setOnClickListener {
            //Toast.makeText(context, currentitem.email, Toast.LENGTH_SHORT).show()

            val userName: String = currentitem.name.toString()
            val userEmail: String = currentitem.email.toString()
            val userUid: String = currentitem.uid.toString()

            val intent = Intent(context, UserInfoActivity::class.java)
            intent.putExtra("userName", userName)
            intent.putExtra("userEmail", userEmail)
            intent.putExtra("userUid", userUid)

            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UsersViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.tvName)
        val email : TextView = itemView.findViewById(R.id.tvEmail)
        val cardView : CardView = itemView.findViewById(R.id.cardview)
    }

}