package com.lemi.prix_finance_v3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class ChangeLanguage : AppCompatActivity() {

    //declaring buttons
    private lateinit var rdbtnEnglish : RadioButton
    private lateinit var rdbtnIsiXhosa : RadioButton
    private lateinit var rdbtnAfrikaans: RadioButton
    private lateinit var btnAcceptChanges: Button

    //declaring navigation boue
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContentView(R.layout.activity_change_language)

          ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drLayoutChangeLanguage)) { v, insets ->
              val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
              v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
              insets
          }

          //finding all views in current page
          drawerLayout = findViewById(R.id.drLayoutChangeLanguage)
          bottomNavigationView = findViewById(R.id.bottomNavChangeLanguage)
          navView = findViewById(R.id.navView_changeLanguage)
          rdbtnEnglish = findViewById(R.id.rdbtnEnglish)
          rdbtnIsiXhosa = findViewById(R.id.rdbtnIsiXhosa)
          rdbtnAfrikaans = findViewById(R.id.rdbtnAfrikaans)
          btnAcceptChanges = findViewById(R.id.btnAcceptChanges)


          // Setup toolbar
          setSupportActionBar(findViewById(R.id.toolBarChangeLanguage))
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

          val radioGroup = findViewById<RadioGroup>(R.id.rdbtnGroup)
          radioGroup.setOnCheckedChangeListener { _, checkedId ->
              when (checkedId) {
                  R.id.rdbtnEnglish -> {
                      // Set selected language to English
                      setLocale("en")
                  }

                  R.id.rdbtnIsiXhosa -> {
                      // Set selected language to English
                      setLocale("xh")
                  }

                  R.id.rdbtnAfrikaans -> {
                      // Set selected language to English
                      setLocale("af")
                  }
              }
          }
          btnAcceptChanges.setOnClickListener{
              //restart the view on the click of this button,
              //i believe i set it to take user to dashboard
              restartActivity()
          }
      }
    //controls the navigation, sliding one
    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    // Function to change the locale (language) of the app
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    // Function to restart the activity after applying language change
    private fun restartActivity() {
        val intent = Intent(this, MainActivity::class.java)
        finish()  // Finish the current activity
        startActivity(intent)  // Start the activity again
    }
}