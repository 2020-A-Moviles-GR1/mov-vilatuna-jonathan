package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")
        Departamento.insertarDatos("Marketing", "Cuenca", true, 5.1, 1)
        Departamento.insertarDatos("Contabilidad", "Cuenca", true, 2.1, 2)
        Departamento.insertarDatos("Inventigacion", "Quito", true, 3.1, 3)
        Departamento.insertarDatos("Ventas", "Quito", true, 6.1, 4)
        Departamento.insertarDatos("RRHH", "Loja", true, 8.1, 5)

        Empleado.insertarDatos(1,"Juan", 360.14, fecha("01-02-2000"),true,2)
        Empleado.insertarDatos(2, "Pedro", 370.14,fecha("01-02-1995"),false,2)
        Empleado.insertarDatos(3, "Pepe", 800.14,fecha("01-02-1996"),true,3)
        Empleado.insertarDatos(4, "Irina", 1200.14,fecha("01-02-2002"),true,2)
        Empleado.insertarDatos(5,"Ju", 360.14, fecha("01-02-2002"),true,2)
        Empleado.insertarDatos(6, "Pe", 370.14,fecha("01-02-1998"),false,2)
        Empleado.insertarDatos(7, "Pedr", 800.14,fecha("01-02-1999"),true,3)
        Empleado.insertarDatos(8, "Ivys", 1200.14,fecha("01-02-2007"),true,2)
        Empleado.insertarDatos(9, "Al", 900.14,fecha("01-02-1999"),false,1)
        Empleado.insertarDatos(10, "Alfon", 900.14,fecha("01-02-1970"),false,1)

        btn_opc_empleado.setOnClickListener{boton ->
          irOpncEmpleado()
        }
        btn_opc_departamento.setOnClickListener{boton ->
            irOpncDepartamento()
        }
    }


    fun irOpncEmpleado(){
        val intentExplicito= Intent(
            this, MenuEmpleado::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irOpncDepartamento(){
        val intentExplicito= Intent(
            this, MenuDepartamento::class.java
        )
        this.startActivity(intentExplicito)
    }
    fun fecha(Date:String): Date {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        return Date(sdf.parse(Date).time)
    }
}
