package com.bartz.sistemaclientes.modelo

import java.util.*

class Cliente {
    var id : Int = 0
    var nome : String = ""
    var telefone : String = ""
    var email : String = ""
    var tipoPessoa : String = "F"
    var cpfCnpj : String = ""
    var dataNascimento : Date? = null
    var sexo: String = "M"
    var status: Boolean = true
}