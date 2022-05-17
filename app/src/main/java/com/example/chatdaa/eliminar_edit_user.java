package com.example.chatdaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class eliminar_edit_user extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnactualizar, btneliminar;
    EditText txtusername, txtrut, txtcontraseña;
    Spinner rol;
    String id;

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

                txtusername.setText(username);
                txtcontraseña.setText(contraseña);
                txtrut.setText(rut);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_eliminar_edit_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtrut = (EditText) view.findViewById(R.id.txt_rut_edit_delete_user);
        txtusername = (EditText) view.findViewById(R.id.txt_name_edit_delete_user);
        txtcontraseña = (EditText) view.findViewById(R.id.txt_pass_edit_delete_user);
        rol = (Spinner) view.findViewById(R.id.spi_rol);
    }
}