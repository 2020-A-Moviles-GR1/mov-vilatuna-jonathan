package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*
import kotlin.math.log

class IntentEnviaParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)
    //proiedad clase intent
        //this.intet
       val numeroEncontrado = intent.getIntExtra("numero",0)

        if(numeroEncontrado!=0){
            Log.i("intents","el numero encontrado es : ${numeroEncontrado}")
        }
        val textoCompartido: String?  = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido != null){
            Log.i("intents","el texto es : ${textoCompartido}")

        }

        val cachetes = intent.getParcelableExtra<Mascota>("cachetes")
        if (cachetes!=null){
            Log.i("Parcelable","${cachetes.nombre} ${cachetes.duenio?.nombre}")
        }
        val arregloMascota = intent.getParcelableArrayListExtra<Mascota>("arregloMascota")
        if(arregloMascota != null) {
            arregloMascota.forEach {
                if (it != null) {
                    Log.i("Parcelable", "EN arreglo")
                    Log.i("Parcelable", "${it.nombre} ${it.duenio?.nombre}")
                }
            }
        }

        btn_devolver_respuesta.
                setOnClickListener{
                    //metodo clase
                    //this.finish()
                    finish()
                }

        btn_resp_aceptar
            .setOnClickListener {
                val nombre = "Adrian"
                val edad = 31
                val intentRespuesta = Intent()
                intentRespuesta.putExtra("nombre", nombre)
                intentRespuesta.putExtra("edad", edad)
                // this.setResult()
                setResult(
                    RESULT_OK,
                    intentRespuesta
                )
                // this.finish()
                finish()
            }

        btn_resp_cancelar
            .setOnClickListener {
                val intentCancelado = Intent()
                setResult(
                    Activity.RESULT_CANCELED,
                    intentCancelado
                )
                finish()
            }
    }
}