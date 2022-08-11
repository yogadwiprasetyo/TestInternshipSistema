package com.yogaprasetyo.testinternshipsistema.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import com.yogaprasetyo.testinternshipsistema.core.utils.loadImage
import com.yogaprasetyo.testinternshipsistema.databinding.ItemSeafoodBinding

class MainAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<MealsListItem, MainAdapter.MainViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            ItemSeafoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val food = getItem(position) as MealsListItem
        holder.bind(food)
    }

    inner class MainViewHolder(private val binding: ItemSeafoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: MealsListItem) {
            val (strMealThumb, idMeal, strMeal) = food
            binding.apply {
                ivMainThumbnail.loadImage(itemView.context, strMealThumb)
                tvMainName.text = strMeal
            }

            idMeal?.let { id -> itemView.setOnClickListener { onClick(id) } }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MealsListItem>() {
            override fun areItemsTheSame(oldItem: MealsListItem, newItem: MealsListItem): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(
                oldItem: MealsListItem,
                newItem: MealsListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}