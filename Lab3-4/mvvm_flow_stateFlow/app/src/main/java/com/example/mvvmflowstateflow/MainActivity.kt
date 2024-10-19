package com.example.mvvmflowstateflow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmflowstateflow.Products.View.AllProductActivity
import com.example.mvvmflowstateflow.favorite.view.FavActivity




class MainActivity : AppCompatActivity() {

    private lateinit var introImg: ImageView
    private lateinit var btnAllProducts: Button
    private lateinit var btnFavProducts: Button
    private lateinit var btnExit: Button
    private var thumbnail: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAllProducts = findViewById(R.id.btnGetAll)
        btnFavProducts = findViewById(R.id.btnGetFav)
        btnExit = findViewById(R.id.btnExitID)
        thumbnail = R.drawable.products
        introImg = findViewById(R.id.imageView2)
        introImg.setImageResource(thumbnail)

        btnAllProducts.setOnClickListener {
            val intent = Intent(this@MainActivity, AllProductActivity::class.java)
            startActivity(intent)
        }

        btnFavProducts.setOnClickListener {
            val intent = Intent(this@MainActivity, FavActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener { finish() }
    }
}
