package com.example.myapplication8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import api.RetrofitClient;
import api.api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Creando la actividad");
        api service = RetrofitClient.getRetrofitInstance().create(api.class);
        Call<PreguntasLista> call = service.getAllQuestions();
//Async
        call.enqueue(new Callback<PreguntasLista>() {
            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                if(response != null) {
                    Log.d(TAG, response.toString());
                    for(int x = 0; x < response.body().getResults().size(); x++) {
                        Log.d(TAG, "Pregunta: "+response.body().getResults().get(x).getQuestion());
                    }
                }
            }
            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: no pudimos recuperar los datos de opentdb", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
