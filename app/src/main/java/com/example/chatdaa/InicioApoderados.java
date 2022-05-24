package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Modelo.Apoderado;
import Modelo.Usuario;

public class InicioApoderados extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public List<Usuario> usuarioList;
    Usuario usuario;
    ArrayAdapter<Usuario> adUsuario;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_apoderados);
        iniciarFirebase();
        usuarioList = new ArrayList<Usuario>();
        Bundle extras = getIntent().getExtras();
        String json = extras.getString("Usuario");
        usuario = new Usuario();
        try {
            JSONObject j = new JSONObject(json);
            usuario.setRol(j.getString("rol"));
            usuario.setUser_name(j.getString("user_name"));
            usuario.setContraseña(j.getString("contraseña"));
            usuario.setId(j.getString("id"));
            usuario.setRut(j.getString("rut"));
        }
        catch (JSONException r){
            r.printStackTrace();
        }
        databaseReference.child("Apoderado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objeto: snapshot.getChildren()) {
                    Apoderado apoderado1 = objeto.getValue(Apoderado.class);
                    if (usuario.getId().equals(apoderado1.getId_apoderado())){
                        
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    public void chat(View view){
        Intent i = new Intent(this, InicioAlumno.class);
        startActivity(i);
    }
    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}