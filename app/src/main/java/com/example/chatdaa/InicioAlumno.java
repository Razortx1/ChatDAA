package com.example.chatdaa;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import Modelo.Curso;
import Modelo.Usuario;


public class InicioAlumno extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Curso curso1;

    public List<Curso> docenteList;
    ListView listView_docente;
    ArrayAdapter<Curso> addocente;

    String json;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alumno);
        iniciarFirebase();

        Bundle extras = getIntent().getExtras();
        json = extras.getString("Usuario");
        user = new Usuario();
        try {
            JSONObject j = new JSONObject(json);
            user.setRut(j.getString("Rut"));
            user.setRol(j.getString("rol"));
            user.setUser_name(j.getString("user_name"));
            user.setContraseña(j.getString("contraseña"));
            user.setId(j.getString("id"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        databaseReference.child("Curso").child(user.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot curso: snapshot.getChildren()) {
                    curso1 = curso.getValue(Curso.class);
                }
                databaseReference.child("Usuario").child(curso1.getId_usuarios()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot result) {
                        for (DataSnapshot docente: result.getChildren()) {
                            Usuario usuario1 = result.getValue(Usuario.class);
                            System.out.println(usuario1.getContraseña());
                            System.out.println(usuario1.getId());
                            System.out.println(usuario1.getRol());
                            System.out.println(usuario1.getRut());
                            System.out.println(usuario1.getUser_name());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        NavigationView nav = (NavigationView) findViewById(R.id.nav);
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
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal_curso);
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
    public void config(View v){
        Intent h = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(h);
    }
    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}