package dev.echavez.cocktail.ui.First

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.echavez.cocktail.R
import dev.echavez.cocktail.adapter.DrinksAdapter
import dev.echavez.cocktail.core.Snack
import dev.echavez.cocktail.data.model.Drink
import dev.echavez.cocktail.data.model.TypeSnack
import dev.echavez.cocktail.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstViewModel
    private var _binding: FragmentFirstBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: DrinksAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init(){
        observers()
        listener()
        clear()
    }

    private fun observers(){
        viewModel.drinks.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                createRv()
            }else{
                Snack.build(binding.etName,"Is Empty", TypeSnack.SERVER).show()
            }
        }

        viewModel.exception.observe(viewLifecycleOwner){
            if(viewModel.exception.value != null){
                when(it.code){
                    404 -> Snack.build(binding.etName,"${it.code} ${it.message}", TypeSnack.SERVER).show()
                    500 -> Snack.build(binding.etName,"${it.code} ${it.message}", TypeSnack.ERROR).show()
                }
                viewModel.exception.value = null
            }
        }
    }
    private fun listener(){
        binding.btnSearch.setOnClickListener {
            if(binding.etName.text.isBlank()){
                Snack.build(it,"Escribe nombre de cocktel", TypeSnack.ERROR).show()
            }else{
                viewModel.search(binding.etName.text.toString())
            }
        }
    }

    private  fun clear(){

    }

    private fun createRv(){
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = DrinksAdapter(viewModel.drinks.value!!){ item: Drink ->
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = adapter
    }
}