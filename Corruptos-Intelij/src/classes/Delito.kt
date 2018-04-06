package classes

class Delito(
		var descricao:String,
		var valor:Double? = null,
		var freq:Int? = null){

	var tipo:String = ""

	override fun toString(): String =
			"[descricao = " + descricao + ", " +
			"valor = " + valor + ", " +
			"freq = " + freq + "]"
}