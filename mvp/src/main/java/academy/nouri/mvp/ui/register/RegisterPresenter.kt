package academy.nouri.mvp.ui.register

import academy.nouri.mvp.data.model.base.ResponseError
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.services.ApiClient
import academy.nouri.mvp.ui.base.BasePresenterImpl
import academy.nouri.mvp.utils.applyIoScheduler
import com.google.gson.Gson

class RegisterPresenter constructor(private val view: RegisterContracts.View) :
    RegisterContracts.Presenter, BasePresenterImpl() {

    private val gson = Gson()

    override fun callUserRegister(body: BodyRegister) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .postRegister(body)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                view.loadUserInfo(itBody)
                                view.gotoMainPage()
                            }
                        }
                        422 -> {
                            val errorBody = gson.fromJson(
                                response.errorBody()?.charStream(),
                                ResponseError::class.java
                            )
                            view.responseError(errorBody.errors.toString())
                        }
                    }
                }, { error ->
                    view.hideLoading()
                    view.responseError(error.message.toString())
                })

        } else {
            view.networkConnectionError()
        }
    }
}