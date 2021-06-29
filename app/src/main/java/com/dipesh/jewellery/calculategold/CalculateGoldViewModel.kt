package com.dipesh.jewellery.calculategold

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dipesh.jewellery.R
import com.dipesh.jewellery.login.model.UserType
import com.dipesh.jewellery.repo.LoginRepository

class CalculateGoldViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _goldEntryForm = MutableLiveData<GoldEntryFormState>()
    val goldEntryForm: LiveData<GoldEntryFormState> = _goldEntryForm

    private val _calculateResult = MutableLiveData<GoldPriceDataModel>()
    val calculateResult: LiveData<GoldPriceDataModel> = _calculateResult

    fun calculateTotalPrice(goldPriceModel : GoldPriceDataModel, userType: UserType) {

        var totalPrice = goldPriceModel.goldPrice * goldPriceModel.weight

        when (userType) {
            UserType.PRIVILEGED -> {
                val discount = (goldPriceModel.discount * totalPrice / 100)
                totalPrice -= discount
            }
        }

        goldPriceModel.totalPrice = String.format("%.2f", totalPrice).toDouble()

        _calculateResult.value = goldPriceModel
    }

    fun isDataValid(goldPriceModel : GoldPriceDataModel) {
        when {
            goldPriceModel.goldPrice == 0.0 -> {
                _goldEntryForm.value = GoldEntryFormState(goldPriceError = R.string.invalid_gold_price)
            }
            goldPriceModel.weight == 0.0 -> {
                _goldEntryForm.value = GoldEntryFormState(weightError = R.string.invalid_weight)
            }
            else -> {
                _goldEntryForm.value = GoldEntryFormState(isDataValid = true, goldPriceDataModel = goldPriceModel)
            }
        }
    }
}