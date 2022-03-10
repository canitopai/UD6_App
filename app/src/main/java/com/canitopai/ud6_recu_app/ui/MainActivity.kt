package com.canitopai.ud6_recu_app.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.canitopai.ud6_recu_app.R
import com.canitopai.ud6_recu_app.data.database.ProductDb
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var db: ProductDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext,ProductDb::class.java,"products")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

    }

}
val Fragment.db: ProductDb
    get() = (requireActivity() as MainActivity).db