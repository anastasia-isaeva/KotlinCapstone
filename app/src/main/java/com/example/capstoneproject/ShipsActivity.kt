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

class ShipsActivity : AppCompatActivity() {
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

                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                var a = 1


            },
            {  })
        queue.add(stringReq)
    }
}