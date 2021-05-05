package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView

class LandpadsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landpads_details)

        val bundle = intent.getBundleExtra("Bundle")
        val landpad = bundle?.getParcelable<Landpad>("Landpad")
        if (landpad != null) {
            this.title = "Landpad: ${landpad.name} Details"
        } else {
            this.title = "Landpad Details"
        }

        var textView = findViewById<TextView>(R.id.name)
        textView.text = Html.fromHtml("<b>Name</b>: ${landpad?.name}")

        textView = findViewById<TextView>(R.id.details)
        textView.text = Html.fromHtml("<b>Details</b>: ${landpad?.details}")

        textView = findViewById<TextView>(R.id.type)
        textView.text = Html.fromHtml("<b>Type</b>: ${landpad?.type}")

        textView = findViewById<TextView>(R.id.locality)
        textView.text = Html.fromHtml("<b>Type</b>: ${landpad?.locality}")

        textView = findViewById<TextView>(R.id.region)
        textView.text = Html.fromHtml("<b>Type</b>: ${landpad?.region}")
    }
}