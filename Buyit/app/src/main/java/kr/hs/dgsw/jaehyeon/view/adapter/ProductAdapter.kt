package kr.hs.dgsw.jaehyeon.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ItemBuyitBinding
import kr.hs.dgsw.jaehyeon.model.entity.Product
import kr.hs.dgsw.jaehyeon.view.activity.DetailActivity
import kr.hs.dgsw.jaehyeon.view.adapter.diffutil.ProductDiffUtil

class ProductAdapter(val context: Context): ListAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffUtil.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemBuyitBinding>(LayoutInflater.from(parent.context), R.layout.item_buyit, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemBuyitBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.product = product
            binding.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("pid", product.pid)
                context.startActivity(intent)
            }
        }
    }
}