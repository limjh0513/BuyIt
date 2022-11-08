package kr.hs.dgsw.jaehyeon.model.repository

import android.content.Context
import androidx.lifecycle.Transformations.map
import androidx.room.Room
import kr.hs.dgsw.jaehyeon.model.database.ProductDataBase
import kr.hs.dgsw.jaehyeon.model.entity.Product

class ProductRepository(context: Context) {
    private val dao =
        Room.databaseBuilder(context, ProductDataBase::class.java, "product-database").build()
            .productDao()

    fun getAllProduct(): List<Product> =
        dao.getAll()

    fun getProductById(pid: Int): Product =
        dao.getProductById(pid)

    suspend fun updateProduct(product: Product) =
        dao.updateState(product)

    suspend fun insertProduct(product: Product) =
        dao.insertProduct(product)

    fun getBuyPrice(): Int {
        var result = 0

        for (p in dao.getAll().filter { it.state == 1 }) {
            result += p.price
        }

        return result
    }

    fun getNotBuyPrice(): Int {
        var result = 0

        for (p in dao.getAll().filter { it.state == 2 }) {
            result += p.price
        }

        return result
    }
}