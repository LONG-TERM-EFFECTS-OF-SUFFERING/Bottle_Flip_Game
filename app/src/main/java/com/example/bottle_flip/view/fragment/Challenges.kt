package com.example.bottle_flip.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottle_flip.R
import com.example.bottle_flip.databinding.ActivityChallengesBinding
import com.example.bottle_flip.view.fragment.EditChallengeDialog
import com.example.bottle_flip.view.fragment.DeleteChangeDialog
import com.example.bottle_flip.viewmodel.ChallengeViewModel
import com.example.bottle_flip.view.adapter.ChallengeAdapter
import androidx.recyclerview.widget.RecyclerView

class Challenge : Fragment() {
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button
    private var _binding: ActivityChallengesBinding? = null
    private val binding get() = _binding!!
    private val challengeViewModel: ChallengeViewModel by viewModels()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout y obtener el binding
        _binding = ActivityChallengesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observadorViewModel()
        // Configurar el RecyclerView y el FloatingActionButton
        val recyclerView = binding.recyclerViewRetos
        val fabAddChallenge = binding.fabAddChallenge

        // Configuración para el botón flotante "Agregar reto"
        fabAddChallenge.setOnClickListener {
            // Navegar al fragmento de agregar reto
            findNavController().navigate(R.id.action_challenges_to_addChangeDialog)
        }

        // Configurar los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observadorViewModel(){
        observerListInventory()
       // observerProgress()
    }

    private fun observerListInventory() {

        challengeViewModel.getListChallenge()
        challengeViewModel.listChallenge.observe(viewLifecycleOwner) { listChallenge ->
            val recycler = binding.recyclerViewRetos
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengeAdapter(listChallenge, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }


}
