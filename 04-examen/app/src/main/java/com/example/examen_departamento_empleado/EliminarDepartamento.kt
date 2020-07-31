package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_eliminar_departamento.*

class EliminarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_departamento)
        Log.i("Activity","OnCreate")

        btn_eliminar_departamento.setOnClickListener{ boton ->
            eliminarEmpleado()
        }
        btn_cancelar_eliminacion_depa.setOnClickListener{ boton ->
            finish()
        }
    }

    fun eliminarEmpleado(){
//////////************************/////////////////
    }
}