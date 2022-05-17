package com.example.chatdaa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CambioContra extends AppCompatActivity {
    EditText txt_antigua_contraseña, txt_nueva_contraseña;
    Button btn_cambiar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contra);

        txt_nueva_contraseña = (EditText) findViewById(R.id.txt_nueva_contra);
        txt_antigua_contraseña = (EditText) findViewById(R.id.txt_antigua_contra);

        btn_cambiar = (Button) findViewById(R.id.btn_actualizar_cambios);
        btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass_nueva = txt_nueva_contraseña.getText().toString();
                String pass_vieja = txt_antigua_contraseña.getText().toString();
                if (pass_nueva.equals("") || pass_vieja.equals("")){
                    Toast.makeText(getApplicationContext(), "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Contraseña Cambiada", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}