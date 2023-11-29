import android.os.Parcel
import android.os.Parcelable

enum class TipoProducto : Parcelable {
    FRUTA,
    VEGETAL,
    BEBESTIBLE,
    OTRO;

    // Métodos necesarios para Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TipoProducto> {
        override fun createFromParcel(parcel: Parcel): TipoProducto {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<TipoProducto?> {
            return arrayOfNulls(size)
        }
    }
}

data class Producto(
    val nombre: String?,
    val id: Int,
    var cantidad: Int,
    var precio: Int,
    val tipo: TipoProducto,
    val descripcion: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(TipoProducto::class.java.classLoader) ?: TipoProducto.OTRO,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(id)
        parcel.writeInt(cantidad)
        parcel.writeInt(precio)
        parcel.writeParcelable(tipo, flags)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}
