package academy.nouri.mvp.data.services

import academy.nouri.mvp.data.model.detail.ResponseDetailMovie
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @GET("movies")
    fun moviesList(@Query("page") page: Int): Single<Response<ResponseMoviesList>>

    @GET("movies/{movie_id}")
    fun detailMovie(@Path("movie_id") id: Int): Single<Response<ResponseDetailMovie>>

    @POST("register")
    fun registerUser(@Body body: BodyRegister): Single<Response<ResponseRegister>>

    @GET("movies")
    fun searchMovie(@Query("q") name: String, @Query("page") page: Int): Single<Response<ResponseMoviesList>>
}