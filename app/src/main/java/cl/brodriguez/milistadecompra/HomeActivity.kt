package cl.brodriguez.milistadecompra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun irAMisProductos(view: View){
        val intent = Intent(this, MisProductosActivity::class.java)
        startActivity(intent)
    }

    fun irAListaDeCompra(view: View){
        val intent = Intent(this, ListaDeCompraActivity::class.java)
        startActivity(intent)
    }

    fun irALugaresDeInteres(view: View){
        val intent = Intent(this, LugaresDeInteresActivity::class.java)
        startActivity(intent)
    }

}