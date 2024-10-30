package com.example.bottle_flip.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bottle_flip.databinding.DialogAgregarRetoBinding
import com.example.bottle_flip.model.challenge
import com.example.bottle_flip.viewmodel.ChallengeViewModel


class AddChangeDialog: DialogFragment() {

    private lateinit var binding: DialogAgregarRetoBinding
    private val inventoryViewModel: ChallengeViewModel by viewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())

        // Inflar el layout existente y obtener el binding
        binding = DialogAgregarRetoBinding.inflate(LayoutInflater.from(context))

        // Referenciar los elementos del layout usando el binding
        val editTextChallenge = binding.etDescripcion
        val buttonAdd = binding.btnAgregar

        // Configurar el botón si
        buttonAdd.setOnClickListener {
            dismiss()  // Cierra el diálogo
        }


        // Configurar el diálogo
        val alertDialog = dialog.setView(binding.root).setCancelable(true).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog.create()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
        //observerViewModel()


    }

    private fun controladores() {
        //validarDatos()
        binding.btnAgregar.setOnClickListener {
            saveInvetory()
        }
    }

    private fun saveInvetory(){
        val description = binding.etDescripcion.text.toString()
        val challenge = challenge(description = description )
        inventoryViewModel.savechallenge(challenge)
        Log.d("test22",challenge.toString())
        Toast.makeText(context,"Artículo guardado !!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()

    }
}
