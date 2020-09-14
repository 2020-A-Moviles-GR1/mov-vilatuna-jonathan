package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_registrar_departamento.*

class RegistrarDepartamento : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"

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
               val url = urlPrincipal + "/Departamento"

               val parametrosUsuario: List<Pair<String, String>> = listOf(
                   "nombreDepartamento" to "${txt_nom_dep.text}",
                   "ciudad" to "${txt_cui_dep.text}",
                   "estado" to "${estado}",
                   "numero" to "${txt_nun_dep.text.toString().toDouble()}",
                   "codDepartamento" to "${txt_cod_dep.text.toString().toInt()}"
               )

               url
                   .httpPost(parametrosUsuario)
                   .responseString { req, res, result ->
                       when (result) {
                           is Result.Failure -> {
                               val error = result.getException()
                               Log.i("http-klaxon", "Error: ${error}")
                           }
                           is Result.Success -> {
                               val usuarioString = result.get()
                               Log.i("http-klaon", "${usuarioString}")
                           }
                       }
                   }

               mostrarDepartamento()
           }
        }
        btn_cancelar_registrar_departamento.setOnClickListener{ boton ->
            finish()
        }
    }
    fun mostrarDepartamento(){
        val intentExplicito= Intent(
            this, MenuDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
}