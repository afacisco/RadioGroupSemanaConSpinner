package com.juanfra.radiogroupsemanaconspinner

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
Autor: Juan Francisco Sánchez González
Fecha: 08/10/2024
Clase: Actividad que recoge el texto (día) del RadioButton pulsado y la opción marcada en el listado (spinner)
por el usuario y lo muestra en el mensaje.
*/

class MainActivity : AppCompatActivity() {

    // Objetos que van a instanciar los controles básicos que se van a utilizar
    lateinit var mensaje: TextView
    lateinit var listado: Spinner
    lateinit var rgSemana: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initControles()
        initListado()
        mostrarMensaje()
    }

    // Se instancian los controles TextView y RadioGroup
    private fun initControles() {
        mensaje = findViewById(R.id.tvMensajeBienvenida)
        rgSemana = findViewById(R.id.rgSemana)
    }

    // Se instancia el Spinner y se le asigna el adaptador
    private fun initListado() {
        listado = findViewById(R.id.spinner)
        // Datos del listado
        val datosListado: Array<String> = arrayOf("Mañana", "Tarde", "Noche")
        listado.adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, datosListado)
        // Se selecciona un elemento por defecto
        listado.setSelection(0)
    }

    // Listener del RadioGroup para cambiar el mensaje del TextView
    private fun mostrarMensaje() {
        rgSemana.setOnCheckedChangeListener { group, checkedId ->
            val rbSeleccionado: RadioButton = findViewById(checkedId)
            // Concatenación de strings utilizando los recursos
            mensaje.setText(getString(R.string.mensajeSaludo, rbSeleccionado.text, listado.selectedItem))
        }
    }
}