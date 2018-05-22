package com.example.ifpb.pessoas

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.text.Normalizer

class MainActivity : AppCompatActivity() {
    val NOVO = 1
    val EDIT = 2
    private lateinit var dao: PessoaDAO
    private lateinit var lvPessoas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.dao = PessoaDAO(this)

        fab.setOnClickListener { view ->
            val it = Intent(this, FormActivity::class.java)
            startActivityForResult(it, NOVO)
        }

        this.lvPessoas = findViewById(R.id.lvMainPessoas)
        this.adapter()

        this.lvPessoas.setOnItemClickListener { parent, view, position, id ->
            val pessoa = this.lvPessoas.getItemAtPosition(position) as Pessoa
            val it = Intent(this, FormActivity::class.java)
            it.putExtra("PESSOA", pessoa)
            startActivityForResult(it, EDIT)
//            Toast.makeText(this, "Position Clicked:"+" "+position,Toast.LENGTH_SHORT).show()
        }

        this.lvPessoas.setOnItemLongClickListener({ parent, view, position, id ->
            val pessoa = this.lvPessoas.getItemAtPosition(position) as Pessoa
            excluir(pessoa)
            Toast.makeText(this, ""+ pessoa.nome +" excluído(a) com sucesso!",Toast.LENGTH_SHORT).show()
            true
        })


    }

    fun excluir(pessoa: Pessoa){
        this.dao.delete(pessoa)
        this.adapter()

    }

    fun adapter(){
        this.lvPessoas.adapter = ArrayAdapter<Pessoa>(this,
                android.R.layout.simple_list_item_1,
                this.dao.select())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == NOVO){
                val pessoa = data?.getSerializableExtra("PESSOA") as Pessoa

                this.dao.insert(pessoa)
                this.adapter()
            }

            if (requestCode == EDIT){
                val pessoa = data?.getSerializableExtra("PESSOA") as Pessoa

                this.dao.update(pessoa)
                this.adapter()
                Toast.makeText(this, ""+ pessoa.nome +" alterado(a) com sucesso!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
