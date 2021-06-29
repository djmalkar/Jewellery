package com.dipesh.jewellery.calculategold.printOutput

import com.dipesh.jewellery.calculategold.GoldPriceDataModel

interface PrintOutput {

    fun executePrint(goldPriceDataModel: GoldPriceDataModel) : PrintResult

}