package com.example.examen_departamento_empleado

import android.text.Editable
import java.io.File
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class Empleado private constructor(var codEmpleado: Int, var nombreEmpleado:String, var sueldo: Double, var fechaNacimiento: Date, var estado:Boolean, var codDepartamento: Int) {
    companion object {
        var count: Int = 0
        private fun create(codEmpleado: Int, nombreEmpleado:String, sueldo: Double, fechaNacimiento: Date, estado:Boolean, codDepartamento: Int): Empleado = Empleado(codEmpleado, nombreEmpleado, sueldo, fechaNacimiento, estado, codDepartamento)
        var datosEmpleado = arrayListOf<Any>()

        fun insertarDatos(codEmpleado: Int, nombreEmpleado:String, sueldo: Double, fechaNacimiento: Date, estado:Boolean, codDepartamento: Int){
            val ingresoDatos = Empleado.create(codEmpleado, nombreEmpleado, sueldo, fechaNacimiento, estado,codDepartamento)
            this.datosEmpleado.add(arrayListOf(ingresoDatos.codEmpleado, ingresoDatos.nombreEmpleado,ingresoDatos.sueldo, ingresoDatos.fechaNacimiento, ingresoDatos.estado, ingresoDatos.codDepartamento))
        }
        fun totalDatos(){
            println("TOTAL EMPLEADOS: ${datosEmpleado.size}")
        }
        fun mostrar(){
            for ((indice, item) in datosEmpleado.withIndex()) {
                println("Indice $indice: $item")
            }
        this.datosEmpleado
        }
        fun insertDatosPorConsola(){
            println ("Ingrese Codigo Empleado:")
            val codEmpleado = readLine()?.toInt() as Int
            println ("Ingrese Nombre del Empleado: ")
            val nombreEmpleado = readLine()?.toString() as String
            println ("Ingrese Sueldo: ")
            val sueldo = readLine()?.toDouble() as Double
            println ("Ingrese Fecha de nacimiento: ")
            val fechaNacimiento = readLine()?.toString() as String
            println ("Ingrese El Estado: ")
            val estado = readLine()?.toBoolean() as Boolean
            println ("Ingrese Codigo Departamento:")
            val codDepartamento = readLine()?.toInt() as Int
            insertarDatos(codEmpleado, nombreEmpleado, sueldo, fecha(fechaNacimiento), estado, codDepartamento)
        }
        fun delete(idice: Int){
            this.datosEmpleado.removeAt(idice)
        }
        fun udate(indice:Int, codEmpleado: Int, nombreEmpleado:String, sueldo:Double, fechaNacimiento: Date, estado:Boolean, codDepartamento: Int){
            this.datosEmpleado[indice]=arrayListOf(codEmpleado, nombreEmpleado, sueldo, fechaNacimiento, estado, codDepartamento)
        }
        fun guardarDatos() {
            var datos: String=""

            for ((indice, item) in datosEmpleado.withIndex()) {
                datos += "Indice $indice: $item \n"
            }
            File("TABLA_EMPLEADOS.txt").writeText(datos+"\n")
            println("Datos Guardados Correctamente")
        }
        fun buscar(codEmpleado: Int, nombreEmpleado:String, sueldo:Double, fechaNacimiento: Date, estado:Boolean, codDepartamento: Int){
            val dato = datosEmpleado.find { it == arrayListOf(codEmpleado, nombreEmpleado, sueldo, fechaNacimiento, estado, codDepartamento) }
            val count: Int = 0
            for ((indice, item) in datosEmpleado.withIndex()) {
                if(item ==arrayListOf(codEmpleado, nombreEmpleado, sueldo, fechaNacimiento, estado, codDepartamento)){
                    println("Indice $indice: $dato")
                    count == indice
                }
            }
            if(dato==null){
                println("Dato no encontrado")
            }
        }
        fun borrarEnCascade(codDepartamento:String){
            var codDepa: String=""
            var count: Int = 0
            var count2: Int = -1
            val eliminarDatosPosicion= arrayListOf<Int>()
            val st = StringTokenizer(datosEmpleado.toString())
            while (st.hasMoreTokens()) {
                if((count%11)==0) {
                    if(codDepartamento==codDepa){
                        eliminarDatosPosicion.add(count2)
                    }
                    count2++
                }
                count++
                codDepa=st.nextToken()
            }
            for (item in eliminarDatosPosicion.asReversed()) {
                delete(item)
            }
        }
        fun fecha(Date:String): Date {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val strDate = "2000-01-01"
            return Date(sdf.parse(strDate).time)
        }
    }
}

