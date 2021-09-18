package com.marianoroces.norris.tpfinal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.marianoroces.norris.tpfinal.R
import com.marianoroces.norris.tpfinal.databinding.FragmentRegisterBinding
import com.marianoroces.norris.tpfinal.enums.Province
import com.marianoroces.norris.tpfinal.enums.Sex
import com.marianoroces.norris.tpfinal.enums.Treatment
import com.marianoroces.norris.tpfinal.viewmodel.UserViewModel
import java.util.*

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var tratamiento: String
    lateinit var provincia: String
    lateinit var sexo: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeElements(view)
        val userVM = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.frTreatment.setOnItemClickListener { parent, _, position, _ ->
            tratamiento = parent.getItemAtPosition(position).toString()
        }

        binding.frProvince.setOnItemClickListener { parent, _, position, _ ->
            provincia = parent.getItemAtPosition(position).toString()
        }

        binding.frSex.setOnItemClickListener { parent, _, position, _ ->
            sexo = parent.getItemAtPosition(position).toString()
        }

        binding.frBirthDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)

                if (validateDate(calendar) == -1) {
                    val fecha = (calendar.get(Calendar.DAY_OF_MONTH) + 1).toString() + "/" +
                            (calendar.get(Calendar.MONTH) + 1).toString() + "/" +
                            calendar.get(Calendar.YEAR).toString()
                    binding.frBirthDateLayout.error = null
                    binding.frBirthDate.setText(fecha)
                } else {
                    Toast.makeText(
                        view.context,
                        "Ingrese una fecha de nacimiento valida",
                        Toast.LENGTH_LONG
                    ).show()

                    binding.frBirthDateLayout.error = "Completar"
                    binding.frBirthDate.setText("")
                }
            }
            datePicker.show(childFragmentManager, "DATE_PICKER")
        }

        binding.frRegister.setOnClickListener {
            if (validateInputs())
                if (userVM.saveUser(
                        binding.frUser.text.toString(),
                        binding.frPassword.text.toString(),
                        binding.frDni.text.toString(),
                        binding.frFirstName.text.toString(),
                        binding.frLastName.text.toString(),
                        binding.frBirthDate.text.toString(),
                        sexo,
                        provincia,
                        binding.frCity.text.toString(),
                        tratamiento,
                        it.context
                    )
                )
                Toast.makeText(it.context, "Usuario creado exitosamente", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateDate(date: Calendar): Int {

        val aux: Calendar = Calendar.getInstance()

        return date.compareTo(aux)
    }

    private fun initializeElements(view: View) {
        val adapterSex = ArrayAdapter(view.context, R.layout.list_item, Sex.values())
        binding.frSex.setAdapter(adapterSex)

        val adapterProvince = ArrayAdapter(view.context, R.layout.list_item, Province.values())
        binding.frProvince.setAdapter(adapterProvince)

        val adapterTreatment = ArrayAdapter(view.context, R.layout.list_item, Treatment.values())
        binding.frTreatment.setAdapter(adapterTreatment)
    }

    private fun validateInputs(): Boolean {
        var result = true

        if (binding.frUser.text.toString() == "") {
            binding.frUserLayout.error = "Completar"
            result = false
        } else
            binding.frUserLayout.error = ""

        if (binding.frPassword.text.toString() == "") {
            binding.frPasswordLayout.error = "Completar"
            result = false
        } else
            binding.frPasswordLayout.error = ""

        if (binding.frFirstName.text.toString() == "") {
            binding.frFirstNameLayout.error = "Completar"
            result = false
        } else
            binding.frFirstNameLayout.error = ""

        if (binding.frLastName.text.toString() == "") {
            binding.frLastNameLayout.error = "Completar"
            result = false
        } else
            binding.frLastNameLayout.error = ""

        if (binding.frDni.text.toString() == "") {
            binding.frDniLayout.error = "Completar"
            result = false
        } else
            binding.frDniLayout.error = ""

        if (binding.frSex.text.toString() == "SEXO") {
            binding.frSexLayout.error = "Seleccionar"
            result = false
        } else
            binding.frSexLayout.error = ""

        if (binding.frBirthDate.text.toString() == "") {
            binding.frBirthDateLayout.error = "Completar"
            result = false
        } else
            binding.frBirthDateLayout.error = ""

        if (binding.frProvince.text.toString() == "Provincia") {
            binding.frProvinceLayout.error = "Seleccionar"
            result = false
        } else
            binding.frProvinceLayout.error = ""

        if (binding.frCity.text.toString() == "") {
            binding.frCityLayout.error = "Completar"
            result = false
        } else
            binding.frCityLayout.error = ""

        if (binding.frTreatment.text.toString() == "TRATAMIENTO") {
            binding.frTreatmentLayout.error = "Seleccionar"
            result = false
        } else
            binding.frTreatmentLayout.error = ""

        return result
    }
}