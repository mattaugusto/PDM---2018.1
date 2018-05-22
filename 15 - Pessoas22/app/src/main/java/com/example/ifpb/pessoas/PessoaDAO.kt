package com.example.ifpb.pessoas

import android.content.ContentValues
import android.content.Context

/**
 * Created by ifpb on 18/05/18.
 */

class PessoaDAO (var context: Context){
    private lateinit var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun insert(pessoa: Pessoa){
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)
        this.banco.writableDatabase.insert("pessoa", null, cv)
    }

    fun select() : ArrayList<Pessoa>{
        val lista = ArrayList<Pessoa>()
        val colunas = arrayOf("id", "nome", "idade")
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, null)

        cursor.moveToFirst()

        if (cursor.count > 0){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val nome = cursor.getString(cursor.getColumnIndex("nome"))
                val idade = cursor.getInt(cursor.getColumnIndex("idade"))

                lista.add(Pessoa(nome, idade, id))
            }while(cursor.moveToNext())
        }

        return lista
    }

    fun delete(pessoa: Pessoa){
        this.banco.writableDatabase.delete("pessoa", "nome" + "= ?", arrayOf(pessoa.nome))
    }

    fun update(pessoa: Pessoa){
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)
        this.banco.writableDatabase.update("pessoa", cv,"nome" + "= ?", arrayOf(pessoa.nome))
    }
}