package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ShipDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_details)

        val bundle = intent.getBundleExtra("Bundle")
        val ship = bundle?.getParcelable<Ship>("Ship")

        if (ship != null) {
            this.title = "Ship: ${ship.name} Details"
        } else {
            this.title = "Ship Details"
        }

        var textView = findViewById<TextView>(R.id.name)
        textView.text = Html.fromHtml("<b>Name</b>: ${ship?.name}")

        textView = findViewById<TextView>(R.id.type)
        textView.text = Html.fromHtml("<b>Type</b>: ${ship?.type}")

        textView = findViewById<TextView>(R.id.homePort)
        textView.text = Html.fromHtml("<b>Home Port</b>: ${ship?.homePort}")

        textView = findViewById<TextView>(R.id.type)
        textView.text = Html.fromHtml("<b>Year Built</b>: ${ship?.yearBuilt.toString()}")

        val imageView = findViewById<ImageView>(R.id.image)
        Picasso.get().load(ship?.imageUrl.toString()).into(imageView)
    }
}