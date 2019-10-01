package com.bartz.sistemaclientes.controle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bartz.sistemaclientes.R

class CadastroClientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_clientes)
    }

    fun eventoCliqueCampoData(view: View) {}
}
