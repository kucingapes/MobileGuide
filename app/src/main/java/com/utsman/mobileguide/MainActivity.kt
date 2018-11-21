package com.utsman.mobileguide

import android.os.Bundle
import android.support.multidex.MultiDex
import android.widget.FrameLayout
import com.kucingapes.ankodrawer.AnDrawer
import com.kucingapes.ankodrawer.AnDrawerClickListener
import com.kucingapes.ankodrawer.AnDrawerInit
import com.kucingapes.ankodrawer.AnDrawerView.anDrawerLayoutWithToolbar
import com.utsman.mobileguide.fragment.FragmentHome
import com.utsman.mobileguide.ui.MainUi
import org.jetbrains.anko.appcompat.v7.contentFrameLayout
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), AnDrawerClickListener {

    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MultiDex.install(this)

        val drawer = AnDrawer(this, R.color.colorPrimary)
        contentFrameLayout { anDrawerLayoutWithToolbar(drawer, drawerStatusBarColor = R.color.whiteBg) }
        AnDrawerInit.setupMainView(this, MainUi())
        AnDrawerInit.customToolbar(this, find(R.id.toolbar), R.drawable.ic_custom_navigation)

        container = find(R.id.fragment_container)
        replaceFragment(FragmentHome())

    }
}