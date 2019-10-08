package com.bartz.sistemaclientes.controle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bartz.sistemaclientes.R
import com.bartz.sistemaclientes.modelo.DAO.Conexao

class PesquisaClientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_clientes)
        Conexao.conectarBancoDados(applicationContext);
        carregarCadastroCliente()
    }

    fun cliqueBotaoTelaCadastro(view: View) {
        carregarCadastroCliente()
    }

    fun carregarCadastroCliente() {
        val telaCadastro = Intent(this, CadastroClientes::class.java)
        startActivity(telaCadastro)
    }


}
