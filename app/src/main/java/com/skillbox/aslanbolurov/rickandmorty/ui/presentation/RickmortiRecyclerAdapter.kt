package com.skillbox.aslanbolurov.rickandmorty.ui.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.aslanbolurov.rickandmorty.databinding.FragmentItemBinding
import com.skillbox.aslanbolurov.rickandmorty.model.Results


class CharacterHolder(
    val binding:FragmentItemBinding
): RecyclerView.ViewHolder(binding.root) {

}


class RickmortiRecyclerAdapter: PagingDataAdapter<Results,CharacterHolder>(ElementDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(
            FragmentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item=getItem(position)
        with(holder.binding){
            Glide.with(imageView.context).load(item?.imageUrl).into(imageView)
            tvName.text=item?.name
            tvStatusSpecies.text="${item?.status} - ${item?.species}"
            tvLastLocation.text=item?.location?.name


        }
    }

}


class ElementDiffUtilCallback : DiffUtil.ItemCallback<Results>(){
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem==newItem
    }

}
