package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.databinding.ActivityNavigationMenuBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class NavigationMenu : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNavigationMenuBinding

    //Other
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Set navigation
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}