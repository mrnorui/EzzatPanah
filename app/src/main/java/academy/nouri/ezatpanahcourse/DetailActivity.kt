package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.databinding.ActivityDetailBinding
import academy.nouri.ezatpanahcourse.utils.initRecyclerView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class DetailActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityDetailBinding

    //Other
    private val housesList: MutableList<HousesModel> = mutableListOf()
    private val housesAdapter by lazy { HousesAdapter(housesList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Get data
        intent.extras.let {
            //binding.detailEmailTxt.text = it?.getString("USER_EMAIL_KEY")
        }
        //Add items
        loadData()
        //View
        binding.apply {
            //Back
            detailBackImg.setOnClickListener { finish() }
            //RecyclerView
/*            detailList.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = housesAdapter
            }*/
            detailList.initRecyclerView(LinearLayoutManager(this@DetailActivity), housesAdapter)
        }
    }

    private fun loadData() {
        housesList.add(HousesModel(R.drawable.house1, 1000000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house2, 50000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house3, 70000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house4, 30000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house5, 25400000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house3, 78000000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house2, 4000000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house4, 90000000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house4, 90000000, "تهران - سعادت آباد"))
        housesList.add(HousesModel(R.drawable.house4, 90000000, "تهران - سعادت آباد"))
    }
}