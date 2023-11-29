package cl.brodriguez.milistadecompra

import Producto
import TipoProducto
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.ContextCompat


class CrearProductoActivity : AppCompatActivity() {

    private lateinit var spinnerTipoProducto: Spinner
    private var lastProductId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)

        //Colores de arriba y abajo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.Green3)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.Green3)
        }

        lastProductId = intent.getIntExtra("id_actual", 0)

        spinnerTipoProducto = findViewById(R.id.spinnerTipoProducto)
        val opcionesTipoProducto = arrayOf("Fruta", "Vegetal", "Bebestible", "Otro")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesTipoProducto)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipoProducto.adapter = adapter

        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            guardarProducto()
        }

    }

    private fun guardarProducto() {
        val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
        val cantidad = findViewById<EditText>(R.id.editTextCantidad).text.toString().toIntOrNull() ?: 0
        val precio = findViewById<EditText>(R.id.editTextPrecio).text.toString().toIntOrNull() ?: 0
        val tipo = obtenerTipoSeleccionado()
        val descripcion = findViewById<EditText>(R.id.editTextDescripcion).text.toString()

        //se crea el producto con los valores ingresados por el usuario
        val producto = Producto(nombre, getNextProductId(), cantidad, precio, tipo, descripcion)

        //Se retorna al inventario
        val intent = Intent()
        intent.putExtra("producto_creado", producto)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

    private fun getNextProductId(): Int {
        lastProductId++
        return lastProductId
    }


    private fun obtenerTipoSeleccionado(): TipoProducto {
        val tipoSeleccionado = spinnerTipoProducto.selectedItemPosition
        return when (tipoSeleccionado) {
            0 -> TipoProducto.FRUTA
            1 -> TipoProducto.VEGETAL
            2 -> TipoProducto.BEBESTIBLE
            else -> TipoProducto.OTRO
        }
    }
}