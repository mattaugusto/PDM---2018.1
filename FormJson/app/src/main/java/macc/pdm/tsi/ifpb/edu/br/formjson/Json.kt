package macc.pdm.tsi.ifpb.edu.br.formjson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class Json : AppCompatActivity() {

    private lateinit var tvJson: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json)

        this.tvJson = findViewById(R.id.tvJson)

        val values = TreeMap<String, ArrayList<String>>()

        values.put("nome", intent.getStringArrayListExtra("nome"))
        values.put("data", intent.getStringArrayListExtra("data"))
        values.put("sexo", intent.getStringArrayListExtra("sexo"))
        values.put("estadoCivil", intent.getStringArrayListExtra("estadoCivil"))

        this.tvJson.text = createJson(values)
    }

    fun createJson(values: TreeMap<String, ArrayList<String>>): String {
        var result = "{\n"
        for((key, value) in values){
            if(value.size == 1)
                result += "\t\"$key\": \"${value.first()}\""
            else
                result += "\t\"$key\": \"$value\""
            if(key != values.lastKey())
                result += ",\n"
        }

        result += "\n}"
        return result
    }
}
