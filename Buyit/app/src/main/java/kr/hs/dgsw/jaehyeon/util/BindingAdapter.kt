package kr.hs.dgsw.jaehyeon.util

import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.model.entity.Product


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setItemPriceText")
    fun setItemPriceText(textView: TextView, p: Product?) {
        if(p != null){
            var str: String = ""
            when (p.state) {
                0 -> str = "${PriceHelper.getPriceComma(p.price)}원\n고민중"
                1 -> str = "${PriceHelper.getPriceComma(p.price)}원\n구매함"
                2 -> str = "${PriceHelper.getPriceComma(p.price)}원\n구매하지 않음"
            }

            textView.text = str
        }
    }

    @JvmStatic
    @BindingAdapter("setItemPriceBackground")
    fun setItemPriceBackground(textView: TextView, state: Int?) {
        if (state != null) {
            when (state) {
                0 -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.background_think)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.color_think
                        )
                    )
                }
                1 -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.background_buy)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.color_buy
                        )
                    )
                }
                else -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.background_notbuy)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.color_notBuy
                        )
                    )
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setMoneyComma")
    fun setMoneyComma(textView: TextView, money: Int?) {
        if (money != null) {
            textView.text = "${PriceHelper.getPriceComma(money)}원"
        }
    }
}