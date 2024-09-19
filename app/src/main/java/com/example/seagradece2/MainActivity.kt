// Descripción: Programa que interactua con la app y reproduce musica.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 15/09/2024
// Última modificación: 18/09/2024
package com.example.seagradece2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner


class MainActivity : Activity() {
    private lateinit var spinner: Spinner
    private lateinit var selectButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.audioSpinner)
        selectButton = findViewById(R.id.selectButton)

        val audios = arrayOf("Canción Regueton", "Balada", "Rock Clásico", "Cumbia", "Electrónica")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, audios)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        selectButton.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("selectedAudio", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }
}