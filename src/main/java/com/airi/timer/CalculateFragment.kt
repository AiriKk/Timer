package com.airi.timer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_calculate.*

class CalculateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculate, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref: SharedPreferences =
            requireContext().getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        var totalN = pref.getInt("Time", 0)
        var moneyN = pref.getInt("Money", 0)

        val hour = totalN / 3600
        val min = (totalN - hour * 3600) / 60
        val sec = totalN % 60
        val hourText = String.format("%02d", hour)
        val minText = String.format("%02d", min)
        val secText = String.format("%02d", sec)

        total.text = "${hourText}:${minText}:${secText}"
        money.text = "¥${moneyN}"



        if (moneyN > 1000000000) {
            about.text = "宝くじに当選したくらい稼いだよ"
        }else if (moneyN > 100000000) {
            about.text = "海外の島を買えるくらい稼いだよ"
        }else if (moneyN > 70000000) {
            about.text = "遊園地を貸し切れるくらい稼いだよ"
        }else if (moneyN > 50000000) {
            about.text = "一軒家を買えるくらい稼いだよ"
        } else if (moneyN > 6000000) {
            about.text = "ケーキ屋を始められるくらい稼いだよ"
        } else if (moneyN > 2000000) {
            about.text = "車を買えるくらい稼いだよ"
        } else if (moneyN > 230000) {
            about.text = "MacBookProを買えるくらい稼いだよ"
        } else if (moneyN > 150000) {
            about.text = "冷蔵庫を買えるくらい稼いだよ"
        } else if (moneyN > 60000) {
            about.text = "ランドセルを買えるくらい稼いだよ"
        } else if (moneyN > 50000) {
            about.text = "テレビを買えるくらい稼いだよ"
        } else if (moneyN > 10000) {
            about.text = "いい靴を買えるくらい稼いだよ"
        } else if (moneyN > 5000) {
            about.text = "美容室に行けるくらい稼いだよ"
        } else if (moneyN > 1000) {
            about.text = "Tシャツを買えるくらい稼いだよ"
        } else if (moneyN > 500) {
            about.text = "お弁当を買えるくらい稼いだよ"
        } else if (moneyN > 100) {
            about.text = "飲み物を買えるくらい稼いだよ"
        } else if (moneyN > 10) {
            about.text = "駄菓子を買えるくらい稼いだよ"
        }

        back.setOnClickListener {
            findNavController().popBackStack()
        }

        reset2.setOnClickListener {
            editor.putInt("Time", 0)
            editor.putInt("Money", 0)

            editor.commit()

            totalN = pref.getInt("Time", 0)
            moneyN = pref.getInt("Money", 0)

            total.text = "00:00:00"
            money.text = "¥0"
            about.text = ""
        }
    }
}