package com.example.chatdaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionesAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionesAdmin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button boton_add_user;

    public OpcionesAdmin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionesAdmin.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionesAdmin newInstance(String param1, String param2) {
        OpcionesAdmin fragment = new OpcionesAdmin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        // Inflate the layout for this fragment
        return vista;
    }

}