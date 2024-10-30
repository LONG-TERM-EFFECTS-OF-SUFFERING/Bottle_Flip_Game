package com.example.bottle_flip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.example.bottle_flip.viewmodel.ChallengeViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddChallenge: FloatingActionButton
    private lateinit var challengeAdapter: ChallengeAdapter
    private lateinit var challengeViewModel: ChallengeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        challengeViewModel = ViewModelProvider(this).get(ChallengeViewModel::class.java)
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
        challengeAdapter = ChallengeAdapter(mutableListOf<Challenge>(), supportFragmentManager)
        recyclerView.adapter = challengeAdapter

        fabAddChallenge = findViewById(R.id.fabAddChallenge)
        fabAddChallenge.setOnClickListener {
            val dialog = AgregarRetoDialog ()
            dialog.setListener { challenge ->
                challengeAdapter.addChallenge(challenge)
            }
            dialog.show(supportFragmentManager, "AgregarRetoFragment") // Muestra el fragmento
        }
    }
    /*private fun observerListInventory(){

        challengeViewModel.getListChallenge()
        challengeViewModel.listChallenge.observe(this){ listcChallenge ->
            val recycler = binding.recyclerview
            val layoutManager =LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengeAdapter(listInventory, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }

    }*/

}