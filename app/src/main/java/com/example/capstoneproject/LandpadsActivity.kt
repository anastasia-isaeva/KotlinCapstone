package com.example.capstoneproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class LandpadsActivity : AppCompatActivity() {
    private var landpads: ArrayList<Landpad> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landpads)
        this.title = "Landpads Info"

        val myList = findViewById<ListView>(R.id.listview)
        myList.setOnItemClickListener{ parent, view, position, id ->
            val landpad = landpads[position]
            val bundle = Bundle()

            val intent = Intent(this, LandpadsDetailsActivity::class.java)
            bundle.putParcelable("Landpad", landpad)
            intent.putExtra("Bundle", bundle)
            startActivity(intent)
        }

        getLandpadsInfo()
    }

    private fun getLandpadsInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
            .authority("api.spacexdata.com")
            .appendPath("v4")
            .appendPath("landpads")
        val url: String = builder.build().toString();

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                landpads = ArrayList()

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                for (i in 0 until jsonArray.length()) {
                    val item: JSONObject = jsonArray.getJSONObject(i)

                    val name = item.getString("full_name")
                    val details = item.getString("details")
                    val type = item.getString("type")
                    val locality = item.getString("locality")
                    val region = item.getString("region")

                    val landpad = Landpad(name, details, type, locality, region)
                    landpads.add(landpad)
                }

                val list = landpads.map { it.toString() }.toTypedArray()
                val arrayAdapter: ArrayAdapter<*>
                var landpadList = findViewById<ListView>(R.id.listview)
                arrayAdapter = ArrayAdapter(this, R.layout.rowlist, list)
                landpadList.adapter = arrayAdapter
            },
            {  })
        queue.add(stringReq)
    }
}