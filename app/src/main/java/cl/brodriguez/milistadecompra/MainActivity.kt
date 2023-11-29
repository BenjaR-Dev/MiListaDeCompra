package cl.brodriguez.milistadecompra

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 3000 // Duración de la pantalla de bienvenida en milisegundos

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Colores de arriba y abajo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.Green2)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.Green2)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.inicio)
        mediaPlayer.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            mediaPlayer.stop()
            startActivity(intent)
            finish()

        }, 3000)


    }

}