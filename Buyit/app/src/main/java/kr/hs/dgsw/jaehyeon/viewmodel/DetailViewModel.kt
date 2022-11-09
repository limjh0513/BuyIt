package kr.hs.dgsw.jaehyeon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.jaehyeon.model.entity.Product
import kr.hs.dgsw.jaehyeon.model.repository.ProductRepository
import kr.hs.dgsw.jaehyeon.util.SingleLiveEvent
import java.lang.Exception

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    private val _productItem = MutableLiveData<Product>()
    val productItem: LiveData<Product> get() = _productItem
    private val _onProductErrorEvent = MutableLiveData<Exception>()
    val onProductErrorEvent: LiveData<Exception> get() = _onProductErrorEvent


    private val _updateSuccess = MutableLiveData<Unit>()
    val updateSuccess : LiveData<Unit> get() = _updateSuccess
    private val _onUpdateErrorEvent = MutableLiveData<Exception>()
    val onUpdateErrorEvent: LiveData<Exception> get() = _onUpdateErrorEvent

    fun getProductByPid(pid: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _productItem.postValue(repository.getProductById(pid))
            } catch (e: Exception) {
                e.printStackTrace()
                _onProductErrorEvent.postValue(e)
            }
        }
    }

    fun updateProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _updateSuccess.postValue(
                    repository.updateProduct(product))
            } catch (e: Exception) {
                e.printStackTrace()
                _onUpdateErrorEvent.postValue(e)
            }
        }
    }
}