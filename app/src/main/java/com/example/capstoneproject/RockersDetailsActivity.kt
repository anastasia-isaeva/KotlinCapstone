package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RockersDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rockers_details)

        val bundle = intent.getBundleExtra("Bundle")
        val rocket = bundle?.getParcelable<Rockets>("Rocket")

        if (rocket != null) {
            this.title = "Rocket: ${rocket.name} Details"
        } else {
            this.title = "Rocket Details"
        }

        var textView = findViewById<TextView>(R.id.name)
        textView.text = Html.fromHtml("<b>Name</b>: ${rocket?.name}")

        textView = findViewById<TextView>(R.id.description)
        textView.text = Html.fromHtml("<b>Home Port</b>: ${rocket?.description}")

        val imageView = findViewById<ImageView>(R.id.image)
        Picasso.get().load(rocket?.imageUrl.toString()).into(imageView)

    }
}