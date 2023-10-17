package cl.brodriguez.milistadecompra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.brodriguez.milistadecompra.Producto

class AdapterListaCompra (
    context: Context,
    resource: Int,
    productos: List<Producto>
) : ArrayAdapter<Producto>(context, resource, productos){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.test, null)

        // Get the patient data at the current position
        val producto = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nombreTextView = listItemView.findViewById<TextView>(R.id.nombre_producto)
        val precioTextView = listItemView.findViewById<TextView>(R.id.precio_producto)
        val descripcionTextView = listItemView.findViewById<TextView>(R.id.descripcion_producto)

        // Set the patient data in the TextViews
        nombreTextView.text = producto?.nombre
        precioTextView.text = producto?.precio.toString()
        descripcionTextView.text = producto?.descripcion

        return listItemView
    }

}