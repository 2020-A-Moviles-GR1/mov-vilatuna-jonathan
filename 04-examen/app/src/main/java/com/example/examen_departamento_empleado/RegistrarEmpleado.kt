package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_registrar_empleado.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class RegistrarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_empleado)
        Log.i("Activity","OnCreate")


        btn_registrar_empleado.setOnClickListener{ boton ->
            /*if(txt_cod_emp.text.toString().toInt()==null && txt_nom_emp.text.toString()==null&&
            txt_sueldo.text.toString().toDouble()==null && txt_fecha_emp.text.toString() as java.sql.Date== null
            && txt_code_depa_emp.text.toString().toInt()==null){
        }*/

            var estado = !estado_emp.isChecked
            Empleado.insertarDatos(txt_cod_emp.text.toString().toInt(),txt_nom_emp.text.toString(),
                txt_sueldo.text.toString().toDouble(),
                txt_fecha_emp.text.toString() as java.sql.Date,estado,txt_code_depa_emp.text.toString().toInt())
            mostrarEmpleado()
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
}