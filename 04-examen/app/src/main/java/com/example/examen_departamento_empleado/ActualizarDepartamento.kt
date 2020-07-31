package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_actualizar_departamento.*

class ActualizarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_departamento)

        Log.i("Activity","OnCreate")

        btn_actualizar_departamento.setOnClickListener{ boton ->
            mostrarDepartamentoActualizado()
        }
        btn_cancelar_actualizar_departamento.setOnClickListener{ boton ->
            finish()
        }
    }

    fun mostrarDepartamentoActualizado(){
//////////************************/////////////////
    }
}