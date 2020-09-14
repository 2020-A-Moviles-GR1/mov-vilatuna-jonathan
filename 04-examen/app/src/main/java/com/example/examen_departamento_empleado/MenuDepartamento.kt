package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu_departamento.*

class MenuDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_departamento)
        Log.i("Activity","OnCreate")



        bnt_reg_departamento.setOnClickListener{boton ->
            registrarDepartamento()
        }
        btn_act_departametnto.setOnClickListener{boton ->
            actualizarDepartamento()
        }
        btn_elim_departamento.setOnClickListener{boton ->
            eliminarDepartamento()
        }
        btn_mostr_departamento.setOnClickListener{boton ->
            mostrarDepartamento()
        }
        btn_cancelar_departamento.setOnClickListener{boton ->
            menuPrincipal()
        }
    }

    fun registrarDepartamento(){
        val intentExplicito= Intent(
            this, RegistrarDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun actualizarDepartamento(){
        val intentExplicito= Intent(
            this, ActualizarDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun eliminarDepartamento(){
        val intentExplicito= Intent(
            this, EliminarDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun mostrarDepartamento(){
        val intentExplicito= Intent(
            this, MostrarDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
    fun menuPrincipal(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }
}