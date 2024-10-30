package com.example.bottle_flip.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.bottle_flip.databinding.DeleteChallengeDialogBinding




class DeleteChangeDialog: DialogFragment() {

    private lateinit var binding: DeleteChallengeDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())

        // Inflar el layout existente y obtener el binding
        binding = DeleteChallengeDialogBinding.inflate(LayoutInflater.from(context))

        // Referenciar los elementos del layout usando el binding
        val editTextChallenge = binding.deleteDescription
        val buttonSi = binding.btnSi
        val buttonNo = binding.btnNo

        // Configurar el botón si
        buttonSi.setOnClickListener {
            dismiss()  // Cierra el diálogo
        }

        // Configurar el botón no
        buttonNo.setOnClickListener {
            dismiss()  // Cierra el diálogo
        }

        // Configurar el diálogo
        val alertDialog = dialog.setView(binding.root).setCancelable(true).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog.create()
    }
}
