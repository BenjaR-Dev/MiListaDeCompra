package cl.brodriguez.milistadecompra

import Producto
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class EditarProductoActivity : AppCompatActivity() {

    private lateinit var editTextPrecio: EditText
    private lateinit var editTextCantidad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        //Colores de arriba y abajo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.Green3)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.Green3)
        }

        val productoAEditar: Producto? = intent.getParcelableExtra("producto_a_editar") ?: intent.getParcelableExtra<Parcelable>("producto_a_editar") as? Producto

        //Inicializacion
        editTextPrecio = findViewById(R.id.editTextPrecio)
        editTextCantidad = findViewById(R.id.editTextCantidad)


        editTextPrecio.setText(productoAEditar?.precio.toString())
        editTextCantidad.setText(productoAEditar?.cantidad.toString())
    }

    fun guardarEdicion(view: View){
        val productoAEditar: Producto? = intent.getParcelableExtra("producto_a_editar") ?: intent.getParcelableExtra<Parcelable>("producto_a_editar") as? Producto

        val nuevoPrecio = editTextPrecio.text.toString().toIntOrNull()
        val nuevaCantidad = editTextCantidad.text.toString().toIntOrNull()

        if (nuevoPrecio != null && nuevaCantidad != null) {
            productoAEditar?.precio = nuevoPrecio
            productoAEditar?.cantidad = nuevaCantidad

            //Se envía de vuelta
            val intent = Intent()
            intent.putExtra("producto_editado1", productoAEditar)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }


}