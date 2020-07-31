package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_registrar_departamento.*

class RegistrarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_departamento)
        Log.i("Activity","OnCreate")

       /* btn_actualizar_departamento.setOnClickListener{ boton ->
            mostrarEmpleado()
        }
        btn_cancelar_actualizar_departamento.setOnClickListener{ boton ->
            finish()
        }*/
    }
    fun mostrarEmpleado(){
        val intentExplicito= Intent(
            this, MostrarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }
}