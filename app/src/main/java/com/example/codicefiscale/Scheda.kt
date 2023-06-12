package com.example.codicefiscale

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Scheda : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheda)
        val intent = intent
        val CF = intent.getStringExtra("CodiceFiscale")
        val Cognome = intent.getStringExtra("Cognome")
        val Nome = intent.getStringExtra("Nome")
        val Luogo = intent.getStringExtra("Luogo")
        val Provincia = intent.getStringExtra("Provincia")
        val Giorno = intent.getStringExtra("Giorno")
        val Mese = intent.getStringExtra("Mese")
        val Anno = intent.getStringExtra("Anno")
        val Genere = intent.getStringExtra("Genere")
        val Data = "$Giorno/$Mese/$Anno"

        val cf = findViewById<TextView>(R.id.CF)
        val cognome = findViewById<TextView>(R.id.Cognome)
        val nome = findViewById<TextView>(R.id.Nome)
        val luogo = findViewById<TextView>(R.id.Luogo)
        val provincia = findViewById<TextView>(R.id.Provincia)
        val data = findViewById<TextView>(R.id.data)
        val genere = findViewById<TextView>(R.id.Genere)

        cf.text = "$CF"
        cognome.text = "$Cognome"
        nome.text = "$Nome"
        luogo.text = "$Luogo"
        provincia.text = "$Provincia"
        genere.text = "$Genere"
        data.text = "$Data"

    }
}