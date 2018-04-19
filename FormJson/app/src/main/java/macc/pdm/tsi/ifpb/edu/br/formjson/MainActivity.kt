package macc.pdm.tsi.ifpb.edu.br.formjson

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var spMainSexo: Spinner
    private lateinit var spMainEstadoCivil: Spinner
    private lateinit var etMainNome: EditText
    private lateinit var tvMainData: TextView
    private lateinit var btnMainSelect: Button
    private lateinit var btnMainEnviar: Button
    val cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.spMainSexo = findViewById(R.id.spMainSexo)
        this.spMainEstadoCivil = findViewById(R.id.spMainEstadoCivil)
        this.etMainNome = findViewById(R.id.etMainNome)
        this.etMainNome.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        this.tvMainData = findViewById(R.id.tvMainData)
        this.btnMainSelect = findViewById(R.id.btnMainSelect)
        this.btnMainEnviar = findViewById(R.id.btnMainEnviar)

        // MODAL DE DATA
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, ano: Int, mes: Int, dia: Int){
                cal.set(Calendar.YEAR, ano)
                cal.set(Calendar.MONTH, mes)
                cal.set(Calendar.DAY_OF_MONTH, dia)
                updateDateInView()
            }
        }

        this.btnMainSelect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View){
                DatePickerDialog(this@MainActivity,
                        dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        // POPULANDO SPINNERS COM OS STRING-ARRAYS DECLARADOS NO ARQUIVO strings.xml
        val adapterSexo = ArrayAdapter.createFromResource(this, R.array.sexo_array, android.R.layout.simple_spinner_item)
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterEstadoCivil = ArrayAdapter.createFromResource(this, R.array.estado_civil_array, android.R.layout.simple_spinner_item)
        adapterEstadoCivil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spMainSexo.adapter = adapterSexo
        spMainEstadoCivil.adapter = adapterEstadoCivil


        // ENVIANDO PARA Json.kt
        this.btnMainEnviar.setOnClickListener({
            val it = Intent(this, Json::class.java)

            it.putExtra("nome", ArrayList<String>(Arrays.asList(this.etMainNome.text.toString())))
            it.putExtra("data", ArrayList<String>(Arrays.asList(
                    cal.get(Calendar.DAY_OF_MONTH).toString(),
                    (cal.get(Calendar.MONTH)+1).toString(),
                    cal.get(Calendar.YEAR).toString())))
            it.putExtra("sexo", ArrayList<String>(Arrays.asList(this.spMainSexo.selectedItem.toString())))
            it.putExtra("estadoCivil", ArrayList<String>(Arrays.asList(this.spMainEstadoCivil.selectedItem.toString())))

            startActivity(it)
        })
    }

    private fun updateDateInView(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        this.tvMainData.text = sdf.format(cal.time)
    }
}
