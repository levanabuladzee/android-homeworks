package abuladze.levan.afinal.viewmodel

import abuladze.levan.afinal.database.Coins
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val sync = MutableLiveData<String>()
    val allCoinsList = MutableLiveData<List<Coins>>()
    val favCoinsList = MutableLiveData<List<Coins>>()
    val dataSynced = MutableLiveData<Boolean>()

    fun sendSync(syncTime: String) {
        sync.value = syncTime
    }

    fun sendAllCoinsList(coins: List<Coins>) {
        allCoinsList.value = coins
    }

    fun sendFavCoinsList(coins: List<Coins>) {
        favCoinsList.value = coins
    }

    fun sendDataSynced(status: Boolean) {
        dataSynced.value = status
    }
}