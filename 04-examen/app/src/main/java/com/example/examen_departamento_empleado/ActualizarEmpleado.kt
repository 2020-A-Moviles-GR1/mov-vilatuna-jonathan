package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_empleado.*
import java.sql.Date
import java.text.SimpleDateFormat

class ActualizarEmpleado : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_empleado)
        Log.i("Activity","OnCreate")
        mostrarEmpleadoActualizado()
        txt_act_fecha_emp.addTextChangedListener(object : TextWatcher {
            var sb : StringBuilder = StringBuilder("")
            var _ignore = false
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(_ignore){
                    _ignore = false
                    return
                }

                sb.clear()
                sb.append(if(s!!.length > 10){ s.subSequence(0,10) }else{ s })

                if(sb.lastIndex == 2){
                    if(sb[2] != '/'){
                        sb.insert(2,"/")
                    }
                } else if(sb.lastIndex == 5){
                    if(sb[5] != '/'){
                        sb.insert(5,"/")
                    }
                }

                _ignore = true
                txt_act_fecha_emp.setText(sb.toString())
                txt_act_fecha_emp.setSelection(sb.length)
            }
        })

        btn_actualizar_empleado.setOnClickListener{ boton ->
            var estado = this.estado_act_emp.isChecked
            if (id.text.toString().equals("") ||
                txt_act_cod_emp.text.toString().equals("") ||
                txt_act_nom_emp.text.toString().equals("") ||
                txt_act_sueldo_emp.text.toString().equals("") ||
                txt_act_fecha_emp.text.toString().equals("") ||
                txt_act_cod_dep_emp.text.toString().equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else {
                Empleado.udate(
                    id.text.toString().toInt(),
                    txt_act_cod_emp.text.toString().toInt(),
                    txt_act_nom_emp.text.toString(),
                    txt_act_sueldo_emp.text.toString().toDouble(),
                    fecha(txt_act_fecha_emp.text.toString()),
                    estado,
                    txt_act_cod_dep_emp.text.toString().toInt()
                )
                val url = urlPrincipal + "/Empleado/${id.text.toString().toInt()}"

                val parametrosUsuario: List<Pair<String, String>> = listOf(
                    "codEmpleado" to "${txt_act_cod_emp.text.toString().toInt()}",
                    "nombreEmpleado" to "${ txt_act_nom_emp.text.toString()}",
                    "sueldo" to "${txt_act_sueldo_emp.text.toString().toDouble()}",
                    "fechaNacimiento" to "${fecha(txt_act_fecha_emp.text.toString())}",
                    "estado" to "${estado}",
                    "codDepartamento" to "${txt_act_cod_dep_emp.text.toString().toInt()}"
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
                mostrarEmpleado()
            }
        }
        btn_cancelar_actualizar_empleado.setOnClickListener{ boton ->
            finish()
        }
    }

    fun mostrarEmpleadoActualizado(){
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            EmpleadoHTTP.muestra()
        )//lista

        lv_datos_actualizar.adapter = adaptador
        lv_datos_actualizar
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
    fun fecha(Date:String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return Date(sdf.parse(Date).time)
    }
}