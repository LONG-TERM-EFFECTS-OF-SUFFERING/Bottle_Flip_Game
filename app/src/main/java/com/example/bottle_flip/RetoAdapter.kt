// Archivo: app/src/main/java/com/example/bottle_flip/RetoAdapter.kt
package com.example.bottle_flip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Reto(val descripcion: String)

class RetoAdapter(private val retos: MutableList<Reto>) : RecyclerView.Adapter<RetoAdapter.RetoViewHolder>() {

    class RetoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icono: ImageView = itemView.findViewById(R.id.icono)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reto, parent, false)
        return RetoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetoViewHolder, position: Int) {
        val reto = retos[position]
        holder.descripcion.text = reto.descripcion
        holder.btnEditar.setOnClickListener {
            // Lógica para editar el reto
        }
        holder.btnEliminar.setOnClickListener {
            // Lógica para eliminar el reto
        }
    }

    override fun getItemCount(): Int = retos.size

    fun agregarReto(reto: Reto) {
        retos.add(0, reto)
        notifyItemInserted(0)
    }
}