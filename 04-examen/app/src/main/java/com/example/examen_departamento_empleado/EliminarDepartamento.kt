package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_eliminar_departamento.*

class EliminarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_departamento)
        Log.i("Activity","OnCreate")

        eliminarDepartamento()

        btn_eliminar_departamento.setOnClickListener { boton ->
            if(txt_indice_dep.text.toString().equals("")){
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
            Departamento.borrarEnCascade(txt_indice_dep.text.toString() + "],")
            eliminarDepartamento()
        }
        }
        btn_cancelar_eliminacion_depa.setOnClickListener{ boton ->
            finish()
        }
    }

    fun eliminarDepartamento(){
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            Departamento.mostrar()
        )//lista
        lv_eliminar_dep.adapter = adaptador
        lv_eliminar_dep
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            adaptador.notifyDataSetChanged()
        }
    }
}