package com.dipesh.jewellery.calculategold.printOutput

import android.content.Context
import com.dipesh.jewellery.R
import com.dipesh.jewellery.calculategold.GoldPriceDataModel
import java.io.File
import java.io.FileWriter

class FilePrint(private val context: Context) : PrintOutput {

    override fun executePrint(goldPriceDataModel: GoldPriceDataModel): PrintResult {
        val folder = File(context.cacheDir, "files")

        if(!folder.exists()) {
            folder.mkdir()
        }

        return try {
            val textFile = File(folder, "text.txt")
            val fileWriter = FileWriter(textFile)
            fileWriter.append(getFileMessage(goldPriceDataModel))
            fileWriter.flush()
            fileWriter.close()
            PrintResult(success = "File saved successfully at ${textFile.absolutePath}")
        } catch (exception : Exception) {
            PrintResult(error = R.string.write_file_error)
        }
    }

    private fun getFileMessage(goldPriceDataModel: GoldPriceDataModel) : String {
        return "Gold Price(per gram) ${goldPriceDataModel.goldPrice}\n" +
                "Weight(grams) ${goldPriceDataModel.weight}\n" +
                "Total Price ${goldPriceDataModel.totalPrice}"
    }

}