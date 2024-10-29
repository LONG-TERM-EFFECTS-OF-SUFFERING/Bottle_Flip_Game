// Archivo: app/src/main/java/com/example/bottle_flip/AgregarRetoDialog.kt
package com.example.bottle_flip

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText

class AgregarRetoDialog(context: Context, private val listener: (Reto) -> Unit) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_agregar_reto)

        val btnAgregar: Button = findViewById(R.id.btnAgregar)
        val etDescripcion: EditText = findViewById(R.id.etDescripcion)

        btnAgregar.setOnClickListener {
            val descripcion = etDescripcion.text.toString()
            if (descripcion.isNotEmpty()) {
                listener(Reto(descripcion))
                dismiss()
            }
        }
    }
}