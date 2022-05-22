package com.example.chatdaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Modelo.Usuario;

public class lista_usuarios extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public List<Usuario> usuario;
    ArrayAdapter<Usuario> adUsuario;

    ListView listView;
    View vista;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciarFirebase();
        usuario = new ArrayList<Usuario>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario.clear();
                for (DataSnapshot objeto: snapshot.getChildren()) {
                    Usuario user = objeto.getValue(Usuario.class);
                    if (user.getRol().equals("Administrador")){
                    }
                    else{usuario.add(user);}

                }
                adUsuario = new ArrayAdapter<Usuario>(getContext(), android.R.layout.simple_list_item_activated_1, usuario);
                listView = (ListView)vista.findViewById(R.id.list_users);
                listView.setAdapter(adUsuario);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Usuario user = (Usuario) adapterView.getItemAtPosition(i);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", user.getUser_name());
                        bundle.putString("rut", user.getRut());
                        bundle.putString("contraseña", user.getContraseña());
                        bundle.putString("id", user.getId());
                        bundle.putString("rol", user.getRol());
                        getParentFragmentManager().setFragmentResult("key", bundle);


                        if (user.getRol().equals("Apoderado")) {
                            editar_eliminar_apoderado apoderado = new editar_eliminar_apoderado();
                            FragmentManager h = getActivity().getSupportFragmentManager();
                            FragmentTransaction f = h.beginTransaction();
                            f.replace(R.id.fragment_admin, apoderado);
                            f.addToBackStack(null);
                            f.commit();
                        }
                        else{
                            eliminar_edit_user eliminarEditUser = new eliminar_edit_user();
                            FragmentManager m = getActivity().getSupportFragmentManager();
                            FragmentTransaction t = m.beginTransaction();
                            t.replace(R.id.fragment_admin, eliminarEditUser);
                            t.addToBackStack(null);
                            t.commit();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        vista = inflater.inflate(R.layout.fragment_lista_usuarios, container, false);
        // Inflate the layout for this fragment
        return vista;
    }
    public void iniciarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}