package com.example.bottle_flip.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class challenge(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String
)
