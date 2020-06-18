import java.util.*

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
            .forEach { valorIteracion: Int ->
                println("Valor iteracion: " + valorIteracion)
            }
    arregloCumpleanos
            .forEach(
                    { valorIteracion: Int ->
                        println("Valor iteracion: " + valorIteracion)
                    }
            )
    // Operadores -> TODOS LOS LENGUAJES
    // ForEach no devuelve nada -> Unit

    arregloCumpleanos
            .forEach { iteracion: Int ->
                println("Valor de la iteracion " + iteracion)
                println("Valor con -1 = ${iteracion * -1} ")
            }

    val respuestaArregloForEach = arregloCumpleanos
            .forEachIndexed { index: Int, iteracion: Int ->
                println("Valor de la iteracion " + iteracion)
            }
    println(respuestaArregloForEach) // Void Unit

    // MAP -> muta el arreglo (cambio el arreglo)
    // 1) Enviemos el nuevlo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGlo con los valores modificados
    val respuestaMap = arregloCumpleanos
            .map { iterador: Int ->
                iterador * -1
            }
    val respuestaMapDos= arregloCumpleanos
            .map { iterador: Int ->
                val nuevoValor = iterador * -1
                val otroValor = nuevoValor * 2
                return@map otroValor
            }
    val respuestaMapTres= arregloCumpleanos
            .map { iterador: Int ->
                val nuevoValor = iterador * -1
                val otroValor = nuevoValor * 2
                return@map Date()
            }

    //ANY -> OR (Some)
    //ALL -> AND (Every)
    // Filter -> FILTRAR EL ARREGLO
    //AND -> TRUE, todo lo demas falso  OR -> TODO es falso, lo demas es verdadero
    //1)vdevolver una expresion (TRUE O FALSE)
    //2) nuevo arreglo que cumpla esa expresion (Boolean)
    val respuestaFilter = arregloCumpleanos
            .filter {
                iteracion: Int ->
                val esMayorA23 = iteracion >23
                return@filter esMayorA23
            }

    println(respuestaMap)
    println(respuestaMapDos)
    println(respuestaMapTres)
    println(arregloCumpleanos)
    println(respuestaFilter)

    //ANY -> OR (Some)
    //ALL -> AND (Every)
    //AND -> TRUE, todo lo demas falso  OR -> TODO es falso, lo demas es verdadero
    //1)devolver una expresion (TRUE O FALSE)
    //2) nuevo arreglo que cumpla esa expresion (Boolean)
    //(30,31,22,23,20)
    val respuestaAny = arregloCumpleanos
            .any{ iterador: Int ->
                return@any iterador < 25
            }
    val respuestaAll = arregloCumpleanos
            .all{ iterador: Int ->
                return@all iterador > 18
            }
    println(respuestaAny)
    println(respuestaAll)

    //Reduce
    //1)Devuelve el acumulado
    //2) En que valor empieza
    //devuelve un numero
    //(30,31,22,23,20)
    //("a","b","c","d")
    //"abcd"
    val respuestaReduce = arregloCumpleanos //acomulador 0
            .reduce{acumulador, iteracion ->
                return@reduce acumulador+iteracion
            }
    println(respuestaReduce)

    val respuestaFold = arregloCumpleanos
            .fold(
                    100,
                    { acumulador, iteracion ->
                return@fold acumulador - iteracion
            })
    //arregloCumpleanos.reduceRight
    //arregloCumpleanos.foldRight
    println(respuestaFold)
    // forEach -> nada
    // map -> Arreglo
    // filter -> Arreglo
    // all -> Booleano
    // any -> Booleano
    // reduce -> Valor
    // fold -> Valor

    //Reducir el dano en
    //18<
    val vidaActual = arregloCumpleanos
            .map { it * 0.8 } //(24, 24.8, 17.7, 18.4, 16)
            .filter { it > 18 } //(24, 24.8, 18.4)
            .fold(100.00,
                    {acc, d ->acc - d})
            .also { println(it) }
    println(vidaActual)
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

abstract  class NumerosJava{ // val nuevosNumeros = Numeros(1,2)
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(uno:Int, dos:Int){
        numeroUno = uno
        numeroDos = dos
    }
}

abstract  class Numeros( // val nuevosNumeros =Numeros (1,2)
    protected  val numeroUno:Int, // si son publicos no hace falta declarar el tipo
    protected val numeroDos:Int
){
}

class Suma(
         uno: Int,
         dos: Int
        ):Numeros(uno, dos){
    public fun Sumar():Int{
        return  this.numeroUno + this.numeroDos
    }
}