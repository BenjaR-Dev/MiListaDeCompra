package cl.brodriguez.milistadecompra

import Producto
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat

class InventarioActivity : AppCompatActivity() {

    private lateinit var listViewListaCompra: ListView
    private lateinit var productos: MutableList<Producto>
    private lateinit var adapter : ArrayAdapter<Producto>

    val CODIGO_DETALLE_PRODUCTO = 1
    val CODIGO_CREAR_PRODUCTO = 3
    val CODIGO_ELIMINAR_PRODUCTO = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        //Colores de arriba y abajo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.Green3)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.Green3)
        }

        //Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listViewListaCompra = findViewById(R.id.ListaCompra)

        //Productos de ejemplo
        productos = mutableListOf(
            Producto("Pan", 1, 3, 300, TipoProducto.OTRO,"El pan se come",),
            Producto("Coca-Cola", 2, 1, 1800, TipoProducto.BEBESTIBLE, "2 litros de Coca-cola",),
        )

        //Adapter
        adapter = AdapterInventario(this, R.layout.test, productos)
        listViewListaCompra.adapter = adapter


        listViewListaCompra.setOnItemClickListener { parent, view, position, id ->
            val productoSeleccionado = productos[position]
            mostrarDetalleProducto(productoSeleccionado)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val accion = data?.getStringExtra("accion")

        if (requestCode == CODIGO_DETALLE_PRODUCTO && resultCode == Activity.RESULT_OK) {
            if (accion == "editar"){
                val productoEditado2 = data?.getParcelableExtra<Producto>("producto_editado2")

                if (productoEditado2 != null) {
                    // Buscar y actualizar el producto en la lista
                    val indiceProducto = productos.indexOfFirst { it.id == productoEditado2.id }

                    if (indiceProducto != -1) {
                        productos[indiceProducto] = productoEditado2
                        adapter.notifyDataSetChanged()

                    }
                }
            }

            if (accion == "eliminar"){
                val productoEliminado = data?.getParcelableExtra<Producto>("producto_eliminado")

                // Eliminar el producto de la lista
                if (productoEliminado != null) {
                    productos.remove(productoEliminado)
                    adapter.notifyDataSetChanged()
                }
            }

        }

        if (requestCode == CODIGO_CREAR_PRODUCTO && resultCode == Activity.RESULT_OK) {
            val nuevoProducto: Producto? = data?.getParcelableExtra("producto_creado") as? Producto
            if (nuevoProducto != null) {
                // Agregar el nuevo produto a la lista y notificar al adaptador
                productos.add(nuevoProducto)
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_inventario, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Acción al presionar la flecha hacia atrás
                true
            }
            R.id.action_anadir -> {
                // Acción cuando se selecciona la opción 1 (Añadir)
                val intent = Intent(this, CrearProductoActivity::class.java)

                //Encuentra el ID más alto para que el siguiente objeto a crear tenga un ID único
                var maxId = Int.MIN_VALUE
                for (producto in productos) {
                    if (producto.id > maxId) {
                        maxId = producto.id
                    }
                }
                if (maxId < 0){
                    maxId = 0
                }
                intent.putExtra("id_actual", maxId)

                startActivityForResult(intent, CODIGO_CREAR_PRODUCTO)
                true
            }
            R.id.action_buscar -> {
                // Acción cuando se selecciona la opción 2 (Buscar)

                true
            }
            R.id.action_ordenar -> {
                // Acción cuando se selecciona la opción 3 (Ordenar)

                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDetalleProducto(producto: Producto) {
        val intent = Intent(this, DetalleProductoActivity::class.java)
        intent.putExtra("producto_detalle", producto)
        startActivityForResult(intent, CODIGO_DETALLE_PRODUCTO)
    }
}