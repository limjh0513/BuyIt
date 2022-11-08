package kr.hs.dgsw.jaehyeon.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "pid") val pid: Int?,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="price") val price: Int,
    @ColumnInfo(name="reason") val reason: String,
    @ColumnInfo(name="link") val link: String?,
    @ColumnInfo(name="state") val state: Int, // 0: 고민중, 1: 구매함, 2: 구매안함
)