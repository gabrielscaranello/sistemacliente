package com.bartz.sistemaclientes.modelo.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.security.AccessControlContext

class Conexao {
    companion object {
        private var conexao: SQLiteDatabase? = null

        fun getConexao(): SQLiteDatabase? {
            return conexao
        }

        fun conectarBancoDados(contexto: Context) {
            if (conexao == null) {
                var arquivoBd = File(
                    contexto.applicationInfo.dataDir+"/clientes.bd"
                );
                conexao = SQLiteDatabase.openOrCreateDatabase(
                    arquivoBd, null
                );
                conexao?.execSQL("create table if not exists clientes(" +
                        "    id integer primary key autoincrement," +
                        "    nome varchar(80)," +
                        "    telefone varchar(30)," +
                        "    email varchar(40)," +
                        "    tipo_pessoa varchar(1)," +
                        "    cpf_cnpj varchar(20)," +
                        "    data_nascimento date," +
                        "    sexo varchar(1)," +
                        "    status integer default(1)" +
                        ");");
            }
        }
    }
}