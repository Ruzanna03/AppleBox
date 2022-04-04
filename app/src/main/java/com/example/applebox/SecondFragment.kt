package com.example.applebox

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*

class SecondFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var count: TextView
    lateinit var tvmax: TextView
    lateinit var btnAdd: Button
    lateinit var btnRemove: Button
    lateinit var btnReset: Button
    private var a = 0
    private var min = 0
    private var max = 0
    var isTrue = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_horizontal)
        count = view.findViewById(R.id.tv_count)
        tvmax = view.findViewById(R.id.tv_max)
        btnAdd = view.findViewById(R.id.plus)
        btnRemove = view.findViewById(R.id.minus)
        btnReset = view.findViewById(R.id.btn_reset)
        count.text = arguments?.getString("Min")
        a = count.text.toString().toInt()
        min = a
        max =  arguments?.getString("Max").toString().toInt()
        tvmax.text = max.toString()
        btnAdd.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                myThread()

            }
        }

    }
    suspend fun myThread(){
        download()
        withContext(Dispatchers.Main){
            btnAdd.setOnClickListener {
                if (a>=max){
                    btnReset.visibility = View.VISIBLE
                }else{
                    Thread.sleep(3000)
                    a++
                    count.text = a.toString()
                }
            }
            btnRemove.setOnClickListener {
                if (a<=0){
                    btnReset.visibility = View.VISIBLE
                }else{
                    Thread.sleep(3000)
                    a--
                    count.text = a.toString()
                }
            }
            btnReset.setOnClickListener {
                a = min
                btnReset.visibility = View.GONE
            }
        }
    }
    suspend fun download(){
        withContext(Dispatchers.Main) {
            count.text = "Preparing"
            btnAdd.isEnabled = false
        }
        delay(1000)
        for (i in 1..100) {
            delay(100)
            withContext(Dispatchers.Main) {
                count.text = "$i% Downloaded..."
                progressBar.progress = i
            }
        }
        withContext(Dispatchers.Main) {
            btnAdd.isEnabled = true
            a++
            count.text = a.toString()
        }
    }
}