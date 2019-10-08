package com.bartz.sistemaclientes.controle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bartz.sistemaclientes.R
import com.bartz.sistemaclientes.modelo.Cliente

class CadastroClientes : AppCompatActivity() {

    lateinit var campoNome: EditText
    lateinit var campoTelefone: EditText
    lateinit var campoEmail: EditText
    lateinit var campoCpfCnpj: EditText
    lateinit var campoDataNasc: EditText
    lateinit var campoPessoFisica: RadioButton
    lateinit var campoPessoaJuridica: RadioButton
    lateinit var campoSexo: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_clientes)
        campoNome = findViewById(R.id.campoNome)
        campoSexo = findViewById(R.id.campoSexo)
        campoTelefone = findViewById(R.id.campoTelefone)
        campoEmail = findViewById(R.id.campoEmail)
        campoCpfCnpj = findViewById(R.id.campoCpfCnpj)
        campoDataNasc = findViewById(R.id.campoDataNasc)
        campoPessoFisica = findViewById(R.id.campoPessoaFisica)
        campoPessoaJuridica = findViewById(R.id.campoPessoaJuridica)
        campoSexo = findViewById(R.id.campoSexo)

        var adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayOf(
                getString(R.string.sexoMasc),
                getString(R.string.sexoFem)
            )
        )

        campoSexo.adapter = adapter
    }

    fun eventoCliqueCampoData(view: View) {}

    fun lerDadosTelaParaObjeto(): Cliente {

        var c = Cliente()
        c.nome = campoNome.text.toString()
        c.telefone = campoNome.text.toString()
        c.email = campoEmail.text.toString()
        c.tipoPessoa = if (campoPessoFisica.isChecked) {"F"} else {"J"}
        c.cpfCnpj = campoCpfCnpj.text.toString()
        return c;
    }
    fun eventoCliqueBotaoSalvar(view: View) {}
}
