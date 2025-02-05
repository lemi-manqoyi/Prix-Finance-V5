package com.lemi.prix_finance_v3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var displayActiveBudgets : RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchBudgets: SearchView
    private lateinit var adapter: ItemAdapter
    object Global {
        var userId: Int? = null
    }

    private val items = mutableListOf(
        Item("Munch", 50.99, "Completed"),
        Item("Rent", 7500.00, "On Track"),
        Item("Utilities", 120.75, "Completed"),
        Item("Groceries", 3499.99, "Complete"),
        Item("Petrol", 1800.75, "Caution"),
        Item("Alcohol", 1500.00, "Caution"),
        Item("Insurance", 13000.75, "Completed")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //finding all views in current page
        drawerLayout = findViewById(R.id.activity_home)
        displayActiveBudgets = findViewById(R.id.recyclerViewHome)
        searchBudgets = findViewById(R.id.searchbar)
        bottomNavigationView = findViewById(R.id.bottomNav)
        navView = findViewById(R.id.navView_dashboard)


        // Setup toolbar
        setSupportActionBar(findViewById(R.id.toolBarDashboard))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Setup hamburger button click listener
        findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            toggleDrawer()
        }

        //setting up the recyclerview and navigators
        setupRecyclerView()

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


        displayActiveBudgets.setOnClickListener {
            // Handle RecyclerView click
            val position = displayActiveBudgets.getChildAdapterPosition(it)
            if (position != RecyclerView.NO_POSITION) {
                val item = items[position]
                Toast.makeText(this, "Clicked on ${item.title}", Toast.LENGTH_LONG).show()
            }
        }

        searchBudgets.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterItems(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterItems(newText ?: "")
                return true
            }
        })


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
    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    private fun filterItems(query: String) {
        val filteredItems = items.filter { item ->
            item.title.lowercase().contains(query.lowercase())
        }
        adapter.submitList(filteredItems)
    }

    private fun setupRecyclerView() {
        displayActiveBudgets.apply {
            adapter = ItemAdapter(items)
            displayActiveBudgets.adapter = adapter
            displayActiveBudgets.layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

}

