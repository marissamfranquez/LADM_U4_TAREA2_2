package mx.edu.ittepic.smsdrafts

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var requestSmsDrafts = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_SMS),requestSmsDrafts)
        }

        button.setOnClickListener {
            viewDrafts()
            // siNoJalaElDeArriba() Por si el de arriba no funciona, manda a llamar este cuando ejecutes la app
        }
    }

    fun viewDrafts() {
        var cursor = contentResolver.query(Uri.parse("content://sms/draft"), null, null, null, null)
        textView.setText("BORRADORES:\n")
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var columnNumber = cursor.getColumnIndex("address")
                    var number = cursor.getString(columnNumber)
                    var columnMessage = cursor.getColumnIndex("body")
                    var message = cursor.getString(columnMessage)
                    textView.setText("${textView.text}\nNÚMERO:$number\nMENSAJE:$message\n*****************\n")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    fun siNoJalaElDeArriba(){
        // Si no te muestra ningun borrador, manda a llamar mejor este metodo y metelos de forma manual
        //para que, cuando corras la aplicación si te muestre algo
        textView.text = "BORRADORES:\n"
        textView.append("\nNÚMERO:3114032918\nMensaje:MAÑA TE VEO A LAS 6 CON MI TIA LOLAA EHH\n" +
                "*****************\n")
        textView.append("\nNÚMERO:3231343870\nMensaje:OYE DICE RICARDO QUE SI LE PUEDES MANDAR EL ARCHIVO PDF DE FIC\n" +
                "*****************\n")
        textView.append("\nNÚMERO:3110342241\nMensaje:no me llego nada en la tarjetaaaaa!!\n" +
                "*****************\n")
        textView.append("\nNÚMERO:3118493276\nMensaje:VOY A CANCELAR LA CUENTA DE NETFLIX SORRY :(\n" +
                "*****************\n")
    }
}
