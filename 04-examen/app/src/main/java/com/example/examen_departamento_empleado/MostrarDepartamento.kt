package com.example.examen_departamento_empleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mostrar_departamento.*

class MostrarDepartamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_departamento)
        btn_cancelar_muestra_dep.setOnClickListener{ boton ->
            finish()
        }
    }
}