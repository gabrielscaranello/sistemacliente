package com.bartz.sistemaclientes.controle

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bartz.sistemaclientes.R
import com.bartz.sistemaclientes.modelo.Cliente
import com.bartz.sistemaclientes.modelo.dao.ClienteDAO
import java.text.SimpleDateFormat
import java.util.*

class CadastroClientes : AppCompatActivity() {


    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private lateinit var campoPessoaFisica: RadioButton
    private lateinit var campoPessoaJuridica: RadioButton
    private lateinit var campoCpfCnpj: EditText
    private lateinit var campoDataNascimento: EditText
    private lateinit var campoSexo: Spinner
    private lateinit var camposPessoaFisica: LinearLayout
    private lateinit var labelCpfCnpj: TextView
    private lateinit var ouvidorCnpjCpf: OuvidorTextoCnpjCpf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_clientes)
        campoNome = findViewById(R.id.campoNome)
        campoTelefone = findViewById(R.id.campoTelefone)
        campoEmail = findViewById(R.id.campoEmail)
        campoPessoaFisica = findViewById(R.id.campoPessoaFisica)
        campoPessoaJuridica = findViewById(R.id.campoPessoaJuridica)
        campoCpfCnpj = findViewById(R.id.campoCpfCnpj)
        campoDataNascimento = findViewById(R.id.campoDataNasc)
        campoSexo = findViewById(R.id.campoSexo)
        camposPessoaFisica = findViewById(R.id.camposPessoaFisica)
        labelCpfCnpj = findViewById(R.id.labelCpfCnpj)

        ouvidorCnpjCpf = OuvidorTextoCnpjCpf(campoCpfCnpj, true)
        campoCpfCnpj.addTextChangedListener(ouvidorCnpjCpf)

        var adapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            arrayOf(
                getString(R.string.sexoMasculino),
                getString(R.string.sexoFeminino)
            )
        )
        campoSexo.adapter = adapter

        class OuvidorEventoCheckRadio : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                campoCpfCnpj.text.clear()
                campoCpfCnpj.removeTextChangedListener(ouvidorCnpjCpf)
                ouvidorCnpjCpf = OuvidorTextoCnpjCpf(campoCpfCnpj, isChecked)
                campoCpfCnpj.addTextChangedListener(ouvidorCnpjCpf)

                if (isChecked) {
                    camposPessoaFisica.visibility = View.VISIBLE
                    labelCpfCnpj.text = getString(R.string.labelCpf)
                } else {
                    camposPessoaFisica.visibility = View.GONE
                    labelCpfCnpj.text = getString(R.string.labelCnpj)
                }
            }
        }
        campoPessoaFisica.setOnCheckedChangeListener(OuvidorEventoCheckRadio())
    }

    fun eventoCliqueCampoData(v: View) {
        val data = Calendar.getInstance()

        if (this.campoDataNascimento.text.toString().length > 0)
            data.time =
                SimpleDateFormat("dd/MM/yyyy").parse(this.campoDataNascimento.text.toString())

        class OuvidorMudancaData : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                campoDataNascimento.setText(
                    String.format("%02d", dayOfMonth) + "/" + String.format(
                        "%02d",
                        month + 1
                    ) + "/" + year
                )
            }
        }

        val seletorDeData = DatePickerDialog(
            this,
            OuvidorMudancaData(),
            data.get(Calendar.YEAR),
            data.get(Calendar.MONTH),
            data.get(Calendar.DAY_OF_MONTH)
        )
        seletorDeData.show()
    }

    fun lerDadosTelaParaObjeto(): Cliente {
        var c = Cliente()
        c.nome = campoNome.text.toString()
        c.telefone = campoTelefone.text.toString()
        c.email = campoEmail.text.toString()
        c.tipoPessoa = if (campoPessoaJuridica.isChecked) {
            "J"
        } else {
            "F"
        }
        c.cpfCnpj = campoCpfCnpj.text.toString()
        if (campoPessoaFisica.isChecked) {
            if (campoDataNascimento.text.toString().length > 0) {
                var sdf = SimpleDateFormat("dd/MM/yyyy")
                c.dataNascimento = sdf.parse(campoDataNascimento.text.toString())
            }
            c.sexo = if (campoSexo.selectedItemPosition == 0) "M" else "F"
        }
        return c
    }

    fun validarCamposObrigatorios(): Boolean {
        if (campoNome.text.toString().trim().isEmpty()) {
            mostrarMensagem(
                this,
                getString(R.string.textoTituloMensagemAtencao),
                getString(R.string.textoMensagemInformeNome),
                null
            )
            return false;
        }
        return true;
    }

    fun eventoCliqueBotaoSalvar(view: View) {
        if (validarCamposObrigatorios()) {
            if (ClienteDAO().gravar(lerDadosTelaParaObjeto())) {
                class OkListener : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        finish()
                    }
                }

                //mostra mensagem que deu certo
                mostrarMensagem(
                    this,
                    getString(R.string.textoTituloMensagemConfirmacao),
                    getString(R.string.textoMensagemClienteGravado),
                    OkListener()
                )
            } else {
                //mostra mensagem que deu erro
                mostrarMensagem(
                    this,
                    getString(R.string.textoTituloMensagemErro),
                    getString(R.string.textoMensagemErroGravarCliente),
                    null
                )
            }
        }
    }
}
