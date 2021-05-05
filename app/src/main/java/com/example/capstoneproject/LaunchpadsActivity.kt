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

class LaunchpadsActivity : AppCompatActivity() {
    private var launchpads: ArrayList<Launchpad> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launchpads)
        this.title = "Launchpads Info"

        getLaunchpadsInfo()
    }

    private fun getLaunchpadsInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
            .authority("api.spacexdata.com")
            .appendPath("v4")
            .appendPath("launchpads")
        val url: String = builder.build().toString();

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                launchpads = ArrayList()

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                for (i in 0 until jsonArray.length()) {
                    val item: JSONObject = jsonArray.getJSONObject(i)

                    val name = item.getString("full_name")
                    val details = item.getString("details")
                    val locality = item.getString("locality")
                    val region = item.getString("region")

                    val launchpad = Launchpad(name, details, locality, region)
                    launchpads.add(launchpad)
                }

                val list = launchpads.map { it.toString() }.toTypedArray()
                val arrayAdapter: ArrayAdapter<*>
                var launchpadList = findViewById<ListView>(R.id.listview)
                arrayAdapter = ArrayAdapter(this, R.layout.rowlist, list)
                launchpadList.adapter = arrayAdapter
            },
            {  })
        queue.add(stringReq)
    }
}