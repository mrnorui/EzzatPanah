package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.data.model.detail.ResponseDetailMovie
import academy.nouri.mvp.ui.base.BasePresenter
import academy.nouri.mvp.ui.base.BaseView

interface DetailContracts {
    interface View : BaseView {
        fun loadDetailMovie(data: ResponseDetailMovie)
    }

    interface Presenter : BasePresenter {
        fun callDetailMovie(id: Int)
    }
}