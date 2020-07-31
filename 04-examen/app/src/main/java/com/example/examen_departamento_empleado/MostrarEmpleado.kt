package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_mostrar_empleado.*

class MostrarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_empleado)
        Log.i("Activity","OnCreate")

        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            Empleado.datosEmpleado)//lista

        lv_empleado.adapter = adaptador
        lv_empleado
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            adaptador.notifyDataSetChanged()
        }

        btn_cancelar_muestra_empleado.setOnClickListener{ boton ->
            finish()
        }
    }
}