/*
 * MainPresenter.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 12:39 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.presenter

import com.utsman.mobileguide.iView.iMainView
import com.utsman.mobileguide.iNotice.iNoticeMain
import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

class MainPresenter(private var mainView: iMainView, private var noticeMain: iNoticeMain)
    : iMainPresenter, iNoticeMain.OnCompleteListener {

    override fun requestData() {
        noticeMain.getNoticeMain(this)
    }

    override fun onComplete(list: MutableList<Document>) {
        mainView.showDataToRecyclerView(list)
        mainView.offRefresh()
    }

    override fun onFailure(onFailureListener: Exception) {
        mainView.onFailure(onFailureListener)
        mainView.offRefresh()
    }

}