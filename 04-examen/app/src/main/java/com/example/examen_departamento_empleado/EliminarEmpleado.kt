package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_eliminar_empleado.*

class EliminarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_empleado)
        Log.i("Activity","OnCreate")

        btn_eliminar_empleado.setOnClickListener{ boton ->
            eliminarEmpleado()
        }
        btn_cancelar_eliminacion.setOnClickListener{ boton ->
            finish()
        }
    }

    fun eliminarEmpleado(){
//////////************************/////////////////
    }
}