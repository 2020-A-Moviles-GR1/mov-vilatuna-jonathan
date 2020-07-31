package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")

        btn_opc_empleado.setOnClickListener{boton ->
            irOpncEmpleado()
        }
        btn_opc_departamento.setOnClickListener{boton ->
            irOpncDepartamento()
        }
    }


    fun irOpncEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irOpncDepartamento(){
        val intentExplicito= Intent(
            this, MenuDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
}
