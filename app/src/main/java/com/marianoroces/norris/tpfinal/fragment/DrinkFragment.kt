package com.marianoroces.norris.tpfinal.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marianoroces.norris.tpfinal.R
import com.marianoroces.norris.tpfinal.adapter.IngredientAdapter
import com.marianoroces.norris.tpfinal.databinding.FragmentDrinkBinding
import com.marianoroces.norris.tpfinal.model.Drinks
import com.marianoroces.norris.tpfinal.viewmodel.DrinkViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkFragment : Fragment() {

    private var _binding: FragmentDrinkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val drinkVM: DrinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)

        if (drinkVM.checkConnection(view.context)) {
            drinkVM.getDrink()
                .enqueue(object : Callback<Drinks> {
                    override fun onFailure(call: Call<Drinks>, t: Throwable) {
                        Log.d("API", t.message.toString())
                    }

                    override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {
                        if (response.body() != null) {
                            val data = response.body()

                            binding.fdTitle.text = data?.drinks?.get(0)?.strDrink

                            context?.let {
                                Glide
                                    .with(it)
                                    .load(data?.drinks?.get(0)?.strDrinkThumb)
                                    .fitCenter()
                                    .into(binding.fdImage)
                            }

                            binding.fdIngredients.layoutManager =
                                LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                            binding.fdIngredients.adapter =
                                IngredientAdapter(drinkVM.getIngredients(data?.drinks?.get(0) as Drinks.Drink))
                        }
                    }
                })
        } else {
            val ingredientes = ArrayList<String>()
            ingredientes.add("Milk: 2 cups")
            ingredientes.add("Chocolate syrup")
            ingredientes.add("Mint syrup")

            binding.fdTitle.text = "Just a Moonmint"
            binding.fdImage.setImageResource(R.drawable.drink)

            binding.fdIngredients.layoutManager =
                LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            binding.fdIngredients.adapter = IngredientAdapter(ingredientes)
        }

        binding.fdReturn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.m_frame_layout, MealFragment())
                ?.commit()
        }
    }
}