package kr.hs.dgsw.jaehyeon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.jaehyeon.model.repository.ProductRepository
import java.lang.Exception

class ChartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    private val _buyPrice = MutableLiveData<Int>()
    val buyPrice: LiveData<Int> get() = _buyPrice

    private val _notBuyPrice = MutableLiveData<Int>()
    val notBuyPrice: LiveData<Int> get() = _notBuyPrice

    private val _onErrorEvent = MutableLiveData<Exception>()
    val onErrorEvent: LiveData<Exception> get() = _onErrorEvent

    init {
        getBuyPrice()
        getNotBuyPrice()
    }

    fun getBuyPrice() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _buyPrice.postValue(repository.getBuyPrice())
            } catch (e: Exception) {
                _onErrorEvent.postValue(e)
            }
        }
    }

    fun getNotBuyPrice() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _notBuyPrice.postValue(repository.getNotBuyPrice())
            } catch (e: Exception) {
                _onErrorEvent.postValue(e)
            }
        }
    }
}