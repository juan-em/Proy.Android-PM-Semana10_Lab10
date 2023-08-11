package com.miempresa.lab10

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this)

        var contactorepo = ContactoRepositorio()
        var listaContactos = contactorepo.listar()

        var imagen_list:MutableList<Bitmap> = mutableListOf(
            BitmapFactory.decodeResource(resources,R.drawable.azul),
            BitmapFactory.decodeResource(resources,R.drawable.amarillo),
            BitmapFactory.decodeResource(resources,R.drawable.crema),
            BitmapFactory.decodeResource(resources,R.drawable.rojo),
            BitmapFactory.decodeResource(resources,R.drawable.morado),
            BitmapFactory.decodeResource(resources,R.drawable.verde),
        )

        //obtenemos lista de datos guardados en sugar ORM
        val adapter = AdaptadorContactos(listaContactos as ArrayList<Contacto>,imagen_list)
        lista.adapter = adapter

        btnAgregar.setOnClickListener(){
            var createContacto = Intent(this,RegistroContactos::class.java)
            startActivity(createContacto)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var imagen_list:MutableList<Bitmap> = mutableListOf(
            BitmapFactory.decodeResource(resources,R.drawable.azul),
            BitmapFactory.decodeResource(resources,R.drawable.amarillo),
            BitmapFactory.decodeResource(resources,R.drawable.crema),
            BitmapFactory.decodeResource(resources,R.drawable.rojo),
            BitmapFactory.decodeResource(resources,R.drawable.morado),
            BitmapFactory.decodeResource(resources,R.drawable.verde),
        )
        var contactorepo = ContactoRepositorio()
        var listaContactos = contactorepo.listar()

        val adapter = AdaptadorContactos(listaContactos as ArrayList<Contacto> ,imagen_list)
        lista.adapter = adapter
    }
}