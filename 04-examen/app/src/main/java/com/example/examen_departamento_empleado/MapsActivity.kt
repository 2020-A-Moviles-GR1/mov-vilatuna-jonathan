package com.example.examen_departamento_empleado

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener
{

    private lateinit var mMap: GoogleMap
    var tienePermisos = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        solicitarPermisos()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
// 1) permisos
        establecerConfiguracionMapa(mMap)
        establecerListeners(mMap)
        val puntoUsuario = LatLng(-0.313640, -78.445056)
        val zoom = 17f

        var coordenada1 = -0.313640
        var coordenada2 = -78.445839

        for ((indice, item) in EmpleadoHTTP.muestra().withIndex()) {
            anadirmarcador(LatLng(coordenada1,coordenada2), item.toString())
            coordenada1 = coordenada1-0.000100
            coordenada2 = coordenada2-0.000100
        }

        moverCamaraConZoom(puntoUsuario,zoom)

        mMap.setOnMarkerClickListener { boton ->
            irUrlWeb()
        }

    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mMap.moveCamera(CameraUpdateFactory
            .newLatLngZoom(latLng,zoom))
    }
    fun anadirmarcador(latLng: LatLng,tile: String){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(tile)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.launcher))
        ).showInfoWindow()

    }

    fun irUrlWeb(): Boolean {
        val intentExplicito = Intent(
            this,
            WebView::class.java
        )
        this.startActivity(intentExplicito)
        return true
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation== PackageManager. PERMISSION_GRANTED
        if(tienePermisos){
            this.tienePermisos = true
            Log.i("mapa", "tiene permisos FINE LOCATION")
        }else{
            ActivityCompat.requestPermissions(
                this, //contexto
                arrayOf( // arreglo permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 // codigo que esperamos
            )
        }
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation== PackageManager. PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun establecerListeners(map: GoogleMap){
        with(map){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("mapa","Empezando a mover onCameraMoveStarted")
    }

    override fun onCameraMove() {
        Log.i("mapa","moviendo onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("mapa","quieto onCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa","onPolylineClick ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","onPolygonClick ${p0.toString()}")
    }
}