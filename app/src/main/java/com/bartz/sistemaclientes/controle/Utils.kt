package com.bartz.sistemaclientes.controle

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.bartz.sistemaclientes.R

fun mostrarMensagem(
    contexto: Context,
    titulo: String,
    mensagem: String,
    eventoOK: DialogInterface.OnClickListener?
) {

    val adb: AlertDialog.Builder = AlertDialog.Builder(contexto)
    adb.setTitle(titulo)
    adb.setMessage(mensagem)
    adb.setPositiveButton(R.string.textoBotaoOK, eventoOK)
    adb.show()

}

class OuvidorTextoCnpjCpf(edit: EditText, isCpf: Boolean) : TextWatcher {
    val campoDeTexto: EditText = edit
    val cpf: Boolean = isCpf

    var textoAnterior: String = ""
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        textoAnterior = s.toString()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        var texto = s.toString()
        campoDeTexto.removeTextChangedListener(this)

        if (texto.length > textoAnterior.length) {
            if (cpf) {
                if (textoAnterior.length == 3 || textoAnterior.length == 7)
                    campoDeTexto.setText(textoAnterior + "." + s.toString()[s.toString().length - 1])

                if (textoAnterior.length == 11)
                    campoDeTexto.setText(textoAnterior + "-" + s.toString()[s.toString().length - 1])

                if (textoAnterior.length == 14)
                    campoDeTexto.setText(textoAnterior)
            } else {
                if (textoAnterior.length == 2 || textoAnterior.length == 6)
                    campoDeTexto.setText(textoAnterior + "." + s.toString()[s.toString().length - 1])

                if (textoAnterior.length == 10)
                    campoDeTexto.setText(textoAnterior + "/" + s.toString()[s.toString().length - 1])

                if (textoAnterior.length == 15)
                    campoDeTexto.setText(textoAnterior + "-" + s.toString()[s.toString().length - 1])

                if (textoAnterior.length == 18)
                    campoDeTexto.setText(textoAnterior)
            }
        }

        campoDeTexto.addTextChangedListener(this)
        campoDeTexto.setSelection(campoDeTexto.text.toString().length)
    }
}