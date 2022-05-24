package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

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
    String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usuarioList.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_apoderados);
        iniciarFirebase();
        usuarioList = new ArrayList<Usuario>();
        Bundle extras = getIntent().getExtras();
        json = extras.getString("Usuario");
        usuario = new Usuario();
        try {
            JSONObject j = new JSONObject(json);
            usuario.setRol(j.getString("rol"));
            usuario.setUser_name(j.getString("user_name"));
            usuario.setContraseña(j.getString("contraseña"));
            usuario.setId(j.getString("id"));
            usuario.setRut(j.getString("Rut"));
        }
        catch (JSONException r){
            r.printStackTrace();
        }
        databaseReference.child("Apoderado").child(usuario.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objeto: snapshot.getChildren()) {
                    Apoderado apoderado1 = objeto.getValue(Apoderado.class);
                    databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot alumno: snapshot.getChildren()) {
                                Usuario user = alumno.getValue(Usuario.class);
                                System.out.println("objeto alumno :" + user);
                                if (user.getId().equals(apoderado1.getId_alumno())){
                                    usuarioList.add(user);
                                }
                            }
                            adUsuario = new ArrayAdapter<Usuario>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, usuarioList);
                            listView = (ListView) findViewById(R.id.list_alumnos_apode);
                            listView.setAdapter(adUsuario);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Usuario a = (Usuario) adapterView.getItemAtPosition(i);
                                    Apoderado apoderado = new Apoderado();
                                    apoderado.setId_alumno(a.getId());
                                    apoderado.setId_apoderado(apoderado1.getId_apoderado());
                                    Gson gson = new Gson();
                                    String json1 = gson.toJson(apoderado);
                                    Intent in = new Intent(getApplicationContext(), InicioAlumno.class);
                                    in.putExtra("usuario", json1);
                                    startActivity(in);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        NavigationView nav = (NavigationView) findViewById(R.id.nav_apoderado);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.inicio) {
                    Toast.makeText(getApplicationContext(), "Ya te encuentras en inicio", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.chat_r){
                    Toast.makeText(getApplicationContext(), "Trasnportando a Chat Reciente", Toast.LENGTH_SHORT).show();
                    Intent o = new Intent(getApplicationContext(), ChatReciente.class);
                    startActivity(o);
                }else if (id == R.id.cerrar_sesion){
                    Toast.makeText(getApplicationContext(), "Cerrando Sesion", Toast.LENGTH_SHORT).show();
                    Intent r = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(r);
                }
                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal_apo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dl,R.string.abrir_drawer,R.string.cerrar_drawer);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }
                else{
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });
    }

    public void config(){
        Intent configu = new Intent(getApplicationContext(), Configuracion.class);
        configu.putExtra("id",usuario.getId());
        startActivity(configu);
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}