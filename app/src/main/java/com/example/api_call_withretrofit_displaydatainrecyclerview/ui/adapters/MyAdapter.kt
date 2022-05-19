package com.example.api_call_withretrofit_displaydatainrecyclerview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_call_withretrofit_displaydatainrecyclerview.R
import com.example.api_call_withretrofit_displaydatainrecyclerview.model.MyDataItem

class MyAdapter(val context:Context,val userList:List<MyDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var textViewUserId:TextView = itemView.findViewById(R.id.textViewUserId)
        var textViewTitle :TextView = itemView.findViewById(R.id.textViewTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //bind the views in the recyclerView
        holder.textViewUserId.text = userList[position].userId.toString()
        holder.textViewTitle.text = userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}