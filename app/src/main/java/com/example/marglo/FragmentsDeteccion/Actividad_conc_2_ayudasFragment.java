package com.example.marglo.FragmentsDeteccion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.marglo.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_conc_2_ayudasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_conc_2_ayudasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_conc_2_ayudasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_conc_2_ayudasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_conc_2_ayudasFragment newInstance(String param1, String param2) {
        Actividad_conc_2_ayudasFragment fragment = new Actividad_conc_2_ayudasFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actividad_conc_2_ayudas, container, false);
    }

    boolean AyudasVisuales, AyudasTactiles;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SwitchMaterial switchVisual, switchTactil;
        Button guardar_btn;

        switchVisual = view.findViewById(R.id.switchVisual_id);
        switchTactil = view.findViewById(R.id.switchTactil_id);

        guardar_btn = view.findViewById(R.id.guardar_btn);


//SWITCH AYUDAS VISUALES
        switchVisual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked==true){
                    AyudasVisuales = true;
                    //guardarPreferenciasAyudas();

                }else{
                    AyudasVisuales = false;
                    // guardarPreferenciasAyudas();
                }
            }
        });

//SWITCH AYUDAS TACTILES
        switchTactil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked==true){
                    AyudasTactiles = true;
                    // guardarPreferenciasAyudas();

                }else{
                    AyudasTactiles = false;
                    //guardarPreferenciasAyudas();
                }
            }
        });

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia

        guardar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferenciasAyudas();
                //Navigation.findNavController(v).navigate(R.id.blankFragment1);//Metodo alterno sin funcion; para hacer navegacion con boton welcome a otro fragment
                //navControllermio.navigate(R.id.actividad_conc_2Fragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_conc_2_ayudasFragment_to_actividad_conc_2Fragment); //pasar a otro fragment con actions
            }

        });



    }

    //Shared PReferences para ayudas
    private void guardarPreferenciasAyudas(){
        SharedPreferences preferencesayudas = getActivity().getSharedPreferences("PreferenciasAyudas", Context.MODE_PRIVATE);
        boolean visuales = AyudasVisuales;
        boolean tactiles = AyudasTactiles;

        SharedPreferences.Editor editor = preferencesayudas.edit();
        editor.putBoolean("AyudasVisuales", visuales);
        editor.putBoolean("AyudasTactiles", tactiles);
        editor.commit();
    }


}//final