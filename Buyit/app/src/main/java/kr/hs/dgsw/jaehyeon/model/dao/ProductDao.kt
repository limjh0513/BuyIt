package kr.hs.dgsw.jaehyeon.model.dao

import androidx.room.*
import kr.hs.dgsw.jaehyeon.model.entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE pid = (:pid)")
    fun getProductById(pid: Int): Product

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(vararg product: Product)

    @Update
    suspend fun updateState(product: Product)
}