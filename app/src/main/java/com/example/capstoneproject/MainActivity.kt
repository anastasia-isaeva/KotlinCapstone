package com.example.capstoneproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSpaceXcompanyInfo()

        val button = findViewById<Button>(R.id.btn_go)
        button.setOnClickListener(){
            val intent = Intent(this, LandpadsActivity::class.java)
            startActivity(intent)
        }

        val radioButton = findViewById<RadioButton>(R.id.rb_ships)
        radioButton.setOnClickListener(){
            val intent = Intent(this, ShipsActivity::class.java)
            startActivity(intent)
        }


        val radioButtonLaunchpads = findViewById<RadioButton>(R.id.rb_launchpads)
        radioButtonLaunchpads.setOnClickListener(){
            val intent = Intent(this, LaunchpadsActivity::class.java)
            startActivity(intent)
        }

        val radioButtonLandpads = findViewById<RadioButton>(R.id.rb_landpads)
        radioButtonLandpads.setOnClickListener(){
            val intent = Intent(this, LandpadsActivity::class.java)
            startActivity(intent)
        }

        val radioButtonRockets = findViewById<RadioButton>(R.id.rb_rockets)
        radioButtonRockets.setOnClickListener(){
            val intent = Intent(this, RocketsActivity::class.java)
            startActivity(intent)
        }

        val radioButtonRoadster = findViewById<RadioButton>(R.id.rb_roadster)
        radioButtonRoadster.setOnClickListener(){
            val intent = Intent(this, RoadsterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getSpaceXcompanyInfo() {
        val queue = Volley.newRequestQueue(this)

        val builder = Uri.Builder()

        builder.scheme("https")
                .authority("api.spacexdata.com")
                .appendPath("v4")
                .appendPath("company")

        val url: String = builder.build().toString();

        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->

                var strResp = response.toString()
                val jsonObject: JSONObject = JSONObject(strResp)

                val headquarters = jsonObject.getJSONObject("headquarters")
                val address = headquarters.getString("address")
                val city = headquarters.getString("city")
                val state = headquarters.getString("state")

                val links = jsonObject.getJSONObject("links")
                val website = links.getString("website")

                val companyName = jsonObject.getString("name")
                val founderName = jsonObject.getString("founder")
                val yearFounded = jsonObject.getInt("founded")
                val numberOfEmployees = jsonObject.getInt("employees")
                val numberOfVehicles = jsonObject.getInt("vehicles")
                val numberOfLaunchSites = jsonObject.getInt("launch_sites")
                val numberOfTestSites = jsonObject.getInt("test_sites")
                val ceoName = jsonObject.getString("ceo")
                val summary = jsonObject.getString("summary")


                this.title = companyName
                var textView = findViewById<TextView>(R.id.companyName)
                textView.text = Html.fromHtml("<b>Company name</b>: ${companyName}")

                textView = findViewById(R.id.foundedInfo)
                textView.text = Html.fromHtml("<b>Founded</b>: in ${yearFounded} by ${founderName}")

                textView = findViewById(R.id.ceoInfo)
                textView.text = Html.fromHtml("<b>CEO</b>: ${ceoName}")

                textView = findViewById(R.id.employeesSummary)
                textView.text = Html.fromHtml("<b>Number of employees</b>: ${numberOfEmployees}")

                textView = findViewById(R.id.vehiclesSummary)
                textView.text = Html.fromHtml("<b>Number of vehicles</b>: ${numberOfVehicles}")

                textView = findViewById(R.id.launchSitesSummary)
                textView.text = Html.fromHtml("<b>Number of launch sites</b>: ${numberOfLaunchSites}")

                textView = findViewById(R.id.testSitesSummary)
                textView.text = Html.fromHtml("<b>Number of test sites</b>: ${numberOfTestSites}")

                textView = findViewById(R.id.headquartersInfo)
                textView.text = Html.fromHtml("<b>Headquarters location</b>:<br><sub><tt>${address}<br>${city}<br>${state}</tt></sub>")

                textView = findViewById(R.id.websiteInfo)
                textView.text = Html.fromHtml("<b>Official website</b>: ${website}")

                textView = findViewById(R.id.companySummary)
                textView.text = Html.fromHtml("<b>About</b>: ${summary}")
            },
            {  })
        queue.add(stringReq)
    }
}