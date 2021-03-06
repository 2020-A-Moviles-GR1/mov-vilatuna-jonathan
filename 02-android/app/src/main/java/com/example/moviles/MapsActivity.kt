package com.example.moviles

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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
// 1) permisos
        establecerConfiguracionMapa(mMap)
        establecerListeners(mMap)
        val espe = LatLng(-0.314085, -78.445839)
        val puntoUsuario = LatLng(-0.313640, -78.445056)
        val titulo = "ESPE"
        val zoom = 17f
        anadirmarcador(espe,titulo)
        moverCamaraConZoom(puntoUsuario,zoom)
val poliLineaUno = googleMap.addPolyline(
    PolylineOptions().
    clickable(true).
    add(
    LatLng(-0.298256, -78.461454),
    LatLng(-0.297033, -78.463750),
    LatLng(-0.294764, -78.461647),
    LatLng(-0.295756, -78.460869)
))


        val poligonoUno = googleMap.addPolygon(
            PolygonOptions().
            clickable(true).
            add(
            LatLng(-0.281140, -78.402116),
            LatLng(-0.280150, -78.400794),
            LatLng(-0.280834, -78.399638),
            LatLng(-0.284012, -78.402407)
        ))
        poligonoUno.fillColor = -0xc771c4
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
        )
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation==PackageManager. PERMISSION_GRANTED
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
            val tienePermisos = permisosFineLocation==PackageManager. PERMISSION_GRANTED
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