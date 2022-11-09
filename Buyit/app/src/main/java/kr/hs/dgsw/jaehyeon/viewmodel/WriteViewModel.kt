package kr.hs.dgsw.jaehyeon.viewmodel

import android.app.Application
import android.content.Context
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
import kr.hs.dgsw.jaehyeon.view.activity.WriteActivity
import java.lang.Exception

class WriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val reason = MutableLiveData<String>()
    val link = MutableLiveData<String>()

    private val _onSuccessEvent = MutableLiveData<Unit>()
    val onSuccessEvent: LiveData<Unit> get() = _onSuccessEvent
    private val _onErrorEvent = MutableLiveData<Exception>()
    val onErrorEvent: LiveData<Exception> get() = _onErrorEvent

    fun insertProduct() {

        Log.e("adsf", "function")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = repository.insertProduct(
                    Product(
                        null,
                        name.value!!,
                        Integer.parseInt(price.value!!),
                        reason.value!!,
                        link.value!!,
                        0
                    )
                )
                _onSuccessEvent.postValue(result)
                Log.e("adsf", "$result")
            } catch (e: Exception) {
                _onErrorEvent.postValue(e)
                e.printStackTrace()
            }
        }
    }
}