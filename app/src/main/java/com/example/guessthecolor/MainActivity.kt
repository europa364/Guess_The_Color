package com.example.guessthecolor

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
    }

    fun isScreenReaderOn(): Boolean {
        val am =
            applicationContext.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        if (am.isEnabled) {
            val serviceInfoList =
                am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
            if (serviceInfoList.isNotEmpty())
                return true
        }
        return false
    }

    private fun setUpViews(){

        //Get a list of colors in random order. This list is used to set colors to the tiles.
        val nameColorSet: List<Pair<String, Int>> = listOf(
            Pair("RED", Color.RED),
            Pair("GREEN", Color.GREEN),
            Pair("BLUE", Color.BLUE),
            Pair("YELLOW", Color.YELLOW)).shuffled()


        //we add this condition so that read out text is not affected when screen reader is enabled
        if(!isScreenReaderOn())
        {
            //Set the color name as content description for each of the four tiles
            val colorName1 = nameColorSet[0].first
            val colorName2 = nameColorSet[1].first
            val colorName3 = nameColorSet[2].first
            val colorName4 = nameColorSet[3].first

            findViewById<View>(R.id.tile_1).contentDescription = colorName1
            findViewById<View>(R.id.tile_2).contentDescription = colorName2
            findViewById<View>(R.id.tile_3).contentDescription = colorName3
            findViewById<View>(R.id.tile_4).contentDescription = colorName4

        }

        val colorName1 = nameColorSet[0].first
        val color1 = nameColorSet[0].second
        findViewById<View>(R.id.tile_1).setBackgroundColor(color1)
        findViewById<View>(R.id.tile_1).setOnClickListener{
            Toast.makeText(baseContext, colorName1, Toast.LENGTH_LONG).show()
        }

        val colorName2 = nameColorSet[1].first
        val color2 = nameColorSet[1].second
        findViewById<View>(R.id.tile_2).setBackgroundColor(color2)
        findViewById<View>(R.id.tile_2).setOnClickListener{
            Toast.makeText(baseContext, colorName2, Toast.LENGTH_SHORT).show()
        }

        val colorName3 = nameColorSet[2].first
        val color3 = nameColorSet[2].second
        findViewById<View>(R.id.tile_3).setBackgroundColor(color3)
        findViewById<View>(R.id.tile_3).setOnClickListener{
            Toast.makeText(baseContext, colorName3, Toast.LENGTH_SHORT).show()
        }

        val colorName4 = nameColorSet[3].first
        val color4 = nameColorSet[3].second
        findViewById<View>(R.id.tile_4).setBackgroundColor(color4)
        findViewById<View>(R.id.tile_4).setOnClickListener{
            Toast.makeText(baseContext, colorName4, Toast.LENGTH_SHORT).show()
        }

    }
}