package classes

class Usuario(
		var nome:String,
		var rendaMensal:Double,
		var delito:Delito){
	
	override fun toString(): String =
			"[nome = " + nome + ", " +
			"rendaMensal = " + rendaMensal + ", " +
			"delito = " + delito + "]"
}