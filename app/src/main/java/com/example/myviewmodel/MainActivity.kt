package com.example.myviewmodel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myviewmodel.ViewModel.MyViewModel
import com.example.myviewmodel.adapter.MyListAdapter
import com.example.myviewmodel.databinding.ActivityMainBinding
import com.example.myviewmodel.databinding.ItemDialogBinding
import com.example.myviewmodel.models.MyToDoPostRequest

class MainActivity : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel
    lateinit var myListAdapter: MyListAdapter
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myListAdapter = MyListAdapter()

        binding.rv.adapter = myListAdapter

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        myViewModel.getAllToDo().observe(this){
            myListAdapter.submitList(it)
            Log.d(TAG, "onCreate: $it")
        }

        binding.floating.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.btnSave.setOnClickListener {
                val holat = itemDialogBinding.edtHolat.text.toString()
                val matn = itemDialogBinding.edtMatn.text.toString()
                val sarlavha = itemDialogBinding.edtSarlavha.text.toString()
                val myToDoPostRequest = MyToDoPostRequest(holat,matn,  "muddat",sarlavha)
                myViewModel.addTodo(myToDoPostRequest)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            dialog.setView(itemDialogBinding.root)
            dialog.show()
        }
    }
}