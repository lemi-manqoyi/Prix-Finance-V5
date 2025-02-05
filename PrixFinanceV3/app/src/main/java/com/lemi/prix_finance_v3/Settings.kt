package com.lemi.prix_finance_v3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Settings : AppCompatActivity() {

    //declaring the buttons
    private lateinit var btnChangeLanguage : Button
    private lateinit var btnAboutDevelopers: Button
    private lateinit var btnClearMyData : Button
    private lateinit var btnUpdateProfile : Button
    private lateinit var btnLogout : Button

    //declaring the navigators
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drLayoutSettings)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //finding all views in current page
        btnLogout = findViewById(R.id.btnLogout)
        btnClearMyData = findViewById(R.id.btnClearData)
        btnAboutDevelopers = findViewById(R.id.btnAboutDevelopers)
        btnChangeLanguage = findViewById(R.id.btnChangeLanguage)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)
        drawerLayout = findViewById(R.id.drLayoutSettings)
        bottomNavigationView = findViewById(R.id.bottomNavSettings)
        navView = findViewById(R.id.navView_settings)


        // Setup toolbar
        setSupportActionBar(findViewById(R.id.toolBarSettings))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Setup hamburger button click listener
        findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            toggleDrawer()
        }

        // Bottom navigation setup
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itmHome ->{ intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)}

                R.id.itmBudgetManagement -> {intent = Intent(this, Budget::class.java)
                    startActivity(intent)}

                R.id.itmGoals -> {intent = Intent(this, Goals::class.java)
                    startActivity(intent)}

                R.id.itmTransactions -> {intent = Intent(this, Transactions::class.java)
                    startActivity(intent)}
            }
            true
        }

        // Setup navigation view item selection
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itmDashboard -> {
                    // Handle dashboard action
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.itmBudgetManagement -> {
                    // Handle budget management action
                    startActivity(Intent(this, Budget::class.java))
                    true
                }
                R.id.itmTransactions -> {
                    // Handle transactions action
                    startActivity(Intent(this, Transactions::class.java))
                    true
                }
                R.id.itmGoals -> {
                    // Handle goals action
                    startActivity(Intent(this, Goals::class.java))
                    true
                }
                R.id.itmAboutDevelopers-> {
                    // Handle goals action
                    startActivity(Intent(this, AboutDevelopers::class.java))
                    true
                }
                R.id.itmSettings -> {
                    // Handle settings action
                    startActivity(Intent(this, Settings::class.java))
                    true
                }
                R.id.itmLogout -> {
                    // Handle logout action
                    true
                }
            }
            toggleDrawer()
            true
        }

        //routing button actions
       btnChangeLanguage.setOnClickListener {
            val intent = Intent(this, ChangeLanguage::class.java)
            startActivity(intent)
       }

       btnUpdateProfile.setOnClickListener {
           //to take user to profile
       }

       btnLogout.setOnClickListener {
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
       }

       btnClearMyData.setOnClickListener {
            //to clear data at user 2nd confirmation and logout
           //needing the user to register again as new user
       }

       btnAboutDevelopers.setOnClickListener {
            val intent = Intent(this, AboutDevelopers::class.java)
            startActivity(intent)
       }

    }
    //controls the open nd close function of the sliding navigation
    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

}