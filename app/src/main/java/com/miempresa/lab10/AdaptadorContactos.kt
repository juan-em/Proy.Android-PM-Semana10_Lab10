package com.miempresa.lab10

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable


class AdaptadorContactos (val ListaContactos:ArrayList<Contacto>, var listaimagen:MutableList<Bitmap>): RecyclerView.Adapter<AdaptadorContactos.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val fimagen = itemView.findViewById<ImageView>(R.id.imgFoto)
        val fnombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fcorreo = itemView.findViewById<TextView>(R.id.lblCorreo)
        val ftelefono = itemView.findViewById<TextView>(R.id.lblTelefono)

        val feliminar = itemView.findViewById<ImageButton>(R.id.btnEliminar)
        val fveryeditar = itemView.findViewById<ImageButton>(R.id.btnVeryeditar)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.vista_contacto, parent, false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: AdaptadorContactos.ViewHolder, position: Int) {

        var contact = this.ListaContactos.get(position)
        holder?.fimagen?.setImageBitmap(listaimagen.get((0..5).random()))
        holder?.fnombre?.text=ListaContactos[position].nombre
        holder?.fcorreo?.text=ListaContactos[position].correo
        holder?.ftelefono?.text=ListaContactos[position].telefono

        holder?.feliminar.setOnClickListener(){

            val builder = AlertDialog.Builder(holder.feliminar.context)
            builder.setTitle("¡¡Atención!!")
            builder.setMessage("¿Desea eliminar el reigstro?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                var contrepo = ContactoRepositorio()
                contrepo.borrar(contact.id!!)
                this.ListaContactos.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,itemCount)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
            }
            builder.show()


        }

        holder?.fveryeditar.setOnClickListener(){
            var contrepo =ContactoRepositorio()
            var contacto = contrepo.leer(contact.id!!)

            var editarContacto = Intent(holder?.fveryeditar.context, RegistroContactos::class.java)
            editarContacto.putExtra("contacto",contacto as Serializable)
            holder?.fveryeditar.context.startActivity(editarContacto)
        }
    }

    override fun getItemCount(): Int {
        return ListaContactos.size
    }
}


