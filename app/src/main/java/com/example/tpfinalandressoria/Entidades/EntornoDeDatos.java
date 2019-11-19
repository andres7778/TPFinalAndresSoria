package com.example.tpfinalandressoria.Entidades;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tpfinalandressoria.Entidades.Club.Club;
import com.example.tpfinalandressoria.Entidades.Jugadores.Jugador;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntornoDeDatos
{
    public static final String LinkServidorWeb = "http://192.168.1.145:8080/TPFinalAndresSoria/";




    public static List<Club> ListaClub = new ArrayList<Club>();

    public static List<Jugador> ListaJugador = new ArrayList<Jugador>();

    public static String AgregarParametrosALink(String linkServidorWebParametro, String NombreParametro, String ValorParametro)
    {
        String RutaFinal = linkServidorWebParametro + NombreParametro + "=" + ValorParametro;

        return RutaFinal;
    }



    public static void BuscarClub(Context context, String ClubNombre)
    {

        RequestQueue queue = Volley.newRequestQueue(context);

        String RutaAlServicioWEB = EntornoDeDatos.LinkServidorWeb + "/ClubWS";

        RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"?TipoProceso","4");
        RutaAlServicioWEB = EntornoDeDatos.AgregarParametrosALink(RutaAlServicioWEB,"&DistribuidoraNombreABuscar",ClubNombre);


        // La Dirección a donde vamos a realizar el Request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RutaAlServicioWEB,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)

                    {
                        ConvertirJSONAListaClub(response);

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                //Toast.makeText(context,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        // Agregamos el StringRequest a la Cola de solicitudes //
        queue.add(stringRequest);
    }


    public static void ConvertirJSONAListaClub(String DatosEnFormatoJSON) {
        Gson Conversor = new Gson();

        Type listType = new TypeToken<List<Club>>() {
        }.getType();
        EntornoDeDatos.ListaClub.clear();
        EntornoDeDatos.ListaClub = Conversor.fromJson(DatosEnFormatoJSON, listType);
    }

}
