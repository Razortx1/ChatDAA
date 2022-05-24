package com.example.chatdaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Hash.Hash;
import Modelo.Usuario;

public class agregar_usuario extends DialogFragment {

    Button btn_agregaruser;
    View v;
    EditText txtRut, txtUserName, txtContraseña;
    Spinner spiRol;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciarFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_agregar_usuario, container, false);

        btn_agregaruser = (Button) v.findViewById(R.id.btn_add_user);
        btn_agregaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRut = (EditText) v.findViewById(R.id.txt_rut_newuser);
                txtUserName = (EditText) v.findViewById(R.id.txt_name_newuser);
                txtContraseña = (EditText) v.findViewById(R.id.txt_pass_newuser);
                spiRol = (Spinner) v.findViewById(R.id.spi_roles);

                String rut = txtRut.getText().toString();
                String user_name = txtUserName.getText().toString();
                String contraseña = txtContraseña.getText().toString();
                contraseña = Hash.md5(contraseña);
                contraseña = Hash.md5(contraseña);
                String rol = spiRol.getSelectedItem().toString();
                String id = UUID.randomUUID().toString();

                Usuario user = new Usuario();
                    if(rut.isEmpty() || user_name.isEmpty() || contraseña.isEmpty() || rol.equals("Selecciona un Rol")) {
                        Toast.makeText(getContext(), "Falta rellenar cambios", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        user.setId(id);
                        user.setRut(rut);
                        user.setUser_name(user_name);
                        user.setContraseña(contraseña);
                        user.setRol(rol);
                        System.out.println(user);
                        databaseReference.child("Usuario").child(id).setValue(user);
                        Toast.makeText(getContext(), "Usuario creado con exito", Toast.LENGTH_SHORT).show();

                        Activity activity = getActivity();
                        if (activity != null){
                            activity.onBackPressed();
                        }
                    }
            }
        });
        return v;
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}