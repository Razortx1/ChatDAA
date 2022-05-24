package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class VistadeAlumnos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistade_alumnos);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        NavigationView nav = (NavigationView) findViewById(R.id.nav_curso);
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
    public void chat(View view){
        Intent i = new Intent(this, Chat.class);
        startActivity(i);
    }
}