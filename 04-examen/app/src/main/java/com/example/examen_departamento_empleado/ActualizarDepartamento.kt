package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_departamento.*

class ActualizarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_departamento)

        Log.i("Activity","OnCreate")
        mostrarDepartamentoActualizado()
        btn_actualizar_departamento.setOnClickListener{ boton ->
            var estado = this.estado_depa.isChecked

            if (tv_indice.text.toString().equals("") ||
                txt_nom_dep.text.toString().equals("") ||
                txt_cui_dep.text.toString().equals("") ||
                txt_nun_dep.text.toString().equals("") ||
                txt_cod_dep.text.toString().equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            }else{
            Departamento.udate(tv_indice.text.toString().toInt(),
                txt_nom_dep.text.toString(),
                txt_cui_dep.text.toString(),
                estado,
                txt_nun_dep.text.toString().toDouble(),
                txt_cod_dep.text.toString().toInt())
            mostrarDepartamentoActualizado()
            }
        }
        btn_cancelar_actualizar_departamento.setOnClickListener{ boton ->
            finish()
        }
    }

    fun mostrarDepartamentoActualizado(){
            val adaptador = ArrayAdapter(
                this, //contexto
                android.R.layout.simple_expandable_list_item_1, // nomre layout
                Departamento.mostrar()
            )//lista

            lv_datos_actualizados.adapter = adaptador
            lv_datos_actualizados
                .onItemClickListener = AdapterView.OnItemClickListener {
                    parent, view, position, id ->
                Log.i("list-view", "Posicion $position")
                tv_indice.text=position.toString()
                adaptador.notifyDataSetChanged()
            }
    }
}