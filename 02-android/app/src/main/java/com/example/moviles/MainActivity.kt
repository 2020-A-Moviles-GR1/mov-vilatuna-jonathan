package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")

        btn_ciclo_vida.setOnClickListener{boton ->
           irCicloDeVida()
        }
        btn_list_view.setOnClickListener{boton ->
            irListView()
        }
    }

    fun irCicloDeVida(){
        val intentExplicito= Intent(
            this, CicloDeVida::class.java
        )
this.startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito= Intent(
            this, BListViewActivity::class.java
        )
        //this.startActivity(intentExplicito)
        startActivity(intentExplicito)
    }
}