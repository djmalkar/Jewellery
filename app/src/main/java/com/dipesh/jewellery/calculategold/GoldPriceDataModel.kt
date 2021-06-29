package com.dipesh.jewellery.calculategold

data class GoldPriceDataModel(
    val goldPrice: Double,
    val weight: Double,
    var totalPrice: Double,
    val discount: Int
)
