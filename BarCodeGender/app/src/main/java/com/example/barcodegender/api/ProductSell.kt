package com.example.barcodegender.api

import com.google.gson.annotations.SerializedName

class  ProductSell {
    @SerializedName("BarCode")
    var BarCode: List<String>? = null
    @SerializedName("NameProduct")
    var NameProduct : String? = null
    @SerializedName("NumberStock")
    var NumberStock: List<Int>? = null
    @SerializedName("NumberSell")
    var NumberSell: List<Int>? = null
    @SerializedName("Price")
    var Price: List<String>? = null
    @SerializedName("PriceSell")
    var PriceSell: List<String>? = null
    @SerializedName("size")
    var size: List<String>? = null
    @SerializedName("NameShop")
    var NameShop : String? = null
    @SerializedName("NameAka")
    var NameAka : String? = null



//    @SerializedName("itemName")
//    var itemName: String? = null
//    @SerializedName("brand")
//    var brand: String? = null
//    @SerializedName("price")
//    var price: String? = null
//    @SerializedName("count")
//    var count: String? = null
}