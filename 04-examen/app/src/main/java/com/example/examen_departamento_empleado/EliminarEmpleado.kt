package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_eliminar_empleado.*

class EliminarEmpleado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_empleado)
        Log.i("Activity","OnCreate")
        eliminarEmpleado()
        btn_eliminar_empleado.setOnClickListener{ boton ->
            if(tv_indice.text.toString().equals("")){
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
            Empleado.delete(tv_indice.text.toString().toInt())
            eliminarEmpleado()
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
            Empleado.mostrar()
        )//lista
        lv_eliminar.adapter = adaptador
        lv_eliminar
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            tv_indice.text=position.toString()
            adaptador.notifyDataSetChanged()

        }
    }
}