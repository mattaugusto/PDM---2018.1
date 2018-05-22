package com.example.ifpb.pessoas

import java.io.Serializable

/**
 * Created by ifpb on 18/05/18.
 */

class Pessoa (var nome: String, var idade: Int, var id: Int = -1): Serializable{
    override fun toString(): String {
        return this.nome
    }
}