import java.util.*
import kotlin.collections.ArrayList

fun main(args:Array<String>){
    print("Hola")
    // Ejemplo java:
    //    Int edad = 31;
    // Mutables no usar
    var edadProfesor = 31   // No especificamos el tipo de dato
    // punto y coma no es necesario
    // Duck Typing
    // var edadCachrro; X -> necesitamos el tipo de datos
    var edadCachorro:Int
    edadCachorro = 3
    edadProfesor = 32
    edadCachorro = 4
    //Inmutables siempre usar
    val numeroCuenta = 123456   // NO SE PUEDEN REASIGNAR
    //numeroCuenta=123

    //tipos de varible
    val nombreProfesor = "Vicente Adrian"
    val sueldo= 12.20
    val apellidoProfesor = 'a'
    val fechaNacimiento = Date() //new Date()

    if (sueldo == 12.20){

    }else{

    }

    when (sueldo) {
        12.20 -> println("sueldo normal")
        -12.20-> println("sueldo negativo")
        else-> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEsblecido = if(sueldo == 12.20) true else false
    //EXPRESION ? X: Y
    //calcularSueldo(1000.00, 14.00)
    calcularSueldo(1000.00, 14.00, null)
    calcularSueldo(tasa = 16.00, sueldo = 800.00, calculoEspecial = null)//Named Parameters
    calcularSueldo(700.00)
    calcularSueldo(sueldo = 650.00)

    val arregloConstante: Array<Int> = arrayOf(1,2,3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30,31,22,23,20)
    print(arregloCumpleanos)
    arregloCumpleanos.add(24)
    print(arregloCumpleanos)
    arregloCumpleanos.remove(30)
    print(arregloCumpleanos)

    arregloCumpleanos
            .forEach {
                println("valor de iteracion:" + it)
            }

    arregloCumpleanos
            .forEach { valorIteracion: Int ->
                        println("valor de iteracion:" + valorIteracion)
            }

    arregloCumpleanos
            .forEach(
                    {
                        valorIteracion: Int ->
                            println("valor de iteracion:" + valorIteracion)
                    }
            )
}

//Funciones
fun calcularSueldo(
        sueldo: Double, //Requeriso!
        tasa: Double = 12.00, //Tiene valor por defecto
        calculoEspecial : Int? = null //puede ser nulo
): Double {
    if (calculoEspecial != null){
        return sueldo * tasa * calculoEspecial
    }else{
        return sueldo * tasa
    }
}

fun imprimirMensaje():Unit{ // Unit = Void
    println("")
}
