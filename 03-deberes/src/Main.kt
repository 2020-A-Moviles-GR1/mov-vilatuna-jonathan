import java.text.SimpleDateFormat
import java.util.*


fun main(args:Array<String>) {
    println("****************************DATOS DEPARTAMENTO****************************")
    Departamento.insertarDatos("Marketing", "Cuenca",true, 5.1,1)
    Departamento.insertarDatos("Contabilidad", "Cuenca",true, 2.1,2)
    Departamento.insertarDatos("Inventigacion", "Quito",true, 3.1,3)
    Departamento.insertarDatos("Ventas", "Quito",true, 6.1,4)
    Departamento.insertarDatos("RRHH", "Loja",true, 8.1,5)
    Departamento.mostrar()
    Departamento.totalDatos()
    println("BORRAR DATOS DEL DEPARTAMENTO CON EL INDICE 3")
    Departamento.delete(3)
    Departamento.mostrar()
    Departamento.totalDatos()
    println("ACTUALIZAR DATOS DEL DEPARTAMENTO CON EL INDICE 1")
    Departamento.udate(1,"Gerencia", "Guayas",true, 2.1,2)
    Departamento.mostrar()
    Departamento.totalDatos()
    println("BUSCAR UN DATO DEL DEPARTAMENTO")
    Departamento.buscar("RRHH", "Loja",true, 2.1,5)
    println("GUARDAR DATOS EN ARCHIVO PLANO")
    Departamento.guardarDatos()

    println("\n****************************DATOS DE LOS EMPLEADOS****************************")
    Empleado.insertarDatos(1,"Juan", 360.14,fecha("01-02-2000"),true,2)
    Empleado.insertarDatos(2, "Pedro", 370.14,fecha("01-02-1995"),false,2)
    Empleado.insertarDatos(3, "Pepe", 800.14,fecha("01-02-1996"),true,3)
    Empleado.insertarDatos(4, "Irina", 1200.14,fecha("01-02-2002"),true,2)
    Empleado.insertarDatos(5, "Alex", 900.14,fecha("01-02-1990"),false,1)
    Empleado.mostrar()
    Empleado.totalDatos()
    println("BORRAR DATOS DEL EMPLEADO CON EL INDICE 3")
    Empleado.delete(3)
    Empleado.mostrar()
    Empleado.totalDatos()
    println("ACTUALIZAR DATOS DEL EMPLEADO CON EL INDICE 2")
    Empleado.udate(2,0,"Reychel", 600.00,fecha("01-02-1990"),true,1)
    Empleado.mostrar()
    Empleado.totalDatos()
    println("BUSCAR UN DATOS DEL EMPLEADO")
    Empleado.buscar(5, "Alex", 900.14,fecha("01-02-1990"),false,1)
    println("GUARDAR DATOS EN ARCHIVO PLANO")
    Empleado.guardarDatos()
    println("\n********************BORRAR UN DEPARTAMENTO Y SE BORRARAN LOS EMPLADOS DE ESE DEPARTAMENTO********************")
    println("TABLAS SIN BORRAR UN DEPARTAMENTO")
    Departamento.mostrar()
    Empleado.mostrar()
    Departamento.borrarEnCascade("2"+"],")
    println("TABLAS BORRANDO EL DEPARTAMENTO DE GERENCIA CON EL CODIGO DE DEPARTAMENTO 2")
    Departamento.mostrar()
    Empleado.mostrar()
    Departamento.guardarDatos()
    Empleado.guardarDatos()
    println("*******************FIN DE INGRESO DE DATOS CON LLAMADO A LAS FUNCIONES*************************")
    /****
    **MENU PARA INGRESO DE DATOS
    ****/
    var option = ""
    var codigoDepa = ""

    while(option != "6") {
        println("\n*************************MENU PARA INGRESO DE DATOS POR CONSOLA*****************************\n" +
                "1 - Ingresar Datos Departamento\n" +
                "2 - Ingresar Datos Empleado\n" +
                "3 - Eliminar Departamento\n"+
                "4 - Guardar En Archivo Plano\n"+
                "5 - Editar Departamento\n"+
                "6 - Salir")

        option = readLine().toString()
        when (option) {
            "1" -> {
                Departamento.mostrar()
                Departamento.totalDatos()
                Departamento.insertDatosPorConsola()
                Departamento.mostrar()
                Departamento.totalDatos()
            }
            "2" -> {
                Departamento.mostrar()
                Departamento.totalDatos()
                Empleado.insertDatosPorConsola()
                Empleado.mostrar()
                Departamento.totalDatos()
            }
            "3" -> {
                println("\nTABLAS SIN BORRAR UN DEPARTAMENTO")
                Departamento.mostrar()
                Empleado.mostrar()
                println("\nIngresar Codigo Departamento: ")
                codigoDepa = readLine().toString()
                Departamento.borrarEnCascade("$codigoDepa],")
                println("\nTABLAS BORRANDO EL DEPARTAMENTO DE GERENCIA CON EL CODIGO DE DEPARTAMENTO $codigoDepa")
                Departamento.mostrar()
                Empleado.mostrar()
            }
            "4" -> {
                Departamento.guardarDatos()
                Empleado.guardarDatos()
            }
            "5" -> {
                Departamento.mostrar()
                println("\nIngresar Indice: ")
                val indice = readLine()?.toInt() as Int
                println ("Ingrese  Codigo departamento:")
                val codDepartamento = readLine()?.toInt() as Int
                println ("Ingrese Nombre Departamento: ")
                val nombreDepartamento = readLine()?.toString() as String
                println ("Ingrese Ciudad: ")
                val ciudad = readLine()?.toString() as String
                println ("Ingrese El Estado: ")
                val estado = readLine()?.toBoolean() as Boolean
                println ("Ingrese numero Departamento:")
                val numero =  readLine()?.toDouble() as Double
                Departamento.udate(indice,nombreDepartamento, ciudad,estado,numero,codDepartamento)
                Departamento.mostrar()
                Departamento.totalDatos()
            }
            else -> {
                println("\nCerrando!\n")
            }
        }
    }
}

fun fecha(Date:String): Date {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return Date(sdf.parse(Date).time)
}
