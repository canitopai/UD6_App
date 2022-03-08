package com.canitopai.ud6_recu_app.ui
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.canitopai.proyectointegrador.core.NetworkManager
import com.canitopai.ud6_recu_app.R
import com.canitopai.ud6_recu_app.data.database.ProductModel
import com.canitopai.ud6_recu_app.data.model.ProductItem
import com.canitopai.ud6_recu_app.databinding.FragmentProductDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
        binding.btnBack.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment()
           findNavController().navigate(action)
         }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun requestData() {


        NetworkManager.service.getProductDetailed(args.id)?.enqueue(object : Callback<ProductItem?> {


            override fun onFailure(call: Call<ProductItem?>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<ProductItem?>,
                response: Response<ProductItem?>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.discountPrice!! < response.body()?.regularPrice!!){
                        binding.ivDisc.visibility = View.VISIBLE
                        binding.tvDiscPrice.visibility = View.VISIBLE
                        binding.lblDisc.visibility = View.VISIBLE
                        binding.lblDisc2.visibility = View.VISIBLE
                    }
                    if (response.body()?.available == false){
                        binding.lblEmpty.visibility = View.VISIBLE
                    }

                    val product = ProductModel(true,response.body()?.description.toString(),response.body()?.discountPrice.toString().toDouble(),
                        null,response.body()?.imageUrl.toString()
                        ,response.body()?.name.toString(),response.body()?.regularPrice.toString().toDouble(),response.body()?.stock.toString().toInt())

                    binding.btnAddFav.setOnClickListener {
                        db.productDao().add(product)
                        Toast.makeText(context, "Agregado a Favoritos", Toast.LENGTH_SHORT).show()
                    }


                    binding.tvDtName.text = response.body()?.name
                    binding.tvDtDesc.text = response.body()?.description
                    binding.tvDiscPrice.text = response.body()?.discountPrice.toString()
                    binding.tvDtPrice.text = response.body()?.regularPrice.toString()
                    binding.tvDtStock.text = response.body()?.stock.toString()
                    Picasso.get()
                        .load(response.body()?.imageUrl)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivProd)
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