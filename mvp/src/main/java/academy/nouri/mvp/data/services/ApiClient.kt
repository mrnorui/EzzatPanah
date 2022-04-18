package academy.nouri.mvp.data.services

import academy.nouri.mvp.BuildConfig
import academy.nouri.mvp.utils.Constants
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val apiServices: ApiServices

    init {
        //Gson
        val gson = GsonBuilder()
            .setLenient()
            .create()
        //Http Log
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        //Http builder
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.interceptors().add(logInterceptor)
        //Http client
        val client = clientBuilder
            .readTimeout(Constants.NETWORK_CONNECTION_TIME, TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_CONNECTION_TIME, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK_CONNECTION_TIME, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        //Api services
        apiServices = retrofit.create(ApiServices::class.java)
    }

    companion object {
        private var apiClient: ApiClient? = null

        fun getInstance(): ApiClient = apiClient ?: synchronized(this) {
            apiClient ?: ApiClient().also {
                apiClient = it
            }
        }
    }

    fun apiUseCase(): ApiUseCase {
        return ApiUseCase(apiServices)
    }
}