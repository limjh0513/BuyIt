package kr.hs.dgsw.jaehyeon.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.jaehyeon.model.entity.Product

object ProductDiffUtil {
    val diffUtil = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.pid == newItem.pid

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem

    }
}