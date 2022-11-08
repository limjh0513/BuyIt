package kr.hs.dgsw.jaehyeon.util

import java.text.DecimalFormat

object PriceHelper {
    fun getPriceComma(price: Int):String{
        val decFormat: DecimalFormat = DecimalFormat("###,###")
        return decFormat.format(price)
    }
}