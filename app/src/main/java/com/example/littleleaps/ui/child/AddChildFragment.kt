package com.example.littleleaps.ui.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.littleleaps.databinding.FragmentAddChildBinding

class AddChildFragment : Fragment() {

    private var _binding: FragmentAddChildBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addChildViewModel =
            ViewModelProvider(this).get(AddChildViewModel::class.java)

        _binding = FragmentAddChildBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addChildViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}