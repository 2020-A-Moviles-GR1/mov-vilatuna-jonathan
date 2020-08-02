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

class RegistrarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

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
                txt_code_depa_emp.text.toString().equals("")   ) {
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
                mostrarEmpleado()
            }
        }
        btn_cancelar_registro_empleado.setOnClickListener{ boton ->
            finish()
        }
    }
    fun mostrarEmpleado(){
        val intentExplicito= Intent(
            this, MostrarEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun fecha(Date: String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return Date(sdf.parse(Date.toString()).time)
    }

}
