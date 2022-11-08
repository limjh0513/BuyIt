package kr.hs.dgsw.jaehyeon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityMainBinding
import kr.hs.dgsw.jaehyeon.view.adapter.ProductAdapter
import kr.hs.dgsw.jaehyeon.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val adapter by lazy {
        ProductAdapter(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProduct()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[MainViewModel::class.java]

        binding.activity = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        binding.mainRecycler.adapter = adapter

        with(viewModel){
            products.observe(this@MainActivity, Observer {
                adapter.submitList(it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    fun onClickChartBtn(){
        val intent = Intent(this, ChartActivity::class.java)
        startActivity(intent)
    }

    fun onClickAddBtn(){
        val intent = Intent(this, WriteActivity::class.java)
        startActivity(intent)
    }
}