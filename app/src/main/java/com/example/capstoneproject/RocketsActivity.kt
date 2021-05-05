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
import org.json.JSONObject
import org.json.JSONArray

class RocketsActivity : AppCompatActivity() {
    private var rockets: ArrayList<Rockets> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rockets)
        this.title = "Rockets Info"

        val myList = findViewById<ListView>(R.id.listview)
        myList.setOnItemClickListener{ parent, view, position, id ->
            val rocket = rockets[position]
            val bundle = Bundle()

            val intent = Intent(this, RockersDetailsActivity::class.java)
            bundle.putParcelable("Rocket", rocket)
            intent.putExtra("Bundle", bundle)
            startActivity(intent)
        }

        getRocketsInfo()

    }

    private fun getRocketsInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
            .authority("api.spacexdata.com")
            .appendPath("v4")
            .appendPath("rockets")
        val url: String = builder.build().toString();

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                rockets = ArrayList()

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                for (i in 0 until jsonArray.length()) {
                    val item: JSONObject = jsonArray.getJSONObject(i)

                    val name = item.getString("name")
                    val images = item.getJSONArray("flickr_images")
                    val imageUrl = images[0].toString()
                    val description = item.getString("description")

                    val rocket = Rockets(name, description, imageUrl)
                    rockets.add(rocket)
                }

                val list = rockets.map { it.toString() }.toTypedArray()
                val arrayAdapter: ArrayAdapter<*>
                var rockersList = findViewById<ListView>(R.id.listview)
                arrayAdapter = ArrayAdapter(this, R.layout.rowlist, list)
                rockersList.adapter = arrayAdapter
            },
            {  })
        queue.add(stringReq)
    }
}