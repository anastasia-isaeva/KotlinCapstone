package com.example.capstoneproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import org.json.JSONArray

class RoadsterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Roadster Info"
        setContentView(R.layout.activity_roadster)

        getRoadsterInfo()
    }

    private fun getRoadsterInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
            .authority("api.spacexdata.com")
            .appendPath("v4")
            .appendPath("roadster")
        val url: String = builder.build().toString();

        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->

                var strResp = response.toString()
                val jsonObject: JSONObject = JSONObject(strResp)

                val name = jsonObject.getString("name")
                val details = jsonObject.getString("details")
                val imageArray = jsonObject.getJSONArray("flickr_images")
                val imageUrl = imageArray[0]

                var textView = findViewById<TextView>(R.id.name)
                textView.text = name

                textView = findViewById(R.id.details)
                textView.text = Html.fromHtml("<b>About</b>: ${details}")

                val imageView = findViewById<ImageView>(R.id.roadsterImage)
                Picasso.get().load(imageUrl.toString()).into(imageView)
            },
            {  })
        queue.add(stringReq)
    }
}