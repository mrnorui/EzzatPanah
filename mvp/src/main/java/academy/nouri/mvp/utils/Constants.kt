package academy.nouri.mvp.utils

import academy.nouri.mvp.data.model.register.ResponseRegister

object Constants {
    const val BASE_URL = "https://moviesapi.ir/api/v1/"
    const val NETWORK_CONNECTION_TIME = 60L
    const val MOVIE_ID = "movie_id"
    const val USER_TOKEN = "user_token"

    var RESPONSE_REGISTER : MutableList<ResponseRegister> = mutableListOf()
}