package com.bartz.sistemaclientes.modelo.DAO

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

            var params = ContentValues()

            if (cliente.id > 0) params.put("id", cliente.id)

            params.put("nome", cliente.nome)
            params.put("telefone", cliente.telefone)
            params.put("email", cliente.email)
            params.put("tipo_pessoa", cliente.tipoPessoa)
            params.put("cpf_cnpj", cliente.cpfCnpj)
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            params.put("data_nascimento", sdf.format(cliente.dataNascimento))
            params.put("sexo", cliente.sexo)
            params.put("status", cliente.status)


            db?.insertWithOnConflict("clientes", null, params, SQLiteDatabase.CONFLICT_REPLACE)


            db?.setTransactionSuccessful()
        } catch (ex: Exception) {
            return false
        } finally {
            db?.endTransaction()
        }
        return true

    }
}