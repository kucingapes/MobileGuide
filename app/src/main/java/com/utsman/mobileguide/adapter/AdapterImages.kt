/*
 * AdapterImages.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 10:42 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.utsman.mobileguide.R
import com.utsman.mobileguide.adapter.viewHolder.Holder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class AdapterImages(private var images: MutableList<String>) : RecyclerView.Adapter<Holder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemImagesUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val image = images[position]

        val imageView = holder.itemView.find(R.id.item_image) as ImageView
        Picasso.get().load(image).into(imageView)
    }

    class ItemImagesUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            cardView {
                radius = 0f
                lparams(wrapContent, wrapContent) {
                    margin = dip(6)
                }
                imageView {
                    id = R.id.item_image
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams(dip(120), dip(120))
            }
        }
    }
}