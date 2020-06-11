package com.jonahstarling.idlepharmacy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.stats_bar.*

class MainFragment: Fragment(), DrugAdapter.OnDrugTappedListener {

    private lateinit var preferences: SharedPreferences
    private lateinit var drugAdapter: DrugAdapter
    private lateinit var drugLayoutManager: LinearLayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        piggyBank = PiggyBank(preferences.getInt("coins", 0), preferences.getInt("pills", 0))
        Drugs.loadDrugUnlocks(preferences)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drugAdapter = DrugAdapter(this.requireContext())
        drugAdapter.drugAdapterListener = this
        drugLayoutManager = LinearLayoutManager(context)
        drugList.adapter = drugAdapter
        drugList.layoutManager = drugLayoutManager

        updateStats()
    }

    override fun onStop() {
        val editor = preferences.edit()
        editor.putInt("coins", piggyBank.coins)
        editor.putInt("pills", piggyBank.pills)
        Drugs.saveDrugUnlocks(editor)
        editor.apply()

        super.onStop()
    }

    companion object {
        val TAG = MainFragment::class.java.simpleName
        var piggyBank = PiggyBank()

        fun newInstance() = MainFragment()
    }

    override fun onDrugTapped() {
        updateStats()
    }

    private fun updateStats() {
        val coinsString = "${piggyBank.coins} Coins"
        coins.text = coinsString
        val pillsString = "${piggyBank.pills} Pills Served"
        pillsServed.text = pillsString
    }
}

class PiggyBank(var coins: Int = 0, var pills: Int = 0)