package com.example.capstoneproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import org.json.JSONArray

class ShipsActivity : AppCompatActivity() {
    private var ships: ArrayList<Ship> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ships)
        this.title = "Ships Info"

        getShipsInfo()
    }

    private fun getShipsInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
            .authority("api.spacexdata.com")
            .appendPath("v4")
            .appendPath("ships")
        val url: String = builder.build().toString();

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                ships = ArrayList()

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                var a = 1

                for (i in 0 until jsonArray.length()) {
                    val item: JSONObject = jsonArray.getJSONObject(i)

                    val name = item.getString("name")
                    val imageUrl = item.getString("image")
                    val homePort = item.getString("home_port")
                    var yearBuilt = 0
                    if (!item.isNull("year_built")) {
                        val yearBuilt = item.getInt("year_built")
                    }
                    val type = item.getString("type")

                    val ship = Ship(name, imageUrl, homePort, yearBuilt, type)
                    ships.add(ship)
                }

                val list = ships.map { it.toString() }.toTypedArray()
                val arrayAdapter: ArrayAdapter<*>
                var shipsList = findViewById<ListView>(R.id.listview)
                arrayAdapter = ArrayAdapter(this, R.layout.rowlist, list)
                shipsList.adapter = arrayAdapter
            },
            { })
        queue.add(stringReq)
    }
}