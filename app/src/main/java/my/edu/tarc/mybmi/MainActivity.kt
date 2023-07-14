package my.edu.tarc.mybmi

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declare local variable :)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)

        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)

        val buttonCalculate:Button = findViewById(R.id.buttonCalculate)
        val buttonReset:Button = findViewById(R.id.buttonReset)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)

        buttonCalculate.setOnClickListener{

            if(editTextWeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener //to stop program execution~
            }

            if(editTextHeight.text.isBlank()){
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener //to stop program execution~
            }

            val weight:Float = editTextWeight.text.toString().toFloat()
            val height:Float = editTextHeight.text.toString().toFloat()
            val bmi = weight/ (height/100).pow(2)

            textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)

            if(bmi < 18.5){
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.underweight))
                imageViewBMI.setImageResource(R.drawable.under)
            }
            else if(bmi > 18.5 && bmi < 24.9){
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
                imageViewBMI.setImageResource(R.drawable.normal)
            }
            else{
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.overweight))
                imageViewBMI.setImageResource(R.drawable.over)
            }
        }

        buttonReset.setOnClickListener {
            editTextWeight.setText("")
            editTextHeight.setText("")
            textViewBMI.text = getString(R.string.bmi)
            textViewStatus.text = getString(R.string.status)
            imageViewBMI.setImageResource(R.drawable.empty)
        }

    }
}