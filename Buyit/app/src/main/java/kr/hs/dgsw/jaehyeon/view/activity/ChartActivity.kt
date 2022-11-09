package kr.hs.dgsw.jaehyeon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityChartBinding
import kr.hs.dgsw.jaehyeon.util.PriceHelper
import kr.hs.dgsw.jaehyeon.viewmodel.ChartViewModel

class ChartActivity : AppCompatActivity() {

    lateinit var binding: ActivityChartBinding
    lateinit var viewModel: ChartViewModel

    private var bPrice: Int = 0
    private var nPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chart)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[ChartViewModel::class.java]

        binding.activity = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        with(viewModel) {
            buyPrice.observe(this@ChartActivity, Observer {
                bPrice = it
                binding.chartTvBuy.text = "${PriceHelper.getPriceComma(bPrice)}원"
                calcPrice()
            })

            notBuyPrice.observe(this@ChartActivity, Observer {
                nPrice = it
                binding.chartTvNotBuy.text = "${PriceHelper.getPriceComma(nPrice)}원"
                calcPrice()
            })

            onErrorEvent.observe(this@ChartActivity, Observer {
                Toast.makeText(this@ChartActivity, "분석 결과 조회 중 문제가 발생했습니다.", Toast.LENGTH_SHORT)
                    .show()
            })
        }
    }

    private fun calcPrice() {
        val result: Int = bPrice - nPrice
        val str = if (bPrice >= nPrice) {
            "총 ${result}원을\n사용했어요!"
        } else {
            "총 ${result * -1}원을\n아꼈어요!"
        }

        binding.chartTvTotal.text = "$str"
    }

}