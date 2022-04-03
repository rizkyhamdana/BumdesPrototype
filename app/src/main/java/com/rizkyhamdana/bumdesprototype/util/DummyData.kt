package com.rizkyhamdana.bumdesprototype.util

import com.rizkyhamdana.bumdesprototype.data.KedaiEntity
import com.rizkyhamdana.bumdesprototype.data.ProdukEntity

object DummyData {

    fun generateDummyKedai(): List<KedaiEntity>{
        val listKedai = ArrayList<KedaiEntity>()
        listKedai.add(
            KedaiEntity(
                "K001",
                "Kedai Alfianty",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K002",
                "Kedai Labaco",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K003",
                "Kedai Corner",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K001",
                "Kedai Alfianty",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K002",
                "Kedai Labaco",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K003",
                "Kedai Corner",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K001",
                "Kedai Alfianty",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K002",
                "Kedai Labaco",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        listKedai.add(
            KedaiEntity(
                "K003",
                "Kedai Corner",
                "file:///android_asset/placeholder_kedai.png"
            )
        )
        return listKedai
    }

    fun generateDummyProduk(): List<ProdukEntity>{
        val listProduk = ArrayList<ProdukEntity>()
        listProduk.add(
            ProdukEntity(
                "M001",
                "Nasi Goreng",
                1,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )
        listProduk.add(
            ProdukEntity(
                "M002",
                "Nasi Campur",
                1,
                "file:///android_asset/placeholder_produk.png",
                20000
            )
        )
        listProduk.add(
            ProdukEntity(
                "M003",
                "Mie Goreng",
                1,
                "file:///android_asset/placeholder_produk.png",
                10000
            )
        )
        listProduk.add(
            ProdukEntity(
                "M004",
                "Mie Kuah",
                1,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )

        listProduk.add(
            ProdukEntity(
                "D001",
                "Jus Jeruk",
                2,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )
        listProduk.add(
            ProdukEntity(
                "D002",
                "Jus Mangga",
                2,
                "file:///android_asset/placeholder_produk.png",
                20000
            )
        )
        listProduk.add(
            ProdukEntity(
                "D003",
                "Thai Tea",
                2,
                "file:///android_asset/placeholder_produk.png",
                10000
            )
        )
        listProduk.add(
            ProdukEntity(
                "D004",
                "Kopi Susu",
                2,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )

        listProduk.add(
            ProdukEntity(
                "C001",
                "Pisang Nugget",
                3,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )
        listProduk.add(
            ProdukEntity(
                "C002",
                "Roti Bakar",
                3,
                "file:///android_asset/placeholder_produk.png",
                20000
            )
        )
        listProduk.add(
            ProdukEntity(
                "C003",
                "Pisang Goreng",
                3,
                "file:///android_asset/placeholder_produk.png",
                10000
            )
        )
        listProduk.add(
            ProdukEntity(
                "C004",
                "Kentang Goreng",
                3,
                "file:///android_asset/placeholder_produk.png",
                15000
            )
        )
        return listProduk
    }

}