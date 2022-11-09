package kr.hs.dgsw.jaehyeon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityWriteBinding
import kr.hs.dgsw.jaehyeon.viewmodel.WriteViewModel

class WriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityWriteBinding
    lateinit var viewModel: WriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[WriteViewModel::class.java]

        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        with(viewModel) {
            onSuccessEvent.observe(this@WriteActivity, Observer {
                Toast.makeText(this@WriteActivity, "등록 성공!", Toast.LENGTH_SHORT).show()
                this@WriteActivity.finish()
            })

            onErrorEvent.observe(this@WriteActivity, Observer {
                Toast.makeText(this@WriteActivity, "등록에 실패했습니다... 다시 시도해주세요", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    fun onClickInsertBtn() {
        Log.e("adsf", "sdaf")
        with(viewModel) {
            Log.e("adsf", "sdafdsafasdf")

            Log.e("dfas", "${name.value} ${price.value} ${reason.value} ${link.value}")
            if (name.value != "" && price.value != "" && reason.value != "" && link.value != "") {
                insertProduct()
                Log.e("adsf", "in")
            } else {
                Toast.makeText(this@WriteActivity, "이름, 가격, 이유를 모두 입력해주세요!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}