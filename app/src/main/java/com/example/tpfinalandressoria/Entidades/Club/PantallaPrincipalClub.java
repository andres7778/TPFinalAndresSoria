package com.example.tpfinalandressoria.Entidades.Club;

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

public class PantallaPrincipalClub extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText editclub;
    EditText clubpais;
    EditText clubcategoria;
    EditText clubdeporte;

    Button botonbuscarclub;
    Button botonagregar;

    ListView listclub;




    ClubAdapter MyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principalclub);

        editclub = findViewById(R.id.editclub);
        clubpais = findViewById(R.id.clubpais);
        clubcategoria = findViewById(R.id.clubcategoria);
        clubdeporte = findViewById(R.id.clubdeporte);

        botonbuscarclub = findViewById(R.id.botonbuscarclub);
        botonagregar = findViewById(R.id.botonagregar);

        listclub = findViewById(R.id.listclub);
        MyAdapter = new ClubAdapter(getApplicationContext());
        listclub.setAdapter(MyAdapter);




        listclub.setOnItemClickListener(this);
        botonbuscarclub.setOnClickListener(this);
        botonagregar.setOnClickListener(this);

    }






    @Override
    public void onClick(View v)
    {
        if (botonbuscarclub.getId() == v.getId())
        {
            String Info = editclub.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);

            String RutaAlServicioWEB =  "http://192.168.1.145:8080/TPFinalAndresSoria/ClubWS?TipoProceso=4" + "&ClubNombreABuscar=" + Info;
            // La Dirección a donde vamos a realizar el Request
            StringRequest stringRequest = new StringRequest(Request.Method.GET, RutaAlServicioWEB,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            Gson Conversor = new Gson();

                            Type listType = new TypeToken<List<Club>>(){}.getType();

                            EntornoDeDatos.ListaClub.clear();
                            EntornoDeDatos.ListaClub = Conversor.fromJson(response, listType);

                            MyAdapter.notifyDataSetChanged();
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

        if (botonagregar.getId()==v.getId())
        {
            String Nombre = editclub.getText().toString();
            String NombrePais = clubpais.getText().toString();
            String NombreCategoria = clubcategoria.getText().toString();
            String NombreDeporte = clubdeporte.getText().toString();

            Club clubinicial = new Club(Long.valueOf(0),Nombre,NombrePais,NombreCategoria,NombreDeporte);
            PersistirDatosEnLaNube(clubinicial,"1");

        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l)
    {
        Intent Club = new Intent(this, ClubABM.class);
        Club.putExtra("PK",l);
        Club.putExtra("Posicion",i);
        startActivity(Club);
    }

    private void PersistirDatosEnLaNube(Club P,String TipoProceso)
    {

        Gson Conversor = new Gson();

        String SalidaDeObjetoEnFormatoJson = Conversor.toJson(P);

        Toast.makeText(getApplicationContext(),SalidaDeObjetoEnFormatoJson,Toast.LENGTH_LONG).show();

        RequestQueue queue = Volley.newRequestQueue(this);

        String RutaAlServicioWEB = EntornoDeDatos.LinkServidorWeb + "/ClubWS";

        RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"?TipoProceso",TipoProceso);

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
