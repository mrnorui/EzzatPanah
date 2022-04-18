package academy.nouri.mvp.ui.main

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.databinding.ActivityMainBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.utils.Constants
import academy.nouri.mvp.utils.isNetworkAvailable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : BaseActivity(), MainContracts.View {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //presenter
    private val presenter by lazy { MainPresenter(this) }

    //Other
    private val moviesAdapter by lazy { MoviesAdapter(moviesList) }
    private val moviesList: MutableList<ResponseMoviesList.Data> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Call movies api
        presenter.callMoviesList(1)
        //Call search api
        binding.searchImg.setOnClickListener {
            val name = binding.searchEdt.text.toString()
            if (name.isNotEmpty()) {
                presenter.callSearchMovie(name, 1)
            }else{
                presenter.callMoviesList(1)
            }
        }
    }

    override fun loadMoviesList(data: MutableList<ResponseMoviesList.Data>) {
        binding.apply {
            emptyTxt.visibility = View.GONE
            moviesRecycler.visibility = View.VISIBLE

            moviesList.clear()
            moviesList.addAll(data)

            binding.moviesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = moviesAdapter
            }
        }
    }

    override fun emptyList() {
        binding.apply {
            emptyTxt.visibility = View.VISIBLE
            moviesRecycler.visibility = View.GONE
        }
    }

    override fun checkNetworkConnection(): Boolean {
        return isNetworkAvailable()
    }

    override fun networkConnectionError() {
        Toast.makeText(this, "Check your internet", Toast.LENGTH_SHORT).show()
    }

    override fun responseError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.moviesLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.moviesLoading.visibility = View.GONE
    }
}