package academy.nouri.mvp.data.services

import academy.nouri.mvp.data.model.detail.ResponseDetailMovie
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import io.reactivex.Single
import retrofit2.Response

open class ApiUseCase(private val apiServices: ApiServices) {

    open fun getMoviesList(page: Int): Single<Response<ResponseMoviesList>> {
        return apiServices.moviesList(page)
    }

    open fun getDetailMovie(id: Int): Single<Response<ResponseDetailMovie>> {
        return apiServices.detailMovie(id)
    }

    open fun postRegister(body: BodyRegister): Single<Response<ResponseRegister>> {
        return apiServices.registerUser(body)
    }

    open fun getSearchMovie(name: String, page: Int): Single<Response<ResponseMoviesList>> {
        return apiServices.searchMovie(name, page)
    }
}