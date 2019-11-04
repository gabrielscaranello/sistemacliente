package com.bartz.sistemaclientes.modelo.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File

class Conexao {
    companion object {
        private var conexao: SQLiteDatabase? = null

        fun getConexao(): SQLiteDatabase? {
            return conexao
        }

        fun conectarBancoDados(contexto: Context) {
            if (conexao == null) {
                val arquivoBd = File(
                    contexto.applicationInfo.dataDir +
                            "/clientes.db"
                )
                conexao = SQLiteDatabase.openOrCreateDatabase(
                    arquivoBd, null
                )

                //arquivoBd.delete()

                conexao?.execSQL("create table if not exists cliente(" +
                        "   id integer primary key autoincrement," +
                        "   nome varchar(80)," +
                        "   telefone varchar(30)," +
                        "   email varchar(40)," +
                        "   tipo_pessoa varchar(1)," +
                        "   cpf_cnpj varchar(20)," +
                        "   data_nascimento date," +
                        "   sexo varchar(1)," +
                        "   status integer default 1" +
                        ");")
            }
        }
    }
}