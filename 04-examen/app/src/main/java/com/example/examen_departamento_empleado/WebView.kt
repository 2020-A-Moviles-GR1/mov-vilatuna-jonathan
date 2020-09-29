package com.example.examen_departamento_empleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*


class WebView : AppCompatActivity() {

    private val url = "http://192.168.56.1:1337/Empleado"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


id_web.webChromeClient = object : WebChromeClient(){
    
}
        id_web.webViewClient = object : WebViewClient(){

        } 
        val setting = id_web.settings
        setting.javaScriptEnabled=true
        id_web.loadUrl(url)

        btn_mapa.setOnClickListener {boton ->
            menu()
        }

    }

    override fun onBackPressed() {
        if(id_web.canGoBack()){
            id_web.goBack()
        }else {
            super.onBackPressed()
        }
    }

    fun menu(){
        val intentExplicito = Intent(
            this,
            MenuEmpleado::class.java
        )
        startActivity(intentExplicito)
    }

}