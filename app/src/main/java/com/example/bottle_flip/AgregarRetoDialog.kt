// Archivo: app/src/main/java/com/example/bottle_flip/AgregarRetoFragment.kt
package com.example.bottle_flip

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bottle_flip.databinding.DialogAgregarRetoBinding
import com.example.bottle_flip.viewmodel.ChallengeViewModel
import com.example.bottle_flip.model.challenge

class AgregarRetoDialog : DialogFragment() { // Cambiado a DialogFragment
    private lateinit var binding: DialogAgregarRetoBinding
    private lateinit var challengeViewModel: ChallengeViewModel
    private var listener: ((Reto) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Opcional: quitar el título
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAgregarRetoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el ViewModel
        challengeViewModel = ViewModelProvider(requireActivity()).get(ChallengeViewModel::class.java)

        val btnAgregar: Button = binding.btnAgregar
        val etDescripcion: EditText = binding.etDescripcion

        btnAgregar.setOnClickListener {
            val descripcion = etDescripcion.text.toString()
            if (descripcion.isNotEmpty()) {
                listener?.invoke(Reto(descripcion)) // Llamar al listener
                saveChallenge()
                dismiss() // Cierra el diálogo
            } else {
                Toast.makeText(context, "Por favor, ingrese una descripción", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveChallenge() {
        val description = binding.etDescripcion.text.toString()
        val challenge = challenge(description = description)
        challengeViewModel.savechallenge(challenge)
        Log.d("111 good","good")
        Log.d("123", challenge.toString())
        Toast.makeText(context, "Artículo guardado !!", Toast.LENGTH_SHORT).show()
    }

    fun setListener(listener: (Reto) -> Unit) {
        this.listener = listener
    }
}
