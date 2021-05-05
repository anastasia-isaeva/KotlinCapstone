package com.example.capstoneproject

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.JSONArray

class RocketsActivity : AppCompatActivity() {
    private var rockers: ArrayList<Rockets> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rockets)
        this.title = "Rockets Info"
        getRocketsInfo()

    }

    private fun getRocketsInfo() {
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
                rockers = ArrayList()

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                for (i in 0 until jsonArray.length()) {
                    val item: JSONObject = jsonArray.getJSONObject(i)

                    val name = item.getString("name")
                    val imageUrl = item.getString("image")
                    val homePort = item.getString("home_port")

                    val rocket = Rockets(name, imageUrl, homePort)
                    rockers.add(rocket)
                }

                val list = rockers.map { it.toString() }.toTypedArray()
                val arrayAdapter: ArrayAdapter<*>
                var rockersList = findViewById<ListView>(R.id.listview)
                arrayAdapter = ArrayAdapter(this, R.layout.rowlist, list)
                rockersList.adapter = arrayAdapter
            },
            {  })
        queue.add(stringReq)
    }
}