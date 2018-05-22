package com.example.ifpb.pessoas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ifpb on 18/05/18.
 */

val VERSAO = 1

class BancoHelper(context: Context?) :
        SQLiteOpenHelper(context, "pessoa.sqlite", null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table pessoa (" +
                      "id integer primary key autoincrement, " +
                      "nome string, " +
                      "idade integer " +
                  ")"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, antes: Int, nova: Int) {

    }

}