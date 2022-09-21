package com.example.myviewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myviewmodel.databinding.ItemListBinding
import com.example.myviewmodel.models.MyToDo

class MyListAdapter : ListAdapter<MyToDo,MyListAdapter.Vh>(DiffUtils()) {
    inner class Vh(val itemListBinding: ItemListBinding) : RecyclerView.ViewHolder(itemListBinding.root){
        fun onBind(myToDo: MyToDo){
            itemListBinding.text.text = myToDo.matn
            itemListBinding.text2.text = myToDo.sarlavha
            itemListBinding.text3.text = myToDo.holat
        }
    }
    class DiffUtils : DiffUtil.ItemCallback<MyToDo>(){
        override fun areItemsTheSame(oldItem: MyToDo, newItem: MyToDo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MyToDo, newItem: MyToDo): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

}