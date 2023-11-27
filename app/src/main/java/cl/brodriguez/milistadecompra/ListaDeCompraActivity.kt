package cl.brodriguez.milistadecompra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import cl.brodriguez.milistadecompra.Producto





class ListaDeCompraActivity : AppCompatActivity() {

    private lateinit var listViewListaCompra: ListView
    private lateinit var productos: MutableList<Producto>
    private lateinit var adapter : ArrayAdapter<Producto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_compra)

        listViewListaCompra = findViewById(R.id.ListaCompra)

        productos = mutableListOf(
            Producto("Pan", 300, "El pan se come",),
            Producto("Coca-Cola", 1800, "2 litros de Coca-cola",),
            Producto("Hamburguesa", 2300, "Hamburguesa con queso",)
        )

        adapter = AdapterListaCompra(this, R.layout.test, productos)
        listViewListaCompra.adapter = adapter





    }
}