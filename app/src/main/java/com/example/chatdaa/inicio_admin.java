package com.example.chatdaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class inicio_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_admin);
    }
    public void config(View v){
        Intent h = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(h);
    }
}