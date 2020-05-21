package com.airi.timer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airi.timer.databinding.FragmentMainBinding
import java.util.*

class MainFragment : Fragment() {
    var count = 0
    lateinit var binding: FragmentMainBinding
    private lateinit var mTimer: Timer
    private val mHandler = Handler()
    var time = 0
    var earning = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isTouch = false

        binding.start.setOnClickListener {
            binding.isTouch = true

            mTimer = Timer()
            mTimer.schedule(object : TimerTask() {
                override fun run() {
                    mHandler.post {
                        time++
                        val hour = time / 3600
                        val min = (time - hour * 3600) / 60
                        val sec = time % 60
                        val hourText = String.format("%02d", hour)
                        val minText = String.format("%02d", min)
                        val secText = String.format("%02d", sec)

                        binding.time.text = "${hourText}:${minText}:${secText}"

                        earning = earning + 11

                        binding.earn.text = "¥${earning}"


                    }
                }
            }, 1000, 1000)
        }
        binding.stop.setOnClickListener {
            binding.isTouch = false
            mTimer.cancel()

        }
        binding.save.setOnClickListener {
            binding.time.text = "00:00:00"

            // "DataStore"という名前でインスタンスを生成
            val pref: SharedPreferences = requireContext().getSharedPreferences("Data", Context.MODE_PRIVATE)
            var ttotal = pref.getInt("Time", 0)
            var mmoney = pref.getInt("Money", 0)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putInt("Time", ttotal+time)
            editor.putInt("Money", mmoney+earning)


            time = 0
            earning = 0
            binding.earn.text = "¥${earning}"

            editor.commit()

        }
        binding.reset.setOnClickListener {
            binding.time.text = "00:00:00"

            time = 0
            earning = 0
            binding.earn.text = "¥${earning}"

        }
        binding.move.setOnClickListener {
            //fragment遷移
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCalculateFragment())
        }
    }
}