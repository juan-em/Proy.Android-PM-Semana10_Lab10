package com.miempresa.lab10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro_contactos.*

class RegistroContactos : AppCompatActivity() {
    var edita:Boolean = false
    var id: Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_contactos)
        btnRegistrar.setOnClickListener(){
            var nombre = txtNombre.text.toString()
            var correo = txtCorreo.text.toString()
            var telefono = txtTelefono.text.toString()
            var observaciones = txtObservaciones.text.toString()

            if(nombre.isEmpty()||correo.isEmpty()||telefono.isEmpty()||observaciones.isEmpty()){
                Toast.makeText(this,"Existencia de campos vacíos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (edita) {
                var contactorepo = ContactoRepositorio()
                contactorepo.actualizar(id, nombre, correo, telefono, observaciones)
            } else {
                var contactorepo = ContactoRepositorio()
                contactorepo.crear(nombre, correo, telefono, observaciones)
            }
            finish()


        }
        var recibidos:Bundle? = intent.extras
        if(recibidos!=null){
            var contacto = recibidos?.get("contacto") as Contacto
            edita = true
            id = contacto.id!!
            txtNombre.setText(contacto.nombre)
            txtTelefono.setText(contacto.telefono)
            txtCorreo.setText(contacto.correo)
            txtObservaciones.setText(contacto.observaciones)

        }
    }
}