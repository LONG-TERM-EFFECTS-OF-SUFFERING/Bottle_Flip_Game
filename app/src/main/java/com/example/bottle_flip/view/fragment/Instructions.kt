package com.example.bottle_flip.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bottle_flip.R

import com.example.bottle_flip.databinding.InstructionsBinding

class Instructions : Fragment() {
    private lateinit var binding: InstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InstructionsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load gif winner using Glide
        Glide.with(this)
            .asGif()
            .load(R.drawable.win_animated)
            .into(binding.ivAnimatedWin)

        // Configuration of back button
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_instructions_to_game)
        }

        // Call to pause audio game function
        checkAndPauseBackgroundAudio()
    }

    // Declaration of pause audio game function
    private fun checkAndPauseBackgroundAudio() {
    }
}