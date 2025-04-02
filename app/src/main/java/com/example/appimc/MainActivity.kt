package com.example.appimc

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appimc.databinding.ActivityMainBinding
import com.example.appimc.databinding.CustomResultImcBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Definindo o layout antes de acessar os elementos!

        binding.btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val alturaStr = binding.inputAltura.text.toString()
        val pesoStr = binding.inputPeso.text.toString()

        if (alturaStr.isNotEmpty() && pesoStr.isNotEmpty()) {
            val altura = alturaStr.toDoubleOrNull()?.div(100) // ✅ Convertendo cm para metros
            val peso = pesoStr.toDoubleOrNull()

            if (altura != null && peso != null && altura > 0) {
                val imc = peso / (altura * altura)
                showDialog(imc)
            } else {
                showDialog(0.0)
            }
        } else {
            showDialog(0.0)
        }
    }


    private fun showDialog(imc: Double) {
        val dialog = BottomSheetDialog(this)
        val sheetDialog = CustomResultImcBinding.inflate(layoutInflater)

        sheetDialog.resultado.text = "Seu IMC: %.2f".format(imc) // Atualiza o resultado no diálogo

        sheetDialog.btnVoltar.setOnClickListener {
            dialog.dismiss() 
        }

        dialog.setContentView(sheetDialog.root)
        dialog.show()
    }
}
