/*
 * MainUi.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/20/18 10:08 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.ui

import android.view.View
import android.view.ViewGroup
import com.utsman.mobileguide.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar

class MainUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            themedToolbar(R.style.ThemeOverlay_AppCompat) {
                backgroundColorResource = R.color.whiteBg
                id = R.id.toolbar
                title = context.getString(R.string.app_name)
            }.lparams(matchParent, dimenAttr(R.attr.actionBarSize))

            frameLayout {
                id = R.id.fragment_container
            }.lparams(matchParent, matchParent)
        }
    }
}