package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import Hash.Hash;
import Modelo.Usuario;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText txt_username, txtcontraseña;
    Button btn_ingresar;

    String txt_user, finalpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFirebase();
      
        btn_ingresar = (Button) findViewById(R.id.btn_ingreso);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

    }
    public void login(View view) {
        txt_username = this.findViewById(R.id.txt_user_rut);
        txtcontraseña = this.findViewById(R.id.pass_user);
        txt_user = txt_username.getText().toString();
        String pass = txtcontraseña.getText().toString();
        pass = Hash.md5(pass);
        finalpass = pass;
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objeto : snapshot.getChildren()) {
                    Usuario user = objeto.getValue(Usuario.class);

                    if (user.getRut().equals(txt_user) && user.getContraseña().equals(finalpass)) {
                        if (user.getRol().equals("Apoderado")) {
                            Gson gson = new Gson();
                            String json = gson.toJson(user);
                            Intent i = new Intent(getApplicationContext(), InicioApoderados.class);
                            i.putExtra("Usuario", json);
                            startActivity(i);
                        }
                        if (user.getRol().equals("Alumno")) {
                            Gson gson = new Gson();
                            String json = gson.toJson(user);
                            Intent i = new Intent(getApplicationContext(), InicioAlumno.class);
                            i.putExtra("Usuario", json);
                            startActivity(i);
                        }
                        if (user.getRol().equals("Docente")) {
                            Gson gson = new Gson();
                            String json = gson.toJson(user);
                            Intent i = new Intent(getApplicationContext(), InicioDocentes.class);
                            i.putExtra("Usuario", json);
                            startActivity(i);
                        }
                        if (user.getRol().equals("Administrador")) {
                            Intent ad = new Intent(getApplicationContext(), inicio_admin.class);
                            startActivity(ad);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void cambiar_contra(View v) {
        txt_username = this.findViewById(R.id.txt_user_rut);
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