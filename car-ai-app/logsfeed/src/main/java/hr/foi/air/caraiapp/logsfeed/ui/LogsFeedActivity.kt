package hr.foi.air.caraiapp.logsfeed.ui

import hr.foi.air.caraiapp.core.R as coreR
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import hr.foi.air.caraiapp.core.getLoggedInUser
import hr.foi.air.caraiapp.core.saveUser
import hr.foi.air.caraiapp.logsfeed.R
import hr.foi.air.caraiapp.logsfeed.databinding.ActivityLogsFeedBinding
import hr.foi.air.caraiapp.logsfeed.managers.DataPresenterManager

class LogsFeedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityLogsFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogsFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeLayout()
        initializeDataPresenterManager()
    }

    private fun initializeDataPresenterManager() {
        DataPresenterManager
            .setDependencies(this, binding.navView, binding.drawerLayout)
            .initializeDataPresenters()
            .showMainDataPresenter()

        binding.navView
            .getHeaderView(0)
            .findViewById<TextView>(R.id.hello_username)
            .text = getString(R.string.hello_username, getLoggedInUser().orEmpty())
    }

    private fun initializeLayout() {
        binding.layoutMain.toolbar.setTitleTextColor(getColor(coreR.color.white))
        setSupportActionBar(binding.layoutMain.toolbar)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.layoutMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_log_out -> {
                saveUser(username = null)
                startActivity(Intent("hr.foi.air.caraiapp.authentication.ui.open").setPackage(this.packageName))
                finish()
            }
            R.id.menu_about -> {
                Toast.makeText(
                    this,
                    "JCC - Marko Mušica, Karlo Gardijan, Josipa Meštrović",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }
}
