package com.example.s4briceo_flashcard20preguntas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PreguntasActivity2 : AppCompatActivity() {

    private val preguntas = arrayOf(
        arrayOf("Pregunta 01: ¿El Ecosistema Android es Gratuito?", "Verdadero", "Falso", "Verdadero", "Falso", "Verdadero"),
        arrayOf("Pregunta 02: ¿Cual de estos no es un componente esencial en Android?", "Activity", "Service", "Broadcast Receiver", "View", "View"),
        arrayOf("Pregunta 03: ¿Que componente se usa para mostrar la interfaz de usuario en una app Android?", "Activity", "Service", "Content Provider", "Broadcast Receiver", "Activity"),
        arrayOf("Pregunta 04: ¿Cual componente recibe mensajes o eventos enviados por el sistema o por otras aplicaciones?", "Activity", "Content Provider", "Broadcast Receiver", "Service", "Broadcast Receiver"),
        arrayOf("Pregunta 05: ¿Cual componente se utiliza para comunicar la aplicacion con otras aplicaciones mediante datos?", "Broadcast Receiver", "Service", "Content Provider", "Activity", "Content Provider"),
        arrayOf("Pregunta 06: ¿Cual componente permite ejecutar tareas en segundo plano sin interfaz de usuario?", "Activity", "Content Provider", "Broadcast Receiver", "Service", "Service"),
        arrayOf("Pregunta 07: ¿Cual de estos no es un archivo necesario para definir un Activity en Android Studio?", "activity_main.xml", "MainActivity.java/kotlin", "AndroidManifest.xml", "build.gradle", "build.gradle"),
        arrayOf("Pregunta 08: ¿Que componenten no se debe usar para mostrar notificaciones al usuario?", "Service", "Activity", "Broadcast Receiver", "Content Provider", "Content Provider"),
        arrayOf("Pregunta 09: ¿Cual archivo es obligatorio para registrar componentes en una App Android?", "AndroidManifest.xml", "build.gradle", "activity_main.xml", "strings.xml", "AndroidManifest.xml"),
        arrayOf("Pregunta 10: ¿Cual componente se activa cuando llega un SMS o un cambio en la red?", "Activity", "Content Provider", "Broadcast Receiver", "Service", "Broadcast Receiver"),

        arrayOf("Pregunta 11: ¿Cual componente no pertenece a la arquitectura basica de una App Android?", "Activity", "View", "Fragment", "Docker Container", "Docker Container"),
        arrayOf("Pregunta 12: ¿Cual de estos componentes se utiliza para dividir la interfaz de usuario en partes reutilizables?", "Activity", "Service", "Fragment", "Broadcast Receiver", "Fragment"),
        arrayOf("Pregunta 13: ¿Que componente no puede interactuar directamente con el usuario?", "Activity", "Service", "Fragment", "Dialog", "Service"),
        arrayOf("Pregunta 14: ¿Que archivo contiene las cadenas de texto usadas en la interfaz de la aplicacion?", "strings.xml", "AndroidManifest.xml", "build.gradle", "activity_main.xml", "strings.xml"),
        arrayOf("Pregunta 15: ¿Qué archivo contiene la configuración general de una app Android?", "build.gradle", "AndroidManifest.xml", "settings.xml", "MainActivity.kt", "AndroidManifest.xml"),
    )

    private var indicePregunta = 0
    private var respuestasCorrectas = 0
    private var respuestasIncorrectas = 0
    private var preguntaRespondida = false

    private lateinit var textViewPregunta: TextView
    private lateinit var btnAlternativa1: Button
    private lateinit var btnAlternativa2: Button
    private lateinit var btnAlternativa3: Button
    private lateinit var btnAlternativa4: Button
    private lateinit var btnSiguiente: Button
    private lateinit var btnMostrarResultados: Button
    private lateinit var textViewFeedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas2)

        textViewPregunta = findViewById(R.id.textViewPregunta1)
        btnAlternativa1 = findViewById(R.id.btnAlternativa1)
        btnAlternativa2 = findViewById(R.id.btnAlternativa2)
        btnAlternativa3 = findViewById(R.id.btnAlternativa3)
        btnAlternativa4 = findViewById(R.id.btnAlternativa4)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnMostrarResultados = findViewById(R.id.btnMostrarResultados)
        textViewFeedback = findViewById(R.id.textViewFeedback)

        mostrarPregunta(indicePregunta)

        btnAlternativa1.setOnClickListener { if (!preguntaRespondida) verificarRespuesta(btnAlternativa1.text.toString()) }
        btnAlternativa2.setOnClickListener { if (!preguntaRespondida) verificarRespuesta(btnAlternativa2.text.toString()) }
        btnAlternativa3.setOnClickListener { if (!preguntaRespondida) verificarRespuesta(btnAlternativa3.text.toString()) }
        btnAlternativa4.setOnClickListener { if (!preguntaRespondida) verificarRespuesta(btnAlternativa4.text.toString()) }

        btnSiguiente.setOnClickListener { siguientePregunta() }

        btnMostrarResultados.setOnClickListener { mostrarResultadosDialog() }
    }

    private fun mostrarPregunta(indice: Int) {
        if (indice >= preguntas.size) {
            // Ya no mostramos nada aquí para evitar que avance fuera del rango
            return
        }

        val preguntaActual = preguntas[indice]
        textViewPregunta.text = preguntaActual[0]
        btnAlternativa1.text = preguntaActual[1]
        btnAlternativa2.text = preguntaActual[2]
        btnAlternativa3.text = preguntaActual[3]
        btnAlternativa4.text = preguntaActual[4]

        preguntaRespondida = false
        habilitarBotones(true)

        textViewFeedback.visibility = TextView.GONE

        if (indice == preguntas.size - 1) {
            btnSiguiente.visibility = Button.GONE
            btnMostrarResultados.visibility = Button.VISIBLE
        } else {
            btnSiguiente.visibility = Button.VISIBLE
            btnMostrarResultados.visibility = Button.GONE
        }
    }

    private fun verificarRespuesta(respuesta: String) {
        val respuestaCorrecta = preguntas[indicePregunta][5]
        preguntaRespondida = true
        habilitarBotones(false)

        if (respuesta == respuestaCorrecta) {
            respuestasCorrectas++
            textViewFeedback.apply {
                text = "Correcto. La respuesta es: \"$respuestaCorrecta\"."
                setTextColor(resources.getColor(android.R.color.holo_green_dark, theme))
                visibility = TextView.VISIBLE
            }
        } else {
            respuestasIncorrectas++
            textViewFeedback.apply {
                text = "Incorrecto. La respuesta correcta es: \"$respuestaCorrecta\"."
                setTextColor(resources.getColor(android.R.color.holo_red_dark, theme))
                visibility = TextView.VISIBLE
            }
        }
    }

    private fun siguientePregunta() {
        if (!preguntaRespondida) {
            Toast.makeText(this, "Por favor, selecciona una respuesta primero", Toast.LENGTH_SHORT).show()
            return
        }
        indicePregunta++
        mostrarPregunta(indicePregunta)
    }

    private fun mostrarResultadosDialog() {
        val totalPreguntas = preguntas.size
        val mensaje = """
            Has respondido correctamente $respuestasCorrectas de $totalPreguntas preguntas.
            Respuestas incorrectas: $respuestasIncorrectas
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Resultados del Quiz")
            .setMessage(mensaje)
            .setCancelable(false)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }

    private fun habilitarBotones(habilitar: Boolean) {
        btnAlternativa1.isEnabled = habilitar
        btnAlternativa2.isEnabled = habilitar
        btnAlternativa3.isEnabled = habilitar
        btnAlternativa4.isEnabled = habilitar
    }
}
