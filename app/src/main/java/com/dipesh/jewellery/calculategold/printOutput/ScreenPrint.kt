package com.dipesh.jewellery.calculategold.printOutput

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.dipesh.jewellery.calculategold.GoldPriceDataModel

class ScreenPrint(private val context: Context) : PrintOutput {

    override fun executePrint(goldPriceDataModel: GoldPriceDataModel): PrintResult {
        val dialogMessage = getDialogMessage(goldPriceDataModel)
        val builder = AlertDialog.Builder(context)
            .setTitle("Gold Price Box")
            .setMessage(dialogMessage)
        val alertDialog = builder.create()
        alertDialog.show()
        return PrintResult(success = "Dialog shown successfully")
    }

    private fun getDialogMessage(goldPriceDataModel: GoldPriceDataModel) : String {
        return "Gold Price(per gram) ${goldPriceDataModel.goldPrice}\n" +
                "Weight(grams) ${goldPriceDataModel.weight}\n" +
                "Total Price ${goldPriceDataModel.totalPrice}"
    }

}