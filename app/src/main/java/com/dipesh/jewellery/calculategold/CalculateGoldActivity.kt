package com.dipesh.jewellery.calculategold

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dipesh.jewellery.R
import com.dipesh.jewellery.di.PrintOutputFactory
import com.dipesh.jewellery.di.ViewModelFactory
import com.dipesh.jewellery.login.model.UserType

class CalculateGoldActivity : AppCompatActivity() {

    companion object {
        private const val USER_TYPE = "USER_TYPE"
        private const val USER_NAME = "USER_NAME"
        fun newInstance(context: Context, userType : String, userName : String): Intent {
            val intent = Intent(context, CalculateGoldActivity::class.java)
            intent.putExtra(USER_TYPE, userType)
            intent.putExtra(USER_NAME, userName)
            return intent
        }
    }

    private val DISCOUNT_PERCENT = 2
    private val NO_DISCOUNT = 1

    private lateinit var welcome : TextView
    private lateinit var goldPrice : EditText
    private lateinit var weight : EditText
    private lateinit var totalPrice : TextView
    private lateinit var discount : TextView
    private lateinit var calculate : Button
    private lateinit var printScreen : Button
    private lateinit var printFile : Button
    private lateinit var printPaper : Button

    private lateinit var calculateGoldViewModel: CalculateGoldViewModel

    private lateinit var printOutputFactory: PrintOutputFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calculate_gold)

        printOutputFactory = PrintOutputFactory(this)

        initializeViews()

        calculateGoldViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(CalculateGoldViewModel::class.java)

        welcome.text = getUserName()
        discount.text = "Discount - ${getDiscount()}"

        addViewListeners()

        addViewModelObservers()
    }

    private fun initializeViews() {
        welcome = findViewById(R.id.welcome)
        goldPrice = findViewById(R.id.goldPrice)
        weight = findViewById(R.id.weight)
        totalPrice = findViewById(R.id.totalPrice)
        discount = findViewById(R.id.discount)
        calculate = findViewById(R.id.calculate)
        printScreen = findViewById(R.id.printScreen)
        printFile = findViewById(R.id.printFile)
        printPaper = findViewById(R.id.printPaper)
    }

    private fun addViewListeners() {
        calculate.setOnClickListener{
            calculateGoldViewModel.isDataValid(GoldPriceDataModel(
                getDoubleFromString(goldPrice.text.toString()),
                getDoubleFromString(weight.text.toString()),
                0.0,
                getDiscount(),
            ))
        }

        printScreen.setOnClickListener{
            executeOutput(PrintOutputFactory.PrintOutputType.SCREEN)
        }

        printFile.setOnClickListener{
            executeOutput(PrintOutputFactory.PrintOutputType.FILE)
        }

        printPaper.setOnClickListener{
            executeOutput(PrintOutputFactory.PrintOutputType.PAPER)
        }
    }

    private fun addViewModelObservers() {
        calculateGoldViewModel.goldEntryForm.observe(this@CalculateGoldActivity, Observer {
            val goldState = it ?: return@Observer

            if (goldState.goldPriceError != null) {
                goldPrice.error = getString(goldState.goldPriceError)
            }
            if (goldState.weightError != null) {
                weight.error = getString(goldState.weightError)
            }
            if (goldState.isDataValid) {
                calculateGoldViewModel.calculateTotalPrice(
                    goldState.goldPriceDataModel!!,
                    getUserType()
                )
            }
        })

        calculateGoldViewModel.calculateResult.observe(this@CalculateGoldActivity, Observer {
            val goldPriceDataModel = it ?: return@Observer

            totalPrice.text = "Total Price = ${goldPriceDataModel.totalPrice}"
        })
    }

    private fun executeOutput(printOutputType: PrintOutputFactory.PrintOutputType) {
        calculateGoldViewModel.calculateResult.value?.let { it ->
            val printOutput = printOutputFactory.getPrintOutput(printOutputType).executePrint(it)
            if(!printOutput.success.isNullOrEmpty()) {
                Toast.makeText(applicationContext, printOutput.success, Toast.LENGTH_SHORT).show()
            } else if(printOutput.error != null) {
                Toast.makeText(applicationContext, printOutput.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUserType(): UserType {
        when(intent.getStringExtra(USER_TYPE)) {
            UserType.PRIVILEGED.toString() -> return UserType.PRIVILEGED
            UserType.REGULAR.toString() -> return UserType.REGULAR
        }
        throw Exception("Unknown Type")
    }

    private fun getUserName(): String {
        return "Welcome ${intent.getStringExtra(USER_NAME)}"
    }

    private fun getDoubleFromString(value : String) : Double {
        return if(value.isNullOrEmpty()){
            0.0
        } else {
            value.toDouble()
        }
    }

    private fun getDiscount(): Int {
        if(intent.getStringExtra(USER_TYPE).equals(UserType.PRIVILEGED.toString())) {
            return DISCOUNT_PERCENT
        } else {
            return NO_DISCOUNT
        }
    }
}