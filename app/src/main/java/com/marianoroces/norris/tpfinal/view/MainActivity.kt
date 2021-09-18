package com.marianoroces.norris.tpfinal.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.marianoroces.norris.tpfinal.broadcast.ConnectionReceiver
import com.marianoroces.norris.tpfinal.databinding.ActivityMainBinding
import com.marianoroces.norris.tpfinal.fragment.MealFragment
import com.marianoroces.norris.tpfinal.viewmodel.MealViewModel

class MainActivity : AppCompatActivity(), ConnectionReceiver.ConnectionReceiverListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.mToolbar)

        registerReceiver(
            ConnectionReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        supportFragmentManager.beginTransaction()
            .replace(binding.mFrameLayout.id, MealFragment())
            .commit()
    }

    override fun onResume() {
        super.onResume()
        ConnectionReceiver.connectionReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        synchronize(isConnected)
    }

    private fun synchronize(connected: Boolean) {
        if (connected) {
            val mealVM: MealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
            mealVM.synchronize(this)
            Toast.makeText(this, "Sincronizando datos", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Usted esta desconectado", Toast.LENGTH_LONG).show()
        }
    }

    //TODO 1: IMPLEMENTAR TESTING

    //TODO 2: REVISAR APLICACION
}