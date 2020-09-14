package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_mostrar_empleado.*


class MostrarEmpleado : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_empleado)
        Log.i("Activity","OnCreate")

        cargar()
        btn_mostrar.setOnClickListener{ boton ->
            mostrarLista()
        }
    EmpleadoHTTP.borrar()
        btn_cancelar_muestra_empleado.setOnClickListener{ boton ->
            irOpncEmpleado()
        }
    }


    fun mostrarLista() {
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            EmpleadoHTTP.muestra()
        )//lista

        lv_empleado.adapter = adaptador
        lv_empleado
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
        }
    }

    fun cargar(){
        val url = urlPrincipal + "/Empleado"
        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val empleado= Klaxon()
                            .converter(EmpleadoHTTP.ConverterPk).parseArray<EmpleadoHTTP>(data)
                        if (empleado != null) {
                            empleado.forEach {
                                Log.i(
                                    "http-klaxon",
                                    "${it}")
                                EmpleadoHTTP.guardar(it)
                            }
                        }
                    }

                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error: ${ex.message}")
                        EmpleadoHTTP.guardar("${ex.message}")
                    }

                }
            }
    }

    fun irOpncEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

 }

