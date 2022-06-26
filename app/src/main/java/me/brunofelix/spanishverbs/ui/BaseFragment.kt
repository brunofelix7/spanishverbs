package me.brunofelix.spanishverbs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewBinding> constructor(
    private val bindingInflater: (inflater: LayoutInflater) -> T
) : Fragment() {

    private var _binding: T? = null
    val binding: T get() = _binding as T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }

        return binding.root
    }

    abstract fun getFragmentView() : Int
}