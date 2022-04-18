package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.detail.ResponseDetailMovie
import academy.nouri.mvp.databinding.ActivityDetailBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.utils.Constants
import academy.nouri.mvp.utils.isNetworkAvailable
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.load

class DetailActivity : BaseActivity(), DetailContracts.View {
    //Binding
    private lateinit var binding: ActivityDetailBinding

    //Presenter
    private val presenter by lazy { DetailPresenter(this) }

    //Other
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Intent
        intent.extras?.let {
            movieId = it.getInt(Constants.MOVIE_ID)
            //Call api
            if (movieId > 0) {
                presenter.callDetailMovie(movieId)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun loadDetailMovie(data: ResponseDetailMovie) {
        binding.apply {
            movieImg.load(data.poster) {
                crossfade(true)
                crossfade(1000)
            }
            movieTitle.text = data.title
            movieDesc.text = "${data.writer}\n${data.actors}"
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
        binding.movieLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.movieLoading.visibility = View.GONE
    }
}