package com.example.abhinayvarma.task.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.abhinayvarma.task.R
import com.example.abhinayvarma.task.databinding.RowItemBinding
import com.example.abhinayvarma.task.model.RowData


class ItemsAdapter(var context: Context, var list: ArrayList<RowData>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        return ViewHolder(DataBindingUtil.inflate(layoutInflator, R.layout.row_item, parent, false))
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        var viewHolder = holder
        viewHolder.binding.tvTitle.text = list[position].title
        viewHolder.binding.tvHeading.text = list[position].description
        if(!(list[position].imageHref.isNullOrEmpty())) {
            loadImage(context, viewHolder.binding.ivMain, list[position].imageHref)
        }else{

            val param = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f
            )
            viewHolder.binding.tvHeading.setLayoutParams(param)
        }


    }

    inner class ViewHolder(var binding : RowItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun loadImage(context: Context,view: ImageView, url: String) {
        Glide.with(context)
                .load(url)
                .into(view)
    }


}