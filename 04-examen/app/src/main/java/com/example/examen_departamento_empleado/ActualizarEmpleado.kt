package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_empleado.*
import java.sql.Date
import java.text.SimpleDateFormat

class ActualizarEmpleado : AppCompatActivity() {
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
            if (tv_indice.text.toString().equals("") ||
                txt_act_cod_emp.text.toString().equals("") ||
                txt_act_nom_emp.text.toString().equals("") ||
                txt_act_sueldo_emp.text.toString().equals("") ||
                txt_act_fecha_emp.text.toString().equals("") ||
                txt_act_cod_dep_emp.text.toString().equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else {
                Empleado.udate(
                    tv_indice.text.toString().toInt(),
                    txt_act_cod_emp.text.toString().toInt(),
                    txt_act_nom_emp.text.toString(),
                    txt_act_sueldo_emp.text.toString().toDouble(),
                    fecha(txt_act_fecha_emp.text.toString()),
                    estado,
                    txt_act_cod_dep_emp.text.toString().toInt()
                )
                mostrarEmpleadoActualizado()
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
            Empleado.mostrar()
        )//lista

        lv_datos_actualizar.adapter = adaptador
        lv_datos_actualizar
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            tv_indice.text=position.toString()
            adaptador.notifyDataSetChanged()
        }
    }

    fun fecha(Date:String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return Date(sdf.parse(Date).time)
    }
}