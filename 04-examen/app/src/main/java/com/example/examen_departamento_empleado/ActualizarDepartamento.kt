package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_departamento.*

class ActualizarDepartamento : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_departamento)

        Log.i("Activity","OnCreate")
        mostrarDepartamentoActualizado()
        btn_actualizar_departamento.setOnClickListener{ boton ->
            var estado = this.estado_depa.isChecked

            if (txt_id.text.toString().equals("") ||
                txt_nom_dep.text.toString().equals("") ||
                txt_cui_dep.text.toString().equals("") ||
                txt_nun_dep.text.toString().equals("") ||
                txt_cod_dep.text.toString().equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
            Departamento.udate(txt_id.text.toString().toInt(),
                txt_nom_dep.text.toString(),
                txt_cui_dep.text.toString(),
                estado,
                txt_nun_dep.text.toString().toDouble(),
                txt_cod_dep.text.toString().toInt())
            mostrarDepartamentoActualizado()
            }
            val url = urlPrincipal + "/Departamento/${txt_id.text.toString().toInt()}"

            val parametrosUsuario: List<Pair<String, String>> = listOf(
                "nombreDepartamento" to "${ txt_nom_dep.text.toString()}",
                "ciudad" to "${txt_cui_dep.text.toString()}",
                "estado" to "${estado}",
                "numero" to "${txt_nun_dep.text.toString().toDouble()}",
                "codDepartamento" to "${txt_cod_dep.text.toString().toInt()}"
            )

            url
                .httpPut(parametrosUsuario)
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
        btn_cancelar_actualizar_departamento.setOnClickListener{ boton ->
            finish()
        }
    }

    fun mostrarDepartamentoActualizado(){
            val adaptador = ArrayAdapter(
                this, //contexto
                android.R.layout.simple_expandable_list_item_1, // nomre layout
                DepartamentoHTTP.muestra()
            )//lista

            lv_datos_actualizados.adapter = adaptador
            lv_datos_actualizados
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