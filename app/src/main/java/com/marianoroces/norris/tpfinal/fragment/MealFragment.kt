package com.marianoroces.norris.tpfinal.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.marianoroces.norris.tpfinal.R
import com.marianoroces.norris.tpfinal.databinding.FragmentMealBinding
import com.marianoroces.norris.tpfinal.enums.FoodType
import com.marianoroces.norris.tpfinal.model.User
import com.marianoroces.norris.tpfinal.viewmodel.MealViewModel
import com.marianoroces.norris.tpfinal.viewmodel.UserViewModel
import java.util.*

class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!
    lateinit var tipoComida: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeElements(view)
        val mealVM: MealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        binding.fmType.setOnItemClickListener { parent, _, position, _ ->
            tipoComida = parent.getItemAtPosition(position).toString()
            when (position) {
                1 -> {
                    binding.fmDesertText.visibility = TextView.VISIBLE
                    binding.fmDesertRg.visibility = RadioButton.VISIBLE
                }
                3 -> {
                    binding.fmDesertText.visibility = TextView.VISIBLE
                    binding.fmDesertRg.visibility = RadioButton.VISIBLE
                }
                else -> {
                    binding.fmDesertText.visibility = TextView.GONE
                    binding.fmDesertRg.visibility = RadioButton.GONE
                }
            }
        }

        binding.fmDesertRg.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.fmDesertYes.id)
                binding.fmDesertLayout.visibility = TextInputLayout.VISIBLE
            else
                binding.fmDesertLayout.visibility = TextInputLayout.GONE
        }

        binding.fmTemptationRg.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.fmTemptationYes.id)
                binding.fmTemptationLayout.visibility = TextInputLayout.VISIBLE
            else
                binding.fmTemptationLayout.visibility = TextInputLayout.GONE
        }

        binding.fmSave.setOnClickListener {
            if (validateInputs()) {
                val user = activity?.intent?.getSerializableExtra("user") as User
                var postre = ""
                var tentacion = ""
                var hambre = ""
                var nombrePostre = ""
                var tentacionNombre = ""
                val nombre = user.nombre
                val apellido = user.apellido
                val principal = binding.fmFirst.text.toString()
                val secundaria = binding.fmSecon.text.toString()
                val bebida = binding.fmBeverage.text.toString()

                when (binding.fmDesertRg.checkedRadioButtonId) {
                    binding.fmDesertYes.id -> {
                        postre = "SI"
                        nombrePostre = binding.fmDesert.text.toString()
                    }
                    binding.fmDesertNo.id -> postre = "NO"
                }

                when (binding.fmTemptationRg.checkedRadioButtonId) {
                    binding.fmTemptationYes.id -> {
                        tentacion = "SI"
                        tentacionNombre = binding.fmTemptation.text.toString()
                    }
                    binding.fmTemptationNo.id -> tentacion = "NO"
                }

                when (binding.fmHungerRg.checkedRadioButtonId) {
                    binding.fmHungerYes.id -> hambre = "SI"
                    binding.fmHungerNo.id -> hambre = "NO"
                }

                val fecha: Calendar = Calendar.getInstance()
                val fechaReal: String = fecha.get(Calendar.DAY_OF_MONTH).toString() + "/" +
                        (fecha.get(Calendar.MONTH) + 1).toString() + "/" +
                        fecha.get(Calendar.YEAR).toString() + " " +
                        fecha.get(Calendar.HOUR_OF_DAY) + ":" +
                        fecha.get(Calendar.MINUTE).toString()

                if(binding.fmType.text.toString() == FoodType.DESAYUNO.name || binding.fmType.text.toString() == FoodType.MERIENDA.name)
                    mealVM.saveMeal(
                        apellido,
                        nombre,
                        fechaReal,
                        tipoComida,
                        principal,
                        secundaria,
                        bebida,
                        "",
                        "",
                        tentacion,
                        tentacionNombre,
                        hambre,
                        it.context
                    )
                else
                    mealVM.saveMeal(
                        apellido,
                        nombre,
                        fechaReal,
                        tipoComida,
                        principal,
                        secundaria,
                        bebida,
                        postre,
                        nombrePostre,
                        tentacion,
                        tentacionNombre,
                        hambre,
                        it.context
                    )

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.m_frame_layout, DrinkFragment())
                    ?.commit()
            }
        }
    }

    private fun initializeElements(view: View) {
        val tipoAdapter = ArrayAdapter(view.context, R.layout.list_item, FoodType.values())
        binding.fmType.setAdapter(tipoAdapter)
    }

    private fun validateInputs(): Boolean {
        var result = true

        when (binding.fmType.text.toString()) {
            "Tipo" -> {
                binding.fmTypeLayout.error = "Seleccionar"
                result = false
            }
            FoodType.ALMUERZO.name -> {
                if (binding.fmDesertRg.checkedRadioButtonId == -1) {
                    binding.fmDesertYes.error = "Seleccionar uno"
                    binding.fmDesertNo.error = "Seleccionar uno"
                    result = false
                } else {
                    binding.fmDesertYes.error = null
                    binding.fmDesertNo.error = null
                }
            }
            FoodType.CENA.name -> {
                if (binding.fmDesertRg.checkedRadioButtonId == -1) {
                    binding.fmDesertYes.error = "Seleccionar uno"
                    binding.fmDesertNo.error = "Seleccionar uno"
                    result = false
                } else {
                    binding.fmDesertYes.error = null
                    binding.fmDesertNo.error = null
                }
            }
            else ->
                binding.fmTypeLayout.error = ""
        }

        if (binding.fmFirst.text.toString() == "") {
            binding.fmFirstLayout.error = "Completar"
            result = false
        } else
            binding.fmFirstLayout.error = ""

        if (binding.fmSecon.text.toString() == "") {
            binding.fmSecondLayout.error = "Completar"
            result = false
        } else
            binding.fmSecondLayout.error = ""

        if (binding.fmBeverage.text.toString() == "") {
            binding.fmBeveragLayout.error = "Completar"
            result = false
        } else
            binding.fmBeveragLayout.error = ""

        if (binding.fmDesertYes.isChecked) {
            if (binding.fmDesert.text.toString() == "") {
                binding.fmDesertLayout.error = "Completar"
                result = false
            } else
                binding.fmDesertLayout.error = ""
        }

        if (binding.fmTemptationRg.checkedRadioButtonId == -1) {
            binding.fmTemptationYes.error = "Seleccionar uno"
            binding.fmTemptationNo.error = "Seleccionar uno"
            result = false
        } else {
            binding.fmTemptationYes.error = null
            binding.fmTemptationNo.error = null
        }

        if (binding.fmTemptationYes.isChecked) {
            if (binding.fmTemptation.text.toString() == "") {
                binding.fmTemptationLayout.error = "Completar"
                result = false
            } else
                binding.fmTemptationLayout.error = ""
        }

        if (binding.fmHungerRg.checkedRadioButtonId == -1) {
            binding.fmHungerYes.error = "Seleccionar uno"
            binding.fmHungerNo.error = "Seleccionar uno"
            result = false
        } else {
            binding.fmHungerYes.error = null
            binding.fmHungerNo.error = null
        }

        return result
    }
}