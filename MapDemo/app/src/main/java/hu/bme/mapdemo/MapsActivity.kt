package hu.bme.mapdemo

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import hu.bme.mapdemo.databinding.ActivityMapsBinding
import java.util.*
import kotlin.concurrent.thread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    MyLocationManager.OnNewLocationAvailable {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationManager: MyLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationManager = MyLocationManager(this, this)

        requestNeededPermission()
    }

    fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        } else {
            // we have the permission
            handleLocationStart()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm granted", Toast.LENGTH_SHORT)
                        .show()

                    handleLocationStart()
                } else {
                    Toast.makeText(
                        this,
                        "ACCESS_FINE_LOCATION perm NOT granted", Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun handleLocationStart() {
        locationManager.startLocationMonitoring()
    }

    var markerAtCurrentPosition: Marker? = null

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,
            R.raw.mymapstyle))

        val marker = LatLng(47.0, 19.0)
        markerAtCurrentPosition = mMap.addMarker(MarkerOptions().position(marker).title("Marker in Hungary"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))

        //mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        mMap.isTrafficEnabled = true

        mMap.setOnMapClickListener {
            val newMarker = mMap.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("Marker demo")
                    .snippet("Long marker text...")
            )

            newMarker?.isDraggable = true

            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

            mMap.setOnMarkerClickListener {
                geocodeLocation(it.position)
                true
            }


/*            val random = Random(System.currentTimeMillis())
            val cameraPostion = CameraPosition.Builder()
                .target(it)
                .zoom(5f + random.nextInt(15))
                .tilt(30f + random.nextInt(15))
                .bearing(-45f + random.nextInt(90))
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPostion))*/
        }

    }

    override fun onNewLocation(location: Location) {
        binding.tvLocation.text = """
            ${location.latitude}
            ${location.longitude}
            Accuracy: ${location.accuracy}
            Speed: ${location.speed}
            Alt: ${location.altitude}
        """.trimIndent()

        val latLng = LatLng(location.latitude, location.longitude)
        markerAtCurrentPosition?.position = latLng
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }


    private fun geocodeLocation(location: LatLng) {
        thread {
            try {
                val gc = Geocoder(this, Locale.getDefault())
                var addrs: List<Address> =
                    gc.getFromLocation(location.latitude, location.longitude, 3)
                val addr =
                    "${addrs[0].getAddressLine(0)}, ${addrs[0].getAddressLine(1)}," +
                            " ${addrs[0].getAddressLine(2)}"

                runOnUiThread {
                    Toast.makeText(this, addr, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MapsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }


}