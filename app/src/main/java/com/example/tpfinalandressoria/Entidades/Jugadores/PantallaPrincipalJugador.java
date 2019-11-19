package com.example.tpfinalandressoria.Entidades.Jugadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tpfinalandressoria.Entidades.EntornoDeDatos;
import com.example.tpfinalandressoria.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PantallaPrincipalJugador extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    EditText jugadornombre;
    Button botonbuscarjugador;
    Button botonagregarjugador;
    ListView listjugador;

    JugadorAdapter MyJugadorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principaljugador);


        botonagregarjugador = findViewById(R.id.botonagregarjugador);
        botonbuscarjugador = findViewById(R.id.botonbuscarjugador);
        listjugador = findViewById(R.id.listjugador);
        jugadornombre = findViewById(R.id.jugadornombre);

        botonbuscarjugador.setOnClickListener(this);
        botonagregarjugador.setOnClickListener(this);
        listjugador.setOnItemClickListener(this);

        MyJugadorAdapter = new JugadorAdapter(getApplicationContext());

        listjugador.setAdapter(MyJugadorAdapter);

        EntornoDeDatos.BuscarClub(getApplicationContext(),"");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == botonbuscarjugador.getId()) {
            String Info = jugadornombre.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);

            String RutaAlServicioWEB = "http://192.168.1.145:8080/TPFinalAndresSoria/JugadorWS?TipoProceso=4" + "&JugadorNombreABuscar=" + Info;
            // La Dirección a donde vamos a realizar el Request
            StringRequest stringRequest = new StringRequest(Request.Method.GET, RutaAlServicioWEB,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            Gson Conversor = new Gson();

                            Type listType = new TypeToken<List<Jugador>>() {
                            }.getType();

                            EntornoDeDatos.ListaJugador.clear();
                            EntornoDeDatos.ListaJugador = Conversor.fromJson(response, listType);

                            MyJugadorAdapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            // Agregamos el StringRequest a la Cola de solicitudes //
            queue.add(stringRequest);
        }
        if (v.getId() == botonagregarjugador.getId()) {
            Intent B = new Intent(this, JugadorABM.class);
            B.putExtra("PK", Long.valueOf(0));
            B.putExtra("Posicion", 0);
            startActivityForResult(B, 1);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent IntentoAgregar = new Intent(this, JugadorABM.class);
        IntentoAgregar.putExtra("PK", l);
        IntentoAgregar.putExtra("POSICION", i);

        startActivityForResult(IntentoAgregar, 2);
    }

}
