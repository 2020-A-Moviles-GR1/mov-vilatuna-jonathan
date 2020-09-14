package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_empleado.*
import java.sql.Date
import java.text.SimpleDateFormat
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class RegistrarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val urlPrincipal = "http://192.168.56.1:1337"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_empleado)
        Log.i("Activity","OnCreate")

        txt_fecha_emp.addTextChangedListener(object : TextWatcher {
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
                txt_fecha_emp.setText(sb.toString())
                txt_fecha_emp.setSelection(sb.length)
            }
        })

        btn_registrar_empleado.setOnClickListener{ boton ->
            var estado = this.estado_emp.isChecked
            if (txt_cod_emp.text.toString().equals("") ||
                txt_nom_emp.text.toString().equals("") ||
                txt_sueldo.text.toString().equals("") ||
                txt_fecha_emp.text.toString().equals("") ||
                txt_code_depa_emp.text.toString().equals("")||
                txt_indice.text.toString().equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else {
                Empleado.insertarDatos(
                    txt_cod_emp.text.toString().toInt(),
                    txt_nom_emp.text.toString(),
                    txt_sueldo.text.toString().toDouble(),
                    fecha(txt_fecha_emp.text.toString()),
                    estado,
                    txt_code_depa_emp.text.toString().toInt()
                )
                val url = urlPrincipal + "/Empleado"

                val parametrosUsuario: List<Pair<String, String>> = listOf(
                    "codEmpleado" to "${txt_cod_emp.text.toString().toInt()}",
                    "nombreEmpleado" to "${txt_nom_emp.text.toString()}",
                    "sueldo" to "${txt_sueldo.text.toString().toDouble()}",
                    "fechaNacimiento" to "${fecha(txt_fecha_emp.text.toString())}",
                    "estado" to "${estado}",
                    "codDepartamento" to "${txt_code_depa_emp.text.toString().toInt()}",
                    "departamento" to "${txt_indice.text.toString().toInt()}"
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

                mostrarEmpleado()
            }
        }
        btn_cancelar_registro_empleado.setOnClickListener{ boton ->
            finish()
        }
    }
    fun mostrarEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun fecha(Date: String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return Date(sdf.parse(Date.toString()).time)
    }

}
