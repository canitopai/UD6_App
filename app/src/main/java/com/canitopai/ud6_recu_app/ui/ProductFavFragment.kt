package com.canitopai.ud6_recu_app.ui
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.canitopai.proyectointegrador.core.NetworkManager
import com.canitopai.ud6_recu_app.R
import com.canitopai.ud6_recu_app.data.model.ProductItem
import com.canitopai.ud6_recu_app.databinding.FragmentProductFavBinding


import com.canitopai.ud6_recu_app.databinding.FragmentProductListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match

class ProductFavFragment : Fragment() {


    private var _binding: FragmentProductFavBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = ProductAdapter {


        val action = ProductFavFragmentDirections.actionProductFavFragment2ToProductDetailFragment(
            it.id
        )
        findNavController().navigate(action)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductFavBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView2.layoutManager = GridLayoutManager(context, 1)
        binding.recyclerView2.adapter = adapter
        binding.btnToList.setOnClickListener{
            val action = ProductFavFragmentDirections.actionProductFavFragment2ToProductListFragment(
            )
            findNavController().navigate(action)
        }
        requestData()
    }


    private fun requestData() {
    //room needed here
        NetworkManager.service.getProducts().enqueue(object : Callback<List<ProductItem>> {


            override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<List<ProductItem>>,
                response: Response<List<ProductItem>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                    Log.e("Retrofit", "Salió bien")
                } else {
                    Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}