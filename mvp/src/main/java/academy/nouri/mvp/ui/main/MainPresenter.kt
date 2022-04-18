package academy.nouri.mvp.ui.main

import academy.nouri.mvp.data.services.ApiClient
import academy.nouri.mvp.ui.base.BasePresenterImpl
import academy.nouri.mvp.utils.applyIoScheduler

class MainPresenter constructor(private val view: MainContracts.View) :
    MainContracts.Presenter, BasePresenterImpl() {

    override fun callMoviesList(page:Int) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getMoviesList(page)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                itBody.data?.let { itData ->
                                    if (itData.isNotEmpty()) {
                                        view.loadMoviesList(itData)
                                    } else {
                                        view.emptyList()
                                    }
                                }
                            }
                        }
                        401 -> {
                            //view.logoutUser()
                        }
                        in 400..499 -> {
                            //view.response400Errors()
                        }
                        in 500..599 -> {
                            //view.error()
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

    override fun callSearchMovie(name: String, page: Int) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getSearchMovie(name, page)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                itBody.data?.let { itData ->
                                    if (itData.isNotEmpty()) {
                                        view.loadMoviesList(itData)
                                    } else {
                                        view.emptyList()
                                    }
                                }
                            }
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