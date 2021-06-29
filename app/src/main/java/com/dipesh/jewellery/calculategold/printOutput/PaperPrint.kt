package com.dipesh.jewellery.calculategold.printOutput

import com.dipesh.jewellery.R
import com.dipesh.jewellery.calculategold.GoldPriceDataModel

class PaperPrint : PrintOutput {
    override fun executePrint(goldPriceDataModel: GoldPriceDataModel): PrintResult {
        return PrintResult(error = R.string.paper_print_error)
    }
}