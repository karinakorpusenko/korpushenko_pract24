package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class Screen : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        //Получаем navController
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
                ?: return
        val navController = host.navController

        // Настройка бокового меню
        val sideBar = findViewById<NavigationView>(R.id.navigation_view)
        sideBar.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        drawerLayout = findViewById(R.id.drawerLayout)
        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar) // Устанавливаем Toolbar как ActionBar
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) // Настраиваем ActionBar с NavController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Изменяем заголовок Toolbar в зависимости от текущего фрагмента
            supportActionBar?.title = when (destination.id) {
                R.id.fragment2 -> getString(R.string.f2)
                R.id.fragment3 -> getString(R.string.f3)
                R.id.fragment4 -> getString(R.string.f4)
                else -> getString(R.string.f1)
            }
        }
    }

            override fun onSupportNavigateUp(): Boolean {
                val navController =
                    findNavController(R.id.fragmentContainerView) // Получаем NavController
                return navController.navigateUp() || super.onSupportNavigateUp() // Да - предудщий экран. Нет - закрыть активность
            }

            override fun onCreateOptionsMenu(menu: Menu): Boolean {
                menuInflater.inflate(R.menu.top_right1, menu)
                return true
            }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                val navController = findNavController(R.id.fragmentContainerView)

                // Обработка нажатия кнопки гамбургера
                if (item.itemId == android.R.id.home) {
                    drawerLayout.openDrawer(GravityCompat.START) // Открываем боковое меню
                    return true
                }
//ажата ли кнопка гамбургера
                return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(
                    item
                )
            }
        }
