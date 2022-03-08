package com.canitopai.ud6_recu_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.canitopai.proyectointegrador.core.NetworkManager
import com.canitopai.ud6_recu_app.data.model.ProductItem
import com.canitopai.ud6_recu_app.databinding.FragmentProductAddBinding
import kotlinx.android.synthetic.main.fragment_product_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ProductAddFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
    private val binding
        get() = _binding!!
    private var finalValue: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAgregar.setOnClickListener{
            if (checkEmpty()==true){
                postProduct()
                val action = ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
                findNavController().navigate(action)
            }
        }
        binding.btnBack2.setOnClickListener {
            val action = ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
            findNavController().navigate(action)
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }
    companion object {
        private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
    }

    private fun getRandomString(sizeOfRandomString: Int): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }
    private fun postProduct() {
        NetworkManager.service.savePost(ProductItem(true,etDesc.text.toString(),etDisc.text.toString().toDouble()
            ,"3fa85f64-5717-4562-b3fc-2c963f66afa6"
            ,"https://upload.wikimedia.org/wikipedia/commons/5/58/Animal_Crossing_Leaf.png?20191119013532"
            ,etName.text.toString(),etPrice.text.toString().toDouble()
            ,etStock.text.toString().toInt()))
            .enqueue(object :
            Callback<ProductItem> {
            override fun onResponse(call: Call<ProductItem>, response: Response<ProductItem>) {
                if (response.isSuccessful) {
                    //getMs
                    Toast.makeText(context, "Pasa por el post", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "post hecho con éxito")
                } else {
                    Log.e("Network", "error en la conexion on Response")
                }
            }
            override fun onFailure(call: Call<ProductItem>, t: Throwable) {
                Log.e("Network", "error en la conexion",t)
                Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun checkEmpty(): Boolean {
        if (etName.text.toString().equals("")){
            Toast.makeText(context, "Rellene el campo nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etDesc.text.toString().equals("")){
            Toast.makeText(context, "Rellene el campo descripción", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etPrice.text.toString().equals("")){
            Toast.makeText(context, "Rellene el campo precio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etDisc.text.toString().equals("")){
            Toast.makeText(context, "Rellene el campo descuento", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etStock.text.toString().equals("")){
            Toast.makeText(context, "Rellene el campo stock", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
