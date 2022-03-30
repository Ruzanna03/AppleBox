package com.example.applebox

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class SecondFragment : Fragment() {
    lateinit var count: TextView
    lateinit var btnAdd: Button
    lateinit var btnRemove: Button
    lateinit var btnReset: Button
    private var a = 0
    private var min = 0
    private var max = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        count = view.findViewById(R.id.tv_count)
        btnAdd = view.findViewById(R.id.plus)
        btnRemove = view.findViewById(R.id.minus)
        btnReset = view.findViewById(R.id.btn_reset)
        count.text = arguments?.getString("Min")
        a = count.text.toString().toInt()
        min = a.toInt()
        max =  arguments?.getString("Max").toString().toInt()
        val task = MyTask()
        task.execute()

    }
    inner class MyTask() : AsyncTask<Unit, Int, String>() {
        override fun doInBackground(vararg p0: Unit?): String {
            btnAdd.setOnClickListener {
                if (a>=max){
                    btnReset.visibility = View.VISIBLE
                }else{
                    a++
                    publishProgress(a)
                }
            }
            btnRemove.setOnClickListener {
                if (a<=min){
                    btnReset.visibility = View.VISIBLE
                }else{
                    a--
                    publishProgress(a)
                }
            }
            btnReset.setOnClickListener {
                a = min
                publishProgress(a)
                btnReset.visibility = View.GONE
            }
            return ""
        }
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            count.text = values[0].toString()
        }

    }

}