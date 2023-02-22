package hr.foi.air.caraiapp.managers

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import hr.foi.air.caraiapp.R
import hr.foi.air.caraiapp.core.DataPresenter
import hr.foi.air.caraiapp.fragments.LogsFeedRecyclerViewFragment

//SINGLETON CLASS
class DataPresenterManager private constructor(){
    companion object
    {
        private var instance : DataPresenterManager = DataPresenterManager()
        fun getInstance() : DataPresenterManager {
            return instance
        }
    }
    private var dataPresenters : ArrayList<DataPresenter> = ArrayList()
    private lateinit var activity : AppCompatActivity
    private lateinit var navView : NavigationView
    private lateinit var drawerLayout : DrawerLayout


    fun setDependencies(activity: AppCompatActivity, navView: NavigationView, drawerLayout: DrawerLayout)
    : DataPresenterManager
    {
        this.activity = activity
        this.navView = navView
        this.drawerLayout = drawerLayout

        return this
    }
    fun initializeDataPresenters() : DataPresenterManager
    {
        addDataPresenter(LogsFeedRecyclerViewFragment())
        addDataPresenter(LogsFeedRecyclerViewFragment())
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
            .add(R.id.dynamic_group, id, id+1, dataPresenter.getName(context))
            .setIcon(dataPresenter.getIcon(context))
            .setCheckable(true)
            .setOnMenuItemClickListener {
                showDataPresenter(dataPresenter)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
    }

    private fun showDataPresenter(dataPresenter: DataPresenter) {

        if(activity.supportFragmentManager.findFragmentById(dataPresenter.getFragment().id) == null){
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment,dataPresenter.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit()
        }
        //add setdata if needed
    }
    fun showMainDataPresenter(){
        showDataPresenter(dataPresenters[0])
    }
}