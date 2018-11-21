/*
 * DetailUi.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:10 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.ui

import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.utsman.mobileguide.BaseView.mapsView
import com.utsman.mobileguide.R
import com.utsman.mobileguide.Utils
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

class DetailUi : AnkoComponent<AppCompatActivity> {
    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                fitsSystemWindows = true
                collapsingToolbarLayout {
                    id = R.id.collapsing_toolbar
                    setContentScrimResource(android.R.color.white)
                    imageView {
                        id = R.id.img_banner
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            transitionName = "banner"
                        }
                    }.lparams(matchParent, dip(300)) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }

                    val customSize = Utils.anGetStatusBarHeight(context) + dimenAttr(R.attr.actionBarSize)
                    themedToolbar(R.style.ThemeOverlay_AppCompat) {
                        backgroundColorResource = android.R.color.transparent
                        id = R.id.toolbar
                        title = ""
                        topPadding = Utils.anGetStatusBarHeight(context)
                    }.lparams(matchParent, customSize) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }
                }.lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
            }.lparams(matchParent, wrapContent)

            nestedScrollView {
                relativeLayout {

                    linearLayout {
                        padding = dip(12)
                        gravity = Gravity.CENTER_VERTICAL
                        //ViewUtils.ripple(this)
                        id = R.id.rating_view
                        textView("-") {
                            id = R.id.text_rating
                            textSize = 25f
                        }.lparams { margin = dip(6) }

                        imageView {
                            id = R.id.star_rating
                            //imageResource = R.drawable.ic_star_border
                        }.lparams(dip(30), dip(30))
                    }.lparams(wrapContent, wrapContent) {
                        alignParentRight()
                        margin = dip(10)
                    }

                    verticalLayout {
                        clipToPadding = false
                        textView {
                            id = R.id.title
                            //ViewUtils.initText(this, PLAYFAIR_BOLD, 20f)
                            textColorResource = android.R.color.background_dark
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(12)
                            leftMargin = dip(12)
                            rightMargin = dip(12)
                        }

                        textView {
                            id = R.id.address
                            //ViewUtils.initText(this, SERIF_REGULAR)
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                            leftMargin = dip(12)
                            rightMargin = dip(12)
                        }

                        textView {
                            id = R.id.desc_item
                            //ViewUtils.initText(this, SERIF_REGULAR)
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                            leftMargin = dip(12)
                            rightMargin = dip(12)
                        }

                        textView("Galeri Foto") {
                            //ViewUtils.initText(this, PLAYFAIR_BOLD, 20f)
                            textColorResource = android.R.color.background_dark
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(12)
                            leftMargin = dip(12)
                            rightMargin = dip(12)
                        }

                        recyclerView {
                            id = R.id.images
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            padding = dip(6)
                            itemAnimator = DefaultItemAnimator()
                            clipToPadding = false
                        }.lparams(wrapContent, wrapContent) {
                            bottomMargin = dip(12)
                        }

                        textView {
                            id = R.id.lokasi_title
                            //ViewUtils.initText(this, PLAYFAIR_BOLD, 20f)
                            textColorResource = android.R.color.background_dark
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(12)
                            leftMargin = dip(12)
                            rightMargin = dip(12)
                        }

                        cardView {
                            radius = 0f
                            cardElevation = 8f

                            mapsView().apply {
                                id = R.id.maps_view
                            }
                        }.lparams(matchParent, dip(300)) {
                            margin = dip(12)
                        }

                        cardView {
                            id = R.id.route_btn
                            radius = 0f
                            cardElevation = 8f
                            setCardBackgroundColor(resources.getColor(R.color.colorPrimary))
                            //ripple(this)
                            textView("Rekomendasi Transportasi"){
                                //ViewUtils.initText(this, ViewUtils.SANS_REGULAR)
                                textColorResource = android.R.color.white
                            }.lparams {
                                gravity = Gravity.CENTER
                            }

                        }.lparams(matchParent, dip(70)) {
                            margin = dip(12)
                        }

                    }.lparams(matchParent, matchParent)
                }.lparams(matchParent, matchParent)
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }
}