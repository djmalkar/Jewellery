package com.dipesh.jewellery.di

import android.content.Context
import com.dipesh.jewellery.calculategold.printOutput.FilePrint
import com.dipesh.jewellery.calculategold.printOutput.PaperPrint
import com.dipesh.jewellery.calculategold.printOutput.PrintOutput
import com.dipesh.jewellery.calculategold.printOutput.ScreenPrint

class PrintOutputFactory(private val context: Context) {

    enum class PrintOutputType {
        FILE, PAPER, SCREEN
    }

    fun getPrintOutput (printOutputType: PrintOutputType) : PrintOutput {
        return when (printOutputType) {
            PrintOutputType.FILE -> FilePrint(context)
            PrintOutputType.PAPER -> PaperPrint()
            PrintOutputType.SCREEN -> ScreenPrint(context)
        }
    }
}