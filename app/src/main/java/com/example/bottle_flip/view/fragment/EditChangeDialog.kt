package com.example.bottle_flip.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
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
        dialog.setView(binding.root)  // Usar la raíz del binding
            .setTitle("Editar reto")  // Esto puede ir en el layout si lo prefieres
            .setCancelable(true)

        return dialog.create()
    }
}
