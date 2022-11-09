package kr.hs.dgsw.jaehyeon.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.jaehyeon.model.entity.Product
import kr.hs.dgsw.jaehyeon.model.repository.ProductRepository
import kr.hs.dgsw.jaehyeon.util.SingleLiveEvent
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    val onErrorEvent = SingleLiveEvent<Any>()

    fun getAllProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _products.postValue(repository.getAllProduct())
            } catch (e: Exception) {
                onErrorEvent.call()
            }
        }
    }
}