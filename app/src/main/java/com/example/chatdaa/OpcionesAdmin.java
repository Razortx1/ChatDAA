package com.example.chatdaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class OpcionesAdmin extends Fragment {
    View vista;
    Button boton_add_user, boton_edit_delete_user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_opciones_admin, container, false);

        boton_add_user = (Button) vista.findViewById(R.id.btn_agregaruser);

        boton_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar_usuario i = new agregar_usuario();
                FragmentManager m = getActivity().getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                t.replace(R.id.fragment_admin, i);
                t.addToBackStack(null);
                t.commit();
            }
        });
        boton_edit_delete_user = (Button) vista.findViewById(R.id.btn_editaruser);

        boton_edit_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista_usuarios listaUsuarios = new lista_usuarios();
                FragmentManager m = getActivity().getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                t.replace(R.id.fragment_admin, listaUsuarios);
                t.addToBackStack(null);
                t.commit();
            }
        });
        // Inflate the layout for this fragment
        return vista;
    }

}