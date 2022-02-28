package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val TAG = "RealTime"

    //Declaramos la variable auth
    private lateinit var auth: FirebaseAuth
    //Declaramos la variable de database
    //private lateinit var database: DatabaseReference


    //Declaramos las variables de email, contraseña, registrar e iniciar
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var registrar: Button
    lateinit var iniciar: Button
    lateinit var mapaIntent: Intent
    lateinit var realTime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializamos las variables creadas anteriormente
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        registrar = findViewById(R.id.registrar)
        iniciar = findViewById(R.id.iniciar)
        mapaIntent = Intent(this, MapsActivity::class.java)
        realTime = findViewById(R.id.realtime)


        //Inicializamos Fire base auth
        auth = Firebase.auth

        //Inicializamos la database
        //database = Firebase.database("https://trimestre-218c9-default-rtdb.europe-west1.firebasedatabase.app/").reference


        //Hacemos la llamada para el metodo de iniciar sesión
        iniciar.setOnClickListener {
            signIn(email.text.toString(), password.text.toString())

        }
       /* realTime.setOnClickListener {
            writeNewUser("AA03", 4.567, 5.678, "pepe" )
        }*/

    }

    /*private fun writeNewUser(id: String, lt: Double, lg: Double, nombre: String){
        Log.d(TAG, "Escribiendo datos")
        val user = users( lt, lg, nombre)
        Log.d (TAG, user.toString())

        database.child("users/"+id).setValue(user)

    }*/

    private fun updateUI(user: FirebaseUser?) {
        Log.d("estado", "" + auth.currentUser?.uid)
    }

    /**
     * Método que sirve para iniciar sesión a través de un email y contraseña
     */
    private fun signIn(email: String, password: String) {



        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Iniciamos sesion con la información del usuario.
                    Log.d(TAG, "signInWithEmail:success")
                    Log.d("estado", "inicio de sesión correcto")

                    val user = auth.currentUser
                    updateUI(user)
                    startActivity(mapaIntent)

                } else {
                    //Si los datos no son correctos, envia un mensaje al usuario,
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Log.d("estado", "No se puedo iniciar sesion")

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }
}

