package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu_empleado.*

class MenuEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_empleado)
        Log.i("Activity","OnCreate")

        bnt_reg_empleado.setOnClickListener{boton ->
            registrarEmpleado()
        }
        btn_act_empleado.setOnClickListener{boton ->
            actualizarEmpleado()
        }
        btn_elim_empleado.setOnClickListener{boton ->
            eliminarEmpleado()
        }
        btn_mostr_empleado.setOnClickListener{boton ->
            mostrarEmpleado()
        }
        btn_cancelar_empleado.setOnClickListener{boton ->
            finish()
        }

    }
    fun registrarEmpleado(){
        val intentExplicito= Intent(
            this, RegistrarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun actualizarEmpleado(){
        val intentExplicito= Intent(
            this, ActualizarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun eliminarEmpleado(){
        val intentExplicito= Intent(
            this, EliminarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun mostrarEmpleado(){
        val intentExplicito= Intent(
            this, MostrarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

}