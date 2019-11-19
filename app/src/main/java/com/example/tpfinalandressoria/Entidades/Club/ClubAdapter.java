package com.example.tpfinalandressoria.Entidades.Club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tpfinalandressoria.Entidades.EntornoDeDatos;
import com.example.tpfinalandressoria.R;

public class ClubAdapter extends BaseAdapter

{
    Context context;

    public ClubAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return(EntornoDeDatos.ListaClub.size());
    }

    @Override
    public Object getItem(int i)
    {
        return(EntornoDeDatos.ListaClub.get(i));
    }

    @Override
    public long getItemId(int i)
    {
        return (EntornoDeDatos.ListaClub.get(i).getClubpk());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View Vista;

        LayoutInflater Inflador = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        Vista = Inflador.inflate(R.layout.clubitem,null);

        TextView CategoriaItem_TextView = Vista.findViewById(R.id.clubitem_textview);

        CategoriaItem_TextView.setText(EntornoDeDatos.ListaClub.get(i).toString());

        return Vista;
    }
}
