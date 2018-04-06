package classes

class Delito(
		var descricao:String,
		var valor:Double? = null,
		var freq:Int? = null){
	
	override fun toString(): String =
			"[descricao = " + descricao + ", " +
			"valor = " + valor + ", " +
			"freq = " + freq + "]"
}