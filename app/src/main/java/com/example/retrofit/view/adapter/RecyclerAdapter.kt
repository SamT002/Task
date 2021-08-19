package com.example.retrofit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemLayoutBinding
import com.example.retrofit.model.MyModel

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    private var myList = listOf<MyModel>()

    fun setList(myList: List<MyModel>){
        this.myList = myList
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            holder.itemView.setOnClickListener {
                if(binding.punchline.visibility == View.GONE){
                    binding.punchline.visibility = View.VISIBLE
                } else binding.punchline.visibility = View.GONE

            }
            with(myList[position]){
                binding.id.text = id.toString()
                binding.punchline.text = punchline
                binding.setup.text = setup
                binding.type.text = type

            }
        }

    }

    override fun getItemCount(): Int {
        return myList.size
    }
}