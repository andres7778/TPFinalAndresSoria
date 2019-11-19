package com.example.tpfinalandressoria.Entidades.Jugadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tpfinalandressoria.Entidades.EntornoDeDatos;
import com.example.tpfinalandressoria.R;


public class JugadorAdapter extends BaseAdapter

{
    Context context;

    public JugadorAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return(EntornoDeDatos.ListaJugador.size());
    }

    @Override
    public Object getItem(int i)
    {
        return(EntornoDeDatos.ListaJugador.get(i));
    }

    @Override
    public long getItemId(int i)
    {
        return (EntornoDeDatos.ListaJugador.get(i).getJugadorpk());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View Vista;

        LayoutInflater Inflador = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        Vista = Inflador.inflate(R.layout.jugadoritem,null);

        TextView CategoriaItem_TextView = Vista.findViewById(R.id.jugadoritem_textview);

        CategoriaItem_TextView.setText(EntornoDeDatos.ListaJugador.get(i).toString());

        return Vista;
    }
}





