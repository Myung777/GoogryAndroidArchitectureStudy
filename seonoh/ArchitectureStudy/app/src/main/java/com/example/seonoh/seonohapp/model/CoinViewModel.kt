package com.example.seonoh.seonohapp.model

import android.util.Log
import androidx.databinding.ObservableField
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.example.seonoh.seonohapp.util.CalculateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinViewModel(private val repo : CoinRepositoryImpl) : BaseViewModel() {
    val coinItem = ObservableField<List<UseCoinModel>>()

    private fun handleError(throwable: Throwable) {
        Log.e("currentPriceInfo", "Main Network failed!! ${throwable.message}")
    }

    fun loadData(marketName: String) {

        repo.sendCurrentPriceInfo(marketName)
            .map(::refineData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(coinItem::set, ::handleError)
            .addCompositeDisposable()
    }

    private fun refineData(result: List<CurrentPriceInfoModel>): List<UseCoinModel> {
        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        val marketType = if (result.isNotEmpty()) {
            result[0].market.substringBefore("-")
        } else ""

        return result.map {
            var rate = CalculateUtils.setTradeDiff(it.signedChangeRate)["rate"].toString()
            var color = CalculateUtils.setTradeDiff(it.signedChangeRate)["color"].toString().toInt()

            UseCoinModel(
                CalculateUtils.setMarketName(it.market),
                CalculateUtils.filterTrade(it.tradePrice),
                rate,
                color,
                CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h)
            )
        }
    }
}