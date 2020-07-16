package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_view.*

class BListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

        val listaEntrenadores = arrayListOf<Entrenador>()

        listaEntrenadores.add(Entrenador("Pepe","Chupin"))
        listaEntrenadores.add(Entrenador("Alex","Vilan"))
        listaEntrenadores.add(Entrenador("Irina","Quinga"))
        listaEntrenadores.add(Entrenador("Carlos","Lopez"))
        listaEntrenadores.add(Entrenador("Jeff","Perez"))
        listaEntrenadores.add(Entrenador("Blanco","Cat"))
        listaEntrenadores.add(Entrenador("Oscuro","Cat"))

        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, // nomre layout
            listaEntrenadores)//lista

        lv_numeros.adapter = adaptador
        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
        }
    }
}
