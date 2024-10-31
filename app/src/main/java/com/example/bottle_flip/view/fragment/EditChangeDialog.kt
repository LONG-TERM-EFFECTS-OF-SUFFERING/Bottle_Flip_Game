package com.example.bottle_flip.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.bottle_flip.databinding.EditChallengeDialogBinding




class EditChallengeDialog : DialogFragment() {

    private lateinit var binding: EditChallengeDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())

        // Inflar el layout existente y obtener el binding
        binding = EditChallengeDialogBinding.inflate(LayoutInflater.from(context))

        // Referenciar los elementos del layout usando el binding
        val editTextChallenge = binding.etDescripcionReto
        val buttonCancel = binding.btnCancelar
        val buttonSave = binding.btnGuardar

        // Configurar el botón Cancelar
        buttonCancel.setOnClickListener {
            dismiss()  // Cierra el diálogo
        }

        // Configurar el botón Guardar
        buttonSave.setOnClickListener {
            // Aquí puedes guardar los cambios en SQLite
            dismiss()  // Cierra el diálogo
        }

        // Configurar el diálogo
        val alertDialog = dialog.setView(binding.root).setCancelable(true).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog.create()
    }
}
