package cl.brodriguez.milistadecompra

import Producto
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var nombreTextView: TextView
    private lateinit var IDTextView: TextView
    private lateinit var cantidadTextView: TextView
    private lateinit var precioTextView: TextView
    private lateinit var tipoTextView: TextView
    private lateinit var descripcionTextView: TextView

    val CODIGO_EDICION_PRODUCTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        //Colores de arriba y abajo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.Green3)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.Green3)
        }

        //Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_detalles)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Textos
        nombreTextView = findViewById(R.id.textViewNombre)
        IDTextView = findViewById(R.id.textViewID)
        cantidadTextView = findViewById(R.id.textViewCantidad)
        precioTextView = findViewById(R.id.textViewPrecio)
        tipoTextView = findViewById(R.id.textViewTipo)
        descripcionTextView = findViewById(R.id.textViewDescripcion)


        // Producto entrante
        val producto: Producto? = intent.getParcelableExtra("producto_detalle")

        if (producto != null) {
            nombreTextView.text = producto.nombre
            IDTextView.text = getString(R.string.id) + " " + producto.id.toString()
            cantidadTextView.text = getString(R.string.cantidad) + " " + producto.cantidad.toString()
            precioTextView.text = getString(R.string.precio) + " " + producto.precio.toString()
            tipoTextView.text = getString(R.string.tipo) + " " + producto.tipo.toString()
            descripcionTextView.text = getString(R.string.descripcion) + " " + producto.descripcion
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_detalles, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val producto: Producto? = intent.getParcelableExtra("producto_detalle")

        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Acción al presionar la flecha hacia atrás
                true
            }
            R.id.action_editar -> {
                // Acción cuando se selecciona la opción 1 (Editar)
                val intent = Intent(this, EditarProductoActivity::class.java)
                intent.putExtra("producto_a_editar", producto)
                startActivityForResult(intent, CODIGO_EDICION_PRODUCTO)
                true
            }
            R.id.action_eliminar -> {
                // Acción cuando se selecciona la opción 2 (Eliminar)
                val intent = Intent()
                intent.putExtra("producto_eliminado", producto)
                intent.putExtra("accion", "eliminar")
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODIGO_EDICION_PRODUCTO && resultCode == Activity.RESULT_OK) {
            val productoEditado = data?.getParcelableExtra<Producto>("producto_editado1")

            if (productoEditado != null) {
                precioTextView = findViewById(R.id.textViewPrecio)
                precioTextView.text = getString(R.string.precio) + " " + productoEditado.precio.toString()
                cantidadTextView = findViewById(R.id.textViewCantidad)
                cantidadTextView.text = getString(R.string.cantidad) + " " + productoEditado.cantidad.toString()

                val intent = Intent()
                intent.putExtra("producto_editado2", productoEditado)
                intent.putExtra("accion", "editar")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

}