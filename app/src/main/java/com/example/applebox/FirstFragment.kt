package com.example.applebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn:Button
    val bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        edt1 = view.findViewById(R.id.edt1)
        edt2 = view.findViewById(R.id.edt2)
        btn = view.findViewById(R.id.btn)
        btn.setOnClickListener {
            if(edt1.text.toString().toInt()<edt2.text.toString().toInt()){
                bundle.putString("Min",edt1.text.toString())
                bundle.putString("Max",edt2.text.toString())
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
            }else{
                Toast.makeText(activity, "An incorrect number has been entered", Toast.LENGTH_SHORT).show()
            }
        }
    }

}