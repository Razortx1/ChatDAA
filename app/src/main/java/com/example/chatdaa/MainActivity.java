package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View s){
        EditText ecorreo = this.findViewById(R.id.txt_user);
        EditText epass = this.findViewById(R.id.pass_user);
        String correo = ecorreo.getText().toString();
        String pass = epass.getText().toString();
        if (correo.equals("Alumno")){
            if (pass.equals("123")){
                Intent i = new Intent(this,InicioAlumno.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como alumno", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (correo.equals("Docente")){
            if (pass.equals("123")){
                Intent i = new Intent(this,InicioDocentes.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como docente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (correo.equals("Apoderado")) {
            if (pass.equals("123")) {
                Intent i = new Intent(this, InicioApoderados.class);
                startActivity(i);
                Toast.makeText(this, "Ingresaste como apoderado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contraseña Equivocada", Toast.LENGTH_SHORT).show();
            }
        }
        else if (correo.equals("Administrator")){
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
    public void cambiar_contra(View v){
        Intent o = new Intent(getApplicationContext(), CambioContra.class);
        startActivity(o);}

}