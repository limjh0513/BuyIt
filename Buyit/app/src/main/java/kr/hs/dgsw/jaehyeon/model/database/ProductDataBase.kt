package kr.hs.dgsw.jaehyeon.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.hs.dgsw.jaehyeon.model.dao.ProductDao
import kr.hs.dgsw.jaehyeon.model.entity.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}