package com.github.coronatracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.coronatracker.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.response.view.*
import kotlinx.android.synthetic.main.value_response.*
import kotlinx.android.synthetic.main.value_response.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainFragment : Fragment(), CoroutineScope by MainScope() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        launch {
            val data = viewModel.getCoronaData()
            data.brazil.reverse()
            val context = context ?: return@launch
            responses.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = data.brazil.adapt(context, R.layout.response) { response ->
                    date.text = response.date
                    time.text = response.time
                    values.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = response.values.adapt(context, R.layout.value_response) { value ->
                            value.cases?.let{
                                cases.run {
                                    text = "$it ${if(it == 1) "caso" else "casos"}"
                                    visibility = View.VISIBLE
                                }
                            }
                            value.comments?.let {
                                comments.run {
                                    text = it
                                    visibility = View.VISIBLE
                                }
                            }
                            value.suspects?.let{
                                suspects.run{
                                    text = "$it ${if(it == 1) "suspeito" else "suspeitos"}"
                                    visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }

            message.text = viewModel.getCoronaData().toString()
        }
    }

}




