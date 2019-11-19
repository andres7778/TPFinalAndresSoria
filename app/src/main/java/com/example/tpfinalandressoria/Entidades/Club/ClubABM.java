package com.example.tpfinalandressoria.Entidades.Club;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ClubABM extends AppCompatActivity implements View.OnClickListener {

    EditText clubpk;
    EditText clubnombre;
    EditText clubpais;
    EditText clubcategoria;
    EditText clubdeporte;

    Button clubBotonEditar;
    Button clubABMBotonEliminar;

    Long  PK =Long.valueOf(0);
    int  Posicion = 0;

    Club ClubMuestra;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubabm);

        clubpk = findViewById(R.id.clubpk);
        clubnombre = findViewById(R.id.clubnombre);
        clubpais = findViewById(R.id.clubpais);
        clubcategoria = findViewById(R.id.clubcategoria);
        clubdeporte = findViewById(R.id.clubdeporte);

        clubBotonEditar = findViewById(R.id.clubBotonEditar);
        clubABMBotonEliminar = findViewById(R.id.clubABMBotonEliminar);

        clubBotonEditar.setOnClickListener(this);
        clubABMBotonEliminar.setOnClickListener(this);




        PK = getIntent().getLongExtra("PK",Long.valueOf(0));
        Posicion = getIntent().getIntExtra("Posicion",0);
        Toast.makeText(getApplicationContext(),"Los Son " + PK + " Y " + Posicion, Toast.LENGTH_LONG).show();

        if (PK>0)
        {
            ClubMuestra = EntornoDeDatos.ListaClub.get(Posicion);
            clubpk.setText(String.valueOf(PK));
            clubnombre.setText(ClubMuestra.getClubnombre());
            clubpais.setText(ClubMuestra.getClubpais());
            clubcategoria.setText(ClubMuestra.getClubcategoria());
            clubdeporte.setText(ClubMuestra.getClubdeporte());
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == clubABMBotonEliminar.getId())
        {
            String id = clubpk.getText().toString();
            String Nombre = clubnombre.getText().toString();
            String Pais = clubpais.getText().toString();
            String Categoria = clubcategoria.getText().toString();
            String Deporte = clubdeporte.getText().toString();

            Club ClubModificar= new Club(Long.valueOf(id),Nombre,Pais,Categoria,Deporte);

            Gson Conversor = new Gson();

            String SalidaDeObjetoEnFormatoJson = Conversor.toJson(ClubModificar);

            RequestQueue queue = Volley.newRequestQueue(this);

            String RutaAlServicioWEB = EntornoDeDatos.LinkServidorWeb + "/ClubWS";

            RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"?TipoProceso","3");

            RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"&ParametroJSON",SalidaDeObjetoEnFormatoJson);


            // La Dirección a donde vamos a realizar el Request
            StringRequest stringRequest = new StringRequest(Request.Method.GET, RutaAlServicioWEB,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);

        }

        if (v.getId()==clubBotonEditar.getId())
        {
            String Nombre = clubnombre.getText().toString();
            String Pais = clubpais.getText().toString();
            String Categoria = clubcategoria.getText().toString();
            String Deporte = clubdeporte.getText().toString();

            Club ClubModificar= new Club(Long.valueOf(PK),Nombre,Pais,Categoria,Deporte);

            Gson Conversor = new Gson();

            String SalidaDeObjetoEnFormatoJson = Conversor.toJson(ClubModificar);

            //Toast.makeText(getApplicationContext(),SalidaDeObjetoEnFormatoJson,Toast.LENGTH_LONG).show();

            RequestQueue queue = Volley.newRequestQueue(this);

            String RutaAlServicioWEB = EntornoDeDatos.LinkServidorWeb + "/ClubWS";

            RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"?TipoProceso","2");

            RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"&ParametroJSON",SalidaDeObjetoEnFormatoJson);


            // La Dirección a donde vamos a realizar el Request
            StringRequest stringRequest = new StringRequest(Request.Method.GET, RutaAlServicioWEB,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });

            // Agregamos el StringRequest a la Cola de solicitudes //
            queue.add(stringRequest);

        }

    }
}
