package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText txt_username, txtcontraseña;
    String rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFirebase();
    }
    public void login(View s){
        txt_username = this.findViewById(R.id.txt_user);
        txtcontraseña = this.findViewById(R.id.pass_user);
        String txt_user = txt_username.getText().toString();
        String pass = txtcontraseña.getText().toString();
        if (txt_user.equals("Alumno")){
            if (pass.equals("123")){
                Intent i = new Intent(this,InicioAlumno.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como alumno", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (txt_user.equals("Docente")){
            if (pass.equals("123")){
                Intent i = new Intent(this,InicioDocentes.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como docente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (txt_user.equals("Apoderado")) {
            if (pass.equals("123")) {
                Intent i = new Intent(this, InicioApoderados.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como apoderado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (txt_user.equals("Administrator")){
            if (pass.equals("123")){
                Intent i = new Intent(this,inicio_admin.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como ADMIN", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Correo equivocado", Toast.LENGTH_SHORT).show();
        }

    }
    public void cambiar_contra(View v) {
        txt_username = this.findViewById(R.id.txt_user);
        String txtusername = txt_username.getText().toString();
        if (txtusername.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Para poder cambiar la contraseña, al menos ingrese nombre", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent o = new Intent(getApplicationContext(), CambioContra.class);
            startActivity(o);
        }
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}