/*
 * RouteUi.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 9:18 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.utsman.mobileguide.R
import com.utsman.mobileguide.Utils
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class RouteUi : AnkoComponent<AppCompatActivity> {
    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        verticalLayout {
            view {
            }.lparams(matchParent, Utils.anGetStatusBarHeight(context))
            coordinatorLayout {
                appBarLayout {
                    fitsSystemWindows = true
                    themedToolbar(R.style.ThemeOverlay_AppCompat) {
                        backgroundColorResource = R.color.whiteBg
                        id = R.id.toolbar
                        title = ""
                    }.lparams(matchParent, dimenAttr(R.attr.actionBarSize)) {
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
                    }
                }.lparams(matchParent, wrapContent)

                swipeRefreshLayout {
                    id = R.id.swipe_refresh

                    recyclerView {
                        lparams(matchParent, matchParent)
                        id = R.id.route_list
                        padding = dip(12)
                        clipToPadding = false
                    }
                }.lparams(matchParent, matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }.lparams(matchParent, matchParent)
        }
    }
}