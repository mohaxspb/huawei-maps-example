package ru.kuchanov.huaweiandgoogleservices

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAP_VIEW_BUNDLE_KEY = "map_view_bundle_key"
    }

    private var mapView: MapView? = null

    private var mSupportMapFragment: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)

        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync {
            Log.d(javaClass.simpleName, "mapView getMapAsync")
        }

        mSupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.clubsMapFragment) as SupportMapFragment?
        mSupportMapFragment?.getMapAsync {
            Log.d(javaClass.simpleName, "mSupportMapFragment getMapAsync")
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveMapInstanceState(outState)
    }

    override fun onStop() {
        mapView?.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    private fun saveMapInstanceState(outState: Bundle?) {
        val mapViewBundle = Bundle()
        mapView?.onSaveInstanceState(mapViewBundle)
        outState?.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
    }
}