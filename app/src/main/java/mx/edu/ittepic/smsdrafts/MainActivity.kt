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

}
