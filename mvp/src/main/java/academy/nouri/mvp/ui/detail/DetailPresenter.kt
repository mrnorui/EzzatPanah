package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.data.services.ApiClient
import academy.nouri.mvp.ui.base.BasePresenterImpl
import academy.nouri.mvp.utils.applyIoScheduler

class DetailPresenter constructor(private val view: DetailContracts.View) : DetailContracts.Presenter, BasePresenterImpl() {

    override fun callDetailMovie(id: Int) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getDetailMovie(id)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                view.loadDetailMovie(itBody)
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