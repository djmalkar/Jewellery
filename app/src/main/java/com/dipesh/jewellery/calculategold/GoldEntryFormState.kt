package com.dipesh.jewellery.calculategold

data class GoldEntryFormState(
    val goldPriceError: Int? = null,
    val weightError: Int? = null,
    val isDataValid: Boolean = false,
    val goldPriceDataModel: GoldPriceDataModel? = null
)
