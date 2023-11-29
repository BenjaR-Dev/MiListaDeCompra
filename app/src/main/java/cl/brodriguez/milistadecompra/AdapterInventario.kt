package cl.brodriguez.milistadecompra

import Producto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdapterInventario (
    context: Context,
    resource: Int,
    productos: List<Producto>
) : ArrayAdapter<Producto>(context, resource, productos){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.test, null)

        val producto = getItem(position)

        val nombreTextView = listItemView.findViewById<TextView>(R.id.nombre_producto)
        val precioTextView = listItemView.findViewById<TextView>(R.id.precio_producto)
        val descripcionTextView = listItemView.findViewById<TextView>(R.id.descripcion_producto)

        //no es nulo
        if (producto != null) {
            // Establecer los valores
            nombreTextView.text = producto.nombre
            descripcionTextView.text = producto.descripcion

            //el precio no sea negativo
            val precio = if (producto.precio >= 0) producto.precio.toString() else "No disponible"
            precioTextView.text = "Precio: $precio"
        }

        return listItemView
    }

}