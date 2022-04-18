package academy.nouri.mvp.ui.register

import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import academy.nouri.mvp.ui.base.BasePresenter
import academy.nouri.mvp.ui.base.BaseView

interface RegisterContracts {
    interface View : BaseView {
        fun loadUserInfo(data: ResponseRegister)
        fun gotoMainPage()
    }

    interface Presenter : BasePresenter {
        fun callUserRegister(body: BodyRegister)
    }
}