package academy.nouri.mvp.ui.main

import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.ui.base.BasePresenter
import academy.nouri.mvp.ui.base.BaseView

interface MainContracts {
    interface View : BaseView {
        fun loadMoviesList(data: MutableList<ResponseMoviesList.Data>)
        fun emptyList()
    }

    interface Presenter : BasePresenter {
        fun callMoviesList(page: Int)
        fun callSearchMovie(name: String, page: Int)
    }
}