package com.github.ramiz.sampleapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.ramiz.nameinitialscircleimageview.NameInitialsCircleImageView
import com.materialdesign.ramiz.sampleapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

//import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val imageUrl = "http://i.imgur.com/DvpvklR.png"
        //image info config with valid image url so this should display image
        val imageInfo1: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("RR")
                .setTextColor(android.R.color.primary_text_dark)
                .setImageUrl(imageUrl)
                .setCircleBackgroundColorRes(android.R.color.holo_orange_dark)
                .build();
        initialsCircleImageView1.setImageInfo(imageInfo1)

        //image info config with image url null, so this should display only text
        val imageInfo2: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("RR")
                .setTextColor(android.R.color.primary_text_dark)
                .setCircleBackgroundColorRes(android.R.color.holo_orange_dark)
                .build();
        initialsCircleImageView2.setImageInfo(imageInfo2)


        //image info config with image url null, so this should display only text
        val imageInfo3: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("RR")
                .setTextFont(R.font.aguafina_script)
                .build();
        initialsCircleImageView3.setImageInfo(imageInfo3)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
