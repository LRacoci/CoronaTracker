package com.github.coronatracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.coronatracker.R
import kotlinx.android.synthetic.main.brazil_item.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainFragment : Fragment(), CoroutineScope by MainScope() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        launch {
            val brazilData = viewModel.getCoronaData().brazil
            brazil_list.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): RecyclerView.ViewHolder = object : RecyclerView.ViewHolder(parent) {
                    init {
                        LayoutInflater.from(context).inflate(R.layout.brazil_item, parent)
                    }
                }
                fun bind(position: Int) {
                    testShowItem.text = brazilData[position].toString()
                }

                override fun getItemCount(): Int = brazilData.size

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
                    bind(position)

            }
            message.text = viewModel.getCoronaData().toString()
        }
        // TODO: Use the ViewModel
    }

}
