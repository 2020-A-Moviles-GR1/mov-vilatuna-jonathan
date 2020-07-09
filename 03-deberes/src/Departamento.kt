import java.io.File
import java.nio.charset.Charset
import java.util.*

class Departamento private constructor(var nombreDepartamento:String, var ciudad:String,var codDepartamento: Int) {
    companion object {
        var count: Int = 0
        private fun create(nombreDepartamento:String, ciudad:String,codDepartamento: Int): Departamento = Departamento(nombreDepartamento,  ciudad, codDepartamento)
        var datosDepartamento = arrayListOf<Any>()

        fun insertarDatos(nombreDepartamento:String, ciudad:String,codDepartamento: Int){
            val ingresoDatos = Departamento.create(nombreDepartamento,  ciudad, codDepartamento)
            this.datosDepartamento.add(arrayListOf( ingresoDatos.nombreDepartamento, ingresoDatos.ciudad,ingresoDatos.codDepartamento))
        }

        fun totalDatos(){
            println("TOTAL DEPARTAMENTOS: ${datosDepartamento.size}")
        }
        fun mostrar(){
            for ((indice, item) in datosDepartamento.withIndex()) {
                println("Indice $indice: $item")
            }
        }
        fun insertDatosPorConsola(){
            println ("Ingrese Codigo: ")
            val codDepartamento = readLine()?.toInt() as Int
            println ("Ingrese Nombre Departamento: ")
            val nombreDepartamento = readLine()?.toString() as String
            println ("Ingrese Ciudad: ")
            val ciudad = readLine()?.toString() as String
            insertarDatos(nombreDepartamento,ciudad,codDepartamento)
        }
        fun delete(Indice: Int){
            this.datosDepartamento.removeAt(Indice)
        }
        fun udate(indice: Int, nombreDepartamento:String, ciudad:String,codDepartamento: Int){
            this.datosDepartamento[indice]=arrayListOf(nombreDepartamento, ciudad, codDepartamento)
        }
        fun guardarDatos() {
            var datos: String=""
            for ((indice, item) in datosDepartamento.withIndex()) {
                datos += "Indice $indice: $item \n"
            }
            File("TABLA_DEPARTAMENTO.txt").writeText(datos+"\n")
            println("Datos Guardados Correctamente")
        }
        fun buscar( nombreDepartamento:String, ciudad:String,codDepartamento: Int) {
            val dato = datosDepartamento.find { it == arrayListOf(nombreDepartamento,ciudad,codDepartamento) }
            val count: Int = 0
            for ((indice, item) in datosDepartamento.withIndex()) {
                if(item ==arrayListOf(nombreDepartamento,ciudad, codDepartamento)){
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
                if((count%3)==0) {
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