package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        iniciarRecyclerView(
            EmpleadoHTTP.muestra(),
            this,
            rv_usuarios
        )

        btn_cancelar.setOnClickListener{ boton ->
            irOpncEmpleado()
        }
    }
    fun iniciarRecyclerView(
        lista: ArrayList<Any>,
        actividad: RecyclerViewActivity,
        recycler_view: RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }
    fun irOpncEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }
}



