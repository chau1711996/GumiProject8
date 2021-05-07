package com.example.gumiproject8.ui.comics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.FragmentComicsBinding
import com.example.gumiproject8.model.mymodel.ProductModel
import com.example.gumiproject8.ui.MyAdapter
import com.example.gumiproject8.ui.MyViewModel
import com.example.gumiproject8.ui.detail.DetailFragment
import com.example.gumiproject8.utils.Constants
import com.example.gumiproject8.utils.gone
import com.example.gumiproject8.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComicsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    var binding: FragmentComicsBinding? = null
    val myViewModel: MyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comics, container, false)
        binding?.comic = myViewModel
        binding?.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(requireContext(), "Comics resume", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comicsAdapter = MyAdapter(){
            activity?.apply {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment_container,
                        DetailFragment.newInstance(ProductModel(it, Constants.COMIC))
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }

        val gridManager = GridLayoutManager(requireContext(), 2)
        binding?.apply {
            comicsRec.apply {
                adapter = comicsAdapter
                layoutManager = gridManager
            }
        }

        //get all comic
        param1?.let {
            myViewModel.getData(it)
        }

        //submitList khi có data
        myViewModel.data.observe(requireActivity()){
            it?.let {
                comicsAdapter.submitList(it)
            }
        }

        //loadding hiện khi chưa có data
        myViewModel.progressLiveData.observe(requireActivity()) {
            it?.let {
                if (it) binding?.progressBar?.visible() else binding?.progressBar?.gone()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            ComicsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}