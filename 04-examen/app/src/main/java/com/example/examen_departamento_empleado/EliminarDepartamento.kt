package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_eliminar_departamento.*
import kotlinx.android.synthetic.main.activity_registrar_departamento.*

class EliminarDepartamento : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_departamento)
        Log.i("Activity","OnCreate")
        eliminarDepartamento()

        btn_eliminar_departamento.setOnClickListener { boton ->
            if(txt_indice_dep.text.toString().equals("")){
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
                val url = urlPrincipal + "/Departamento"

                val parametrosUsuario: List<Pair<String, String>> = listOf(
                    "id" to "${txt_indice_dep.text.toString()}"
                )

                url
                    .httpDelete(parametrosUsuario)
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

           // Departamento.borrarEnCascade(txt_indice_dep.text.toString() + "],")
                mostrarDepartamento()
        }
        }
        btn_cancelar_eliminacion_depa.setOnClickListener{ boton ->
            finish()
        }
    }

    fun eliminarDepartamento(){
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            DepartamentoHTTP.muestra()
        )//lista
        lv_eliminar_dep.adapter = adaptador
        lv_eliminar_dep
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            adaptador.notifyDataSetChanged()
        }
    }

    fun mostrarDepartamento(){
        val intentExplicito= Intent(
            this, MenuDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
}