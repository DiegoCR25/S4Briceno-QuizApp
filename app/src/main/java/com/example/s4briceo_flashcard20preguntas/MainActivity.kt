package com.example.s4briceo_flashcard20preguntas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)

        // Para conectar a la siguiente pantalla
        button.setOnClickListener {
            val intent = Intent(this, PreguntasActivity2::class.java)
            startActivity(intent)
        }
    }
}
