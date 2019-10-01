package com.bartz.sistemaclientes.controle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bartz.sistemaclientes.R

class PesquisaClientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_clientes)
        carregarCadastroCliente()
    }

    fun cliqueBotaoTelaCadastro(view: View) {
        carregarCadastroCliente()
    }

    fun carregarCadastroCliente(){
        val telaCadastro = Intent(this, CadastroClientes::class.java)
        startActivity(telaCadastro)
    }


}
