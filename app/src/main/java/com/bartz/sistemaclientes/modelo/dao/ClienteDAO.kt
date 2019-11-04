package com.bartz.sistemaclientes.modelo.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.bartz.sistemaclientes.modelo.Cliente
import java.lang.Exception
import java.text.SimpleDateFormat

class ClienteDAO {

    fun gravar(cliente: Cliente): Boolean {
        val db = Conexao.getConexao()
        try {
            db?.beginTransaction()
            var parametros = ContentValues()
            if (cliente.id > 0) {
                parametros.put("id", cliente.id)
            }

            parametros.put("nome", cliente.nome)
            parametros.put("telefone", cliente.telefone)
            parametros.put("email", cliente.email)
            parametros.put("tipo_pessoa", cliente.tipoPessoa)
            parametros.put("cpf_cnpj", cliente.cpfCnpj)

            if(cliente.dataNascimento != null) {
                var sdf = SimpleDateFormat("yyyy-MM-dd")
                parametros.put("data_nascimento", sdf.format(cliente.dataNascimento))
            }

            parametros.put("sexo", cliente.sexo)
            parametros.put("status", cliente.status)
            db?.insertWithOnConflict(
                "cliente", null, parametros,
                SQLiteDatabase.CONFLICT_REPLACE
            )
            db?.setTransactionSuccessful()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return false
        } finally {
            db?.endTransaction()
        }
        return true
    }
}