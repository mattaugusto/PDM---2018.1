package main

import classes.*
import java.util.Scanner

var qtdeIniciante = 0
var qtdeMedio = 0
var qtdeAvanc = 0
val input = Scanner(System.`in`)

fun inputNome():String{
	println("Digite seu nome: ")
	var name = input.nextLine()
	while(name.isEmpty()){
		println("Nome é obrigatório:")
		name = input.nextLine()
	}
	return name
}

fun inputRenda():String{
	println("Digite sua renda mensal:")
	var renda = input.nextLine()
	while(renda.isEmpty()){
		println("Renda mensal é obrigatória:")
		renda = input.nextLine()
	}
	return renda
}

fun inputDescricao():String{
	println("Dê uma descrição breve ao seu delito:")
	var descricao = input.nextLine()
	while(descricao.isEmpty()){
		println("Descricao de delito é obrigatória:")
		descricao = input.nextLine()
	}
	return descricao
}

fun inputValor():String{
	println("Informe o valor em R$ atrelado ao delito(caso haja):")
	val valor = input.nextLine()
	return valor
}

fun inputFreq():String{
	println("Informe o número de vezes que o delito ocorreu(caso tenha ocorrido múltiplas vezes):")
	val freq = input.nextLine()
	return freq
}

fun mostrarCorruptos(corruptos:ArrayList<Usuario>){
	for(corrupto in corruptos) {
		println("")
		println(corrupto.nome + " corrupto " + corrupto.delito.tipo)
	}
}

fun mostrarQtde(qtdeIniciante:Int, qtdeMedio:Int, qtdeAvanc:Int){
	println("")
	println("QtdeIniciante = " + qtdeIniciante)
	println("QtdeMedio = " + qtdeMedio)
	println("QtdeAvancado = " + qtdeAvanc)
}

fun addQtdeTipo(corruptos:ArrayList<Usuario>){
	for(corrupto in corruptos){
		when(corrupto.delito.tipo){
			"Iniciante" -> qtdeIniciante++
			"Médio" -> qtdeMedio++
			"Avançado" -> qtdeAvanc++
		}
	}
}

fun main(args: Array<String>) {

	val corruptos = ArrayList<Usuario>()

	var freq = ""

	for (i in 1..11){

		val name = inputNome()
		val renda = inputRenda()
		val descricao = inputDescricao()
		val valor = inputValor()
		if(!valor.isEmpty())
			freq = inputFreq()

		val valorDouble = if(valor.isEmpty()) null else valor.toDouble()
		val freqInt = if(freq.isEmpty()) null else freq.toInt()
	
		val delito = Delito(descricao, valorDouble, freqInt)
		when(valorDouble){
			null -> delito.tipo = "Iniciante"
			else -> when(freqInt) {
				null -> delito.tipo = "Médio"
				else -> delito.tipo = "Avançado"
			}
		}
		val usuario = Usuario(name, renda.toDouble(), delito)
		corruptos.add(usuario)
		println("---------------------------------")
	}
	
	addQtdeTipo(corruptos)
	mostrarCorruptos(corruptos)
	mostrarQtde(qtdeIniciante,qtdeMedio,qtdeAvanc)
}