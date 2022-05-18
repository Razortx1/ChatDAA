package com.example.chatdaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Hash.Hash;
import Modelo.Usuario;

public class eliminar_edit_user extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnactualizar, btneliminar;
    EditText txtusername, txtrut, txtcontraseña;
    String id, rol;

    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String username = result.getString("username");
                String rut = result.getString("rut");
                String contraseña = result.getString("contraseña");
                contraseña = Hash.getHash(contraseña, "md5");
                contraseña = Hash.getHash(contraseña, "md5");
                id = result.getString("id");
                rol = result.getString("rol");


                txtusername.setText(username);
                txtcontraseña.setText(contraseña);
                txtrut.setText(rut);
            }
        });
        iniciarFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_eliminar_edit_user, container, false);
        btnactualizar = (Button) view.findViewById(R.id.btn_edit_user);
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnactualizar(view);
            }
        });

        btneliminar = (Button) view.findViewById(R.id.btn_delete_user);
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtneliminar(view);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtrut = (EditText) view.findViewById(R.id.txt_rut_edit_delete_user);
        txtusername = (EditText) view.findViewById(R.id.txt_name_edit_delete_user);
        txtcontraseña = (EditText) view.findViewById(R.id.txt_pass_edit_delete_user);
    }
    public void setBtnactualizar(View view){
        Usuario user = new Usuario();
        user.setId(id);
        user.setRut(txtrut.getText().toString());
        user.setUser_name(txtusername.getText().toString());
        user.setContraseña(Hash.md5("123"));
        user.setRol(rol);
        databaseReference.child("Usuario").child(id).setValue(user);
        Toast.makeText(getContext(), "Este usuario fue actualizado exitosamente", Toast.LENGTH_SHORT).show();
    }
    public void setBtneliminar(View view){
        databaseReference.child("Usuario").child(id).removeValue();
        Toast.makeText(getContext(), "Este usuario fue eliminado exitosamente", Toast.LENGTH_SHORT).show();
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}