package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication8.R;

import java.util.ArrayList;
import java.util.Objects;

public class PreguntaFragment extends Fragment {

    int radioButtonValue = 0;
    private TextView preguntaView, categoriaView;
    private RadioGroup grupoRespuestasView;
    private RadioButton respuestaUno, respuestaDos, respuestaTres, respuestaCuatro;

    public static PreguntaFragment newInstance(String pregunta,
                                               String categoria,
                                               String respuestaCorrecta,
                                               ArrayList<String> respuestasIncorrectas) {
        PreguntaFragment fragment = new PreguntaFragment();
        Bundle arguments = new Bundle();
        arguments.putString("PREGUNTA", pregunta);
        arguments.putString("CATEGORIA", categoria);
        arguments.putString("RESPUESTA_CORRECTA", respuestaCorrecta);
        arguments.putStringArrayList("RESPUESTAS_INCORRECTAS", respuestasIncorrectas);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        final String pregunta = Objects.requireNonNull(getArguments()).getString("PREGUNTA");
        final String categoria = Objects.requireNonNull(getArguments().getString("CATEGORIA"));
        final String respuestaCorrecta =
                Objects.requireNonNull(getArguments().getString("RESPUESTA_CORRECTA"));
        final ArrayList<String> respuestasIncorrectas =
                Objects.requireNonNull(getArguments().getStringArrayList("RESPUESTAS_INCORRECTAS"));
//INICIALIZAMOS LAS VISTAS DECLARADAS
        initializeViews(view);
//ASIGNANDO VALORES DINAMICOS
//EN BASE A LOS DATOS RECIBIDOS DE NUESTRA API ASIGNAMOS VALORES A LAS VISTAS
        preguntaView.setText(pregunta);
        categoriaView.setText(categoria);
//RECORREMOS EL ARREGLO DE STRINGS "RESPUESTAS INCORRECTAS" DE NUESTRA API DE DATOS
        for (int x = 0; x < respuestasIncorrectas.size(); x++) {
            switch (x) {
                case 0:
                    respuestaUno.setText(respuestasIncorrectas.get(x));
                    break;
                case 1:
                    respuestaDos.setText(respuestasIncorrectas.get(x));
                    break;
                case 2:
                    respuestaTres.setText(respuestasIncorrectas.get(x));
                    break;
            }
        }
//AGREGAMOS LA RESPUESTA CORRECTA DE NUESTRA API EN UN CUARTO RADIO BUTTON
        respuestaCuatro.setText(respuestaCorrecta);
//EVENTOS DE RADIO BUTTONS - CODIGO PARA QUE LA SELECCION DEL RADIO BUTTON SEA ACTUALIZADO EN LA VISTA
        grupoRespuestasView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (respuestaUno.isChecked()) {
                    radioButtonValue = 1;
                } else if (respuestaDos.isChecked()) {
                    radioButtonValue = 2;
                } else if (respuestaTres.isChecked()) {
                    radioButtonValue = 3;

                } else if (respuestaCuatro.isChecked()) {
                    radioButtonValue = 4;
                }
            }
        });
        return view;
    }

    private void initializeViews(View view){
        preguntaView = view.findViewById(R.id.numPregunta);
        preguntaView = view.findViewById(R.id.descripcionPregunta);
        categoriaView = view.findViewById(R.id.descripcionCategoria);
        categoriaView = view.findViewById(R.id.categoria);
        grupoRespuestasView = view.findViewById(R.id.radioGroupRespuestas);
        respuestaUno = view.findViewById(R.id.radioButton);
        respuestaDos = view.findViewById(R.id.radioButton2);
        respuestaTres = view.findViewById(R.id.radioButton3);
        respuestaCuatro = view.findViewById(R.id.radioButton4);
    }

}