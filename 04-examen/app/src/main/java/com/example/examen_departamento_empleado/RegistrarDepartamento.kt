package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_departamento.*

class RegistrarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_departamento)
        Log.i("Activity","OnCreate")

       btn_registrar_departamento.setOnClickListener{ boton ->
           var estado = this.estado_depa.isChecked
           if (txt_nom_dep.text.toString().equals("") ||
               txt_cui_dep.text.toString().equals("") ||
               txt_nun_dep.text.toString().equals("") ||
               txt_cod_dep.text.toString().equals("")) {
               Toast.makeText(this, "Ha dejado campos vacios",
                   Toast.LENGTH_LONG).show();
           }else {
               Departamento.insertarDatos(
                   txt_nom_dep.text.toString(), txt_cui_dep.text.toString(),
                   estado, txt_nun_dep.text.toString().toDouble(),
                   txt_cod_dep.text.toString().toInt()
               )
               mostrarDepartamento()
           }
        }
        btn_cancelar_registrar_departamento.setOnClickListener{ boton ->
            finish()
        }
    }
    fun mostrarDepartamento(){
        val intentExplicito= Intent(
            this, MostrarDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
}