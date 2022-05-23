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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Hash.Hash;
import Modelo.Apoderado;
import Modelo.Usuario;

public class editar_eliminar_apoderado extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    EditText name_user, rut_user, rut_alumno, pass;
    String id, rol, id_alumno;
    Button btn_eliminaruser, btn_actualizar;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciarFirebase();

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

                name_user.setText(username);
                pass.setText(contraseña);
                rut_user.setText(rut);


            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_editar_eliminar_apoderado, container, false);
        btn_actualizar = (Button) view.findViewById(R.id.btn_edit_apoderado);
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn_actualizar(view);
            }
        });
        btn_eliminaruser = (Button) view.findViewById(R.id.btn_delete_apoderado);
        btn_eliminaruser.setOnClickListener(new View.OnClickListener() {
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
        rut_user = (EditText) view.findViewById(R.id.txt_rut_edit_delete_apoderado);
        name_user = (EditText) view.findViewById(R.id.txt_name_edit_delete_apoderado);
        rut_alumno = (EditText) view.findViewById(R.id.txt_user_rut_a);
        pass = (EditText) view.findViewById(R.id.txt_pass_edit_delete_apoderado);
    }

    public void setBtn_actualizar(View view){
        Usuario user = new Usuario();
        user.setId(id);
        user.setRut(rut_user.getText().toString());
        user.setUser_name(name_user.getText().toString());
        user.setContraseña(Hash.md5("123"));
        user.setRol(rol);


        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objeto: snapshot.getChildren()) {
                    Usuario user = objeto.getValue(Usuario.class);
                    if (user.getRut().equals(rut_alumno.getText().toString())){
                        id_alumno = user.getId();
                    }
                }
                System.out.println(id_alumno);
                Apoderado apoderado = new Apoderado();
                apoderado.setId_apoderado(id);
                apoderado.setId_alumno(id_alumno);
                String isempy = apoderado.getId_alumno();

                if (isempy == null){
                    Toast.makeText(getContext(), "El alumno no existe, intentelo nuevamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Usuario").child(id).setValue(user);
                    databaseReference.child("Apoderado").child(id).child(apoderado.getId_alumno()).setValue(apoderado);
                    Toast.makeText(getContext(), "Este usuario fue actualizado exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void setBtneliminar(View view){
        databaseReference.child("Usuario").child(id).removeValue();
        databaseReference.child("Apoderado").child(id).removeValue();
        Toast.makeText(getContext(), "Este usuario fue eliminado exitosamente", Toast.LENGTH_SHORT).show();
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}