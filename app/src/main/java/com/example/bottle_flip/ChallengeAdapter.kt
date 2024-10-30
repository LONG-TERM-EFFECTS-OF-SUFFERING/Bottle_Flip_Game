// Archivo: app/src/main/java/com/example/bottle_flip/RetoAdapter.kt
package com.example.bottle_flip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager // Importa el FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottle_flip.view.fragment.EditChallengeDialog

data class Challenge(val descripcion: String)

class ChallengeAdapter(
    private val challenge: MutableList<Challenge>,
    private val fragmentManager: FragmentManager // Añadir el FragmentManager aquí
) : RecyclerView.Adapter<ChallengeAdapter.RetoViewHolder>() {

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
        val reto = challenge[position]
        holder.descripcion.text = reto.descripcion
        holder.btnEditar.setOnClickListener {
            val editChallengeDialog = EditChallengeDialog()
            // Mostrar el diálogo usando el fragmentManager del adaptador
            editChallengeDialog.show(fragmentManager, "EditChallengeDialog")
        }
        holder.btnEliminar.setOnClickListener {
            // Lógica para eliminar el reto
        }
    }

    override fun getItemCount(): Int = challenge.size

    fun addChallenge(challenge: Reto) {
       // challenge.add(0, challenge)
        notifyItemInserted(0)
    }
}
