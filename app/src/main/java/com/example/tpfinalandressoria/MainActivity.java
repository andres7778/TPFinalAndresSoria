package com.example.tpfinalandressoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tpfinalandressoria.Entidades.Club.PantallaPrincipalClub;
import com.example.tpfinalandressoria.Entidades.EntornoDeDatos;
import com.example.tpfinalandressoria.Entidades.Jugadores.PantallaPrincipalJugador;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button jugadoresboton;
    Button clubesboton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);


        jugadoresboton = findViewById(R.id.jugadoresboton);
        clubesboton = findViewById(R.id.clubesboton);
        jugadoresboton.setOnClickListener(this);
        clubesboton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == jugadoresboton.getId())
        {
            Intent A = new Intent(this,PantallaPrincipalJugador.class);
            startActivity(A);


            //Toast.makeText(getApplicationContext(), "HOLA", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == clubesboton.getId())
        {
            Intent iraclubes = new  Intent(this, PantallaPrincipalClub.class);
            startActivity(iraclubes);

        }

    }
}
