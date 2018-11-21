/*
 * HomeUi.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/20/18 10:05 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.fragment.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.view.Gravity
import android.view.View
import com.utsman.mobileguide.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class HomeUi<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                collapsingToolbarLayout {
                    id = R.id.collapsing_toolbar
                    isTitleEnabled = false
                    setExpandedTitleColor(resources.getColor(android.R.color.background_dark))
                    setCollapsedTitleTextColor(resources.getColor(android.R.color.background_dark))
                    setContentScrimResource(R.color.whiteBg)
                    verticalLayout {
                        backgroundColorResource = R.color.whiteBg
                        textView("Ayo eksplorasi bangunan-bangunan bersejarah di Kota Jakarta!") {
                            //ViewUtils.initText(this, ViewUtils.PLAYFAIR_REGULAR)
                            textSize = 18f
                        }.lparams(matchParent, wrapContent)

                        gravity = Gravity.BOTTOM
                        padding = dip(12)
                    }.lparams(matchParent, dip(70)) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }
                }.lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
            }.lparams(matchParent, wrapContent)

            swipeRefreshLayout {
                id = R.id.swipe_refresh
                recyclerView {
                    id = R.id.recycler_main
                }
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

        }
    }
}