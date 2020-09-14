package com.example.examen_departamento_empleado

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*
import kotlin.collections.ArrayList

class EmpleadoHTTP (
    var createdAt: Long,
    var updatedAt: Long,
    var id: Int,
    var codEmpleado: Int,
var nombreEmpleado: String,
//var sueldo: Double,
//var fechaNacimiento: Date,
//var estado: Boolean,
var codDepartamento: Int,
    var departamento: Any?
)
{
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "id: $id Empleado: $nombreEmpleado Departamento: $departamento"
    }

    companion object {
        val datosEmpleado = arrayListOf<Any>()
        val ConverterPk = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == EmpleadoHTTP::class.java

            override fun fromJson(jv: JsonValue): EmpleadoHTTP {
                return if (jv.obj?.get("departamento") is JsonObject) {
                    EmpleadoHTTP(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objInt("codEmpleado"),
                        jv.objString("nombreEmpleado"),
                        //jv.objString("sueldo") as Double,
                        //jv.objString("fechaNacimiento") as Date,
                        //jv.objString("estado").toBoolean(),
                        jv.objInt("codDepartamento"),
                        Klaxon().parseFromJsonObject<DepartamentoHTTP>(jv.obj?.get("departamento") as JsonObject)
                    )
                } else if (jv.obj?.get("departamento") is Int) {
                    return EmpleadoHTTP(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objInt("codEmpleado"),
                        jv.objString("nombreEmpleado"),
                        //jv.objString("sueldo") as Double,
                        //jv.objString("fechaNacimiento") as Date,
                       // jv.objString("estado").toBoolean(),
                        jv.objInt("codDepartamento"),
                        jv.objInt("departamento")
                    )
                } else {
                    throw Error("Error")
                }
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