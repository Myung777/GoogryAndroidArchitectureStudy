package com.mtjin.androidarchitecturestudy.ui.splash

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun doSplash() {
        if (view.checkAutoLogin()) {
            view.showAutoLogin()
            view.goMovieSearch()
        } else {
            view.goLogin()
        }
    }
}