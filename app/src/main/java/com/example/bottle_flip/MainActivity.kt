package com.example.bottle_flip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottle_flip.ChallengeAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddChallenge: FloatingActionButton
    private lateinit var challengeAdapter: ChallengeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Retos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            // Restablecer el audio de fondo si estaba en ON
            finish()
        }

        recyclerView = findViewById(R.id.recyclerViewRetos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        challengeAdapter = ChallengeAdapter(mutableListOf())
        recyclerView.adapter = challengeAdapter

        fabAddChallenge = findViewById(R.id.fabAddChallenge)
        fabAddChallenge.setOnClickListener {
            val dialog = AgregarRetoDialog(this) { reto ->
                challengeAdapter.addChallenge(reto)
            }
            dialog.show()
        }
    }
}