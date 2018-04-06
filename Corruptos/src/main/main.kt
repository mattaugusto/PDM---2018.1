package main

import classes.*
import java.util.Scanner

fun main(args: Array<String>) {
	var input = Scanner(System.`in`)	
	var corruptos = ArrayList<Usuario>()

	var freq = ""
	var qtdeIniciante = 0
	var qtdeMedio = 0
	var qtdeAvanc = 0
	
	
	for (i in 1..3){
		println("Digite seu nome: ")
		var name = input.nextLine()
		while(name.isEmpty()){
			println("Nome é obrigatório:")
			name = input.nextLine()
		}
		
		println("Digite sua renda mensal:")
		var renda = input.nextLine()
		while(renda.isEmpty()){
			println("Renda mensal é obrigatória:")
			renda = input.nextLine()
		}
		
		println("Dê uma descrição breve ao seu delito:")
		var descricao = input.nextLine()
		while(descricao.isEmpty()){
			println("Descricao de delito é obrigatória:")
			descricao = input.nextLine()
		}
		
		println("Informe o valor em R$ atrelado ao delito(caso haja):")
		var valor = input.nextLine()
		if(!valor.isEmpty()){
			println("Informe o número de vezes que o delito ocorreu(caso tenha ocorrido múltiplas vezes):")
			freq = input.nextLine()
		}
		
		var valorDouble = if(valor.isEmpty()) null else valor.toDouble()
		var freqInt = if(freq.isEmpty()) null else freq.toInt()
	
		var delito = Delito(descricao, valorDouble, freqInt)
		var usuario = Usuario(name, renda.toDouble(), delito)
		corruptos.add(usuario)
	}
	
	for(corrupto in corruptos){
		if (corrupto.delito.freq != null)
			qtdeAvanc++
		else if(corrupto.delito.freq == null && corrupto.delito.valor != null)
			qtdeMedio++
		else
			qtdeIniciante++
	}
	
	println("")
	println("QtdeIniciante = " + qtdeIniciante)
	println("QtdeMedio = " + qtdeMedio)
	println("QtdeAvancado = " + qtdeAvanc)
	
}