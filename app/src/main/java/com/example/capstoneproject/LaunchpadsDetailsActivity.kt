package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView

class LaunchpadsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launchpads_details)

        val bundle = intent.getBundleExtra("Bundle")
        val launchpad = bundle?.getParcelable<Launchpad>("Launchpad")
        if (launchpad != null) {
            this.title = "Launchpad: ${launchpad.name} Details"
        } else {
            this.title = "Launchpad Details"
        }

        var textView = findViewById<TextView>(R.id.name)
        textView.text = Html.fromHtml("<b>Name</b>: ${launchpad?.name}")

        textView = findViewById<TextView>(R.id.details)
        textView.text = Html.fromHtml("<b>Details</b>: ${launchpad?.details}")

        textView = findViewById<TextView>(R.id.locality)
        textView.text = Html.fromHtml("<b>Type</b>: ${launchpad?.locality}")

        textView = findViewById<TextView>(R.id.region)
        textView.text = Html.fromHtml("<b>Type</b>: ${launchpad?.region}")
    }
}