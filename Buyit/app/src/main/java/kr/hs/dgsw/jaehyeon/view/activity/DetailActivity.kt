package kr.hs.dgsw.jaehyeon.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityDetailBinding
import kr.hs.dgsw.jaehyeon.model.entity.Product
import kr.hs.dgsw.jaehyeon.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: DetailViewModel

    var pid: Int = 0
    var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[DetailViewModel::class.java]

        getPid()

        binding.lifecycleOwner = this
        binding.executePendingBindings()
        binding.activity = this

        with(viewModel) {
            productItem.observe(this@DetailActivity, Observer {
                binding.product = it
                product = it
            })

            onProductErrorEvent.observe(this@DetailActivity, Observer {
                Toast.makeText(
                    this@DetailActivity,
                    "product data를 조회하는 중 문제가 발생했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            })

            updateSuccess.observe(this@DetailActivity, Observer {
                Toast.makeText(this@DetailActivity, "상태 수정 성공!", Toast.LENGTH_SHORT).show()
                viewModel.getProductByPid(pid)
            })

            onUpdateErrorEvent.observe(this@DetailActivity, Observer {
                Toast.makeText(this@DetailActivity, "상태 수정 중 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun moveToLink() {
        Log.e("adf", product?.link!!)
        if (product != null) {

            Log.e("adf", product?.link!!)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product!!.link))
            startActivity(intent)
        }
    }

    fun changeState(state: Int){
        if(product != null){
            product!!.state = state

            viewModel.updateProduct(product!!)
        }
    }

    private fun getPid() {
        pid = intent.getIntExtra("pid", 0)
        if (pid <= 0) {
            Toast.makeText(this, "pid를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            viewModel.getProductByPid(pid)
        }
    }


}