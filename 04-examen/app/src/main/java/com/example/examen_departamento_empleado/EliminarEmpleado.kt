package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_eliminar_empleado.*

class EliminarEmpleado : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_empleado)
        Log.i("Activity","OnCreate")
        eliminarEmpleado()
        btn_eliminar_empleado.setOnClickListener{ boton ->
            if(txt_indice.text.toString().equals("")){
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
                val url = urlPrincipal + "/Empleado"

                val parametrosUsuario: List<Pair<String, String>> = listOf(
                    "id" to "${txt_indice.text.toString()}"
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
            Empleado.delete(txt_indice.text.toString().toInt())
                mostrarEmpleado()
            }
        }
        btn_cancelar_eliminacion.setOnClickListener{ boton ->
            finish()
        }
    }

    fun eliminarEmpleado(){
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            EmpleadoHTTP.muestra()
        )//lista
        lv_eliminar.adapter = adaptador
        lv_eliminar
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            adaptador.notifyDataSetChanged()

        }
    }

    fun mostrarEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }
}