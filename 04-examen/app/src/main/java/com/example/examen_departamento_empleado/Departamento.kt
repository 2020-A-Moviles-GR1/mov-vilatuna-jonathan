package com.example.examen_departamento_empleado

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Departamento private constructor(var nombreDepartamento:String, var ciudad:String,var estado:Boolean, var numero: Double,var codDepartamento: Int) {
    companion object {
        var count: Int = 0
        private fun create(nombreDepartamento:String, ciudad:String,estado:Boolean, numero: Double,codDepartamento: Int): Departamento = Departamento(nombreDepartamento,  ciudad,estado, numero, codDepartamento)
        var datosDepartamento = arrayListOf<Any>()
        var datosDepartamento2 = arrayListOf<Any>()

        fun insertarDatos(nombreDepartamento:String, ciudad:String,estado:Boolean, numero: Double,codDepartamento: Int){
            val ingresoDatos = Departamento.create(nombreDepartamento,  ciudad,estado, numero, codDepartamento)
            this.datosDepartamento.add(arrayListOf( ingresoDatos.nombreDepartamento, ingresoDatos.ciudad,ingresoDatos.estado, ingresoDatos.numero,ingresoDatos.codDepartamento))
        }

        fun totalDatos(){
            println("TOTAL DEPARTAMENTOS: ${datosDepartamento.size}")
        }
        fun mostrar(): ArrayList<Any> {
            datosDepartamento2.clear()
            for ((indice, item) in datosDepartamento.withIndex()) {
                println("Indice $indice: $item")
                datosDepartamento2.add("Indice $indice: $item")
            }
            return datosDepartamento2
        }
        fun insertDatosPorConsola(){
            println ("Ingrese Codigo: ")
            val codDepartamento = readLine()?.toInt() as Int
            println ("Ingrese Nombre Departamento: ")
            val nombreDepartamento = readLine()?.toString() as String
            println ("Ingrese Ciudad: ")
            val ciudad = readLine()?.toString() as String
            println ("Ingrese El Estado: ")
            val estado = readLine()?.toBoolean() as Boolean
            println ("Ingrese numero Departamento:")
            val numero =  readLine()?.toDouble() as Double
            insertarDatos(nombreDepartamento,ciudad,estado,numero,codDepartamento)
        }
        fun delete(Indice: Int){
            this.datosDepartamento.removeAt(Indice)
        }
        fun udate(indice: Int, nombreDepartamento:String, ciudad:String,estado:Boolean, numero: Double,codDepartamento: Int){
            this.datosDepartamento[indice]=arrayListOf(nombreDepartamento,ciudad,estado,numero,codDepartamento)
        }
        fun guardarDatos() {
            var datos: String=""
            for ((indice, item) in datosDepartamento.withIndex()) {
                datos += "Indice $indice: $item \n"
            }
            File("TABLA_DEPARTAMENTO.txt").writeText(datos+"\n")
            println("Datos Guardados Correctamente")
        }
        fun buscar( nombreDepartamento:String, ciudad:String,estado:Boolean, numero: Double,codDepartamento: Int) {
            val dato = datosDepartamento.find { it == arrayListOf(nombreDepartamento,ciudad,estado,numero,codDepartamento) }
            val count: Int = 0
            for ((indice, item) in datosDepartamento.withIndex()) {
                if(item ==arrayListOf(nombreDepartamento,ciudad,estado,numero,codDepartamento)){
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
            val st = StringTokenizer(datosDepartamento.toString())
            while (st.hasMoreTokens()) {
                if((count%5)==0) {
                    if(codDepartamento==codDepa){
                        Empleado.borrarEnCascade(codDepa)
                        delete(count2)
                    }
                    count2++
                }
                count++
                codDepa=st.nextToken()
            }
        }
    }
}
