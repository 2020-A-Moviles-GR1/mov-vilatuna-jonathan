package com.example.examen_departamento_empleado

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*
import kotlin.collections.ArrayList

class DepartamentoHTTP(
    var createdAt: Long,
    var updatedAt: Long,
    var id: Int,
    var nombreDepartamento: String,
    var ciudad: String,
   // var estado: String,
   // var numero: Double,
    //var codDepartamento: Int,
    var empleados: ArrayList<EmpleadoHTTP>? = null
){
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "id:$id  Departamento: $nombreDepartamento Ciudad: $ciudad"
    }

    companion object{
        val datosEmpleado = arrayListOf<Any>()
        val converterUs = object : Converter {
            override fun canConvert(cls: Class<*>)=cls==DepartamentoHTTP::class.java

            override fun fromJson(jv: JsonValue): DepartamentoHTTP {
                return DepartamentoHTTP(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombreDepartamento"),
                    jv.objString("ciudad"),
                    //jv.objString("estado")//,
                    //jv.objString("numero") as Double//,
                  //  jv.objInt("codDepartamento")//,
                    Klaxon().parseFromJsonArray<EmpleadoHTTP>(
                       jv.obj?.get("empleados") as JsonArray<*>
                    ) as ArrayList<EmpleadoHTTP>
                )
            }

            override fun toJson(value: Any): String {
                TODO("Not yet implemented")
            }

        }
        fun guardar(dato:Any): ArrayList<Any> {
            datosEmpleado.add(
                dato
            )
            return datosEmpleado
        }

        fun muestra(): ArrayList<Any> {
           return datosEmpleado
        }
        fun borrar(){
            datosEmpleado.clear()
        }

    }
}