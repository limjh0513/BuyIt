package kr.hs.dgsw.jaehyeon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.jaehyeon.model.entity.Product
import kr.hs.dgsw.jaehyeon.model.repository.ProductRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun getAllProduct(){
        _products.value = repository.getAllProduct()
    }
}