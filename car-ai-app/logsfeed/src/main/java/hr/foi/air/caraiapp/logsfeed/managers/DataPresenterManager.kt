package hr.foi.air.caraiapp.logsfeed.managers

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import hr.foi.air.caraiapp.core.DataPresenter
import hr.foi.air.caraiapp.logsfeed.R
import hr.foi.air.caraiapp.logsfeed.ui.fragments.LogsFeedConsoleViewFragment
import hr.foi.air.caraiapp.logsfeed.ui.fragments.LogsFeedRecyclerViewFragment

object DataPresenterManager {

    private var dataPresenters: ArrayList<DataPresenter> = ArrayList()
    private lateinit var activity: AppCompatActivity
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    fun setDependencies(
        activity: AppCompatActivity,
        navView: NavigationView,
        drawerLayout: DrawerLayout,
    ): DataPresenterManager {
        DataPresenterManager.activity = activity
        DataPresenterManager.navView = navView
        DataPresenterManager.drawerLayout = drawerLayout

        return this
    }

    fun initializeDataPresenters(): DataPresenterManager {
        addDataPresenter(LogsFeedRecyclerViewFragment())
        addDataPresenter(LogsFeedConsoleViewFragment())
        return this
    }

    private fun addDataPresenter(dataPresenter: DataPresenter) {
        dataPresenters.add(dataPresenter)
        addPresenterToMenu(dataPresenter)
    }

    private fun addPresenterToMenu(dataPresenter: DataPresenter) {
        val context = activity.applicationContext
        val id = dataPresenters.indexOf(dataPresenter)

        navView.menu
            .add(R.id.dynamic_group, id, id + 1, dataPresenter.getName(context))
            .setIcon(dataPresenter.getIcon(context))
            .setCheckable(true)
            .setOnMenuItemClickListener {
                showDataPresenter(dataPresenter)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
    }

    private fun showDataPresenter(dataPresenter: DataPresenter) {
        if (activity.supportFragmentManager.findFragmentById(dataPresenter.getFragment().id) == null) {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, dataPresenter.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit()
        }
    }

    fun showMainDataPresenter() {
        showDataPresenter(dataPresenters[0])
    }
}