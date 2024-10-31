package com.example.bottle_flip.view.viewholder

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottle_flip.databinding.ItemRetoBinding
import com.example.bottle_flip.model.challenge

class ChallengeViewHolder(binding: ItemRetoBinding, navController: NavController) :
    RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    val navController = navController
    fun setItemInventory(challenge: challenge) {
        bindingItem.descripcion.text = challenge.description

    }
}