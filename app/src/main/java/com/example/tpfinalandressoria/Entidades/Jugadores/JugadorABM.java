package com.example.tpfinalandressoria.Entidades.Jugadores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpfinalandressoria.Entidades.Club.ClubAdapter;
import com.example.tpfinalandressoria.Entidades.EntornoDeDatos;
import com.example.tpfinalandressoria.R;


public class JugadorABM extends AppCompatActivity implements View.OnClickListener
{

    EditText jugadornombre ;
    EditText jugadorapellido;
    EditText jugadoredad;
    EditText jugadorposicion;
    EditText jugadorsexo;
    EditText jugadornacionalidad;

    TextView TexTClub;

    Spinner SpinnerClub;
    ClubAdapter MyAdapter;

    Button jugadorBotonEditar;
    Button jugadorABMBotonEliminar;
    Button AgregarJugador;

    Long  PK =Long.valueOf(0);
    int  Posicion = 0;

    Jugador JugadorPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadorabm);

        jugadornombre = findViewById(R.id.jugadornombre);
        jugadorapellido = findViewById(R.id.jugadorapellido);
        jugadoredad = findViewById(R.id.jugadoredad);
        jugadorposicion = findViewById(R.id.jugadorposicion);
        jugadorsexo = findViewById(R.id.jugadorsexo);
        jugadornacionalidad = findViewById(R.id.jugadornacionalidad);
        AgregarJugador = findViewById(R.id.AgregarJugador);
        jugadorBotonEditar = findViewById(R.id.jugadorBotonEditar);
        jugadorABMBotonEliminar = findViewById(R.id. jugadorABMBotonEliminar);

        TexTClub = findViewById(R.id.TexTClub);

        jugadorBotonEditar.setOnClickListener(this);
        jugadorABMBotonEliminar.setOnClickListener(this);

        AgregarJugador.setOnClickListener(this);

        jugadorBotonEditar = findViewById(R.id.jugadorBotonEditar);
        jugadorABMBotonEliminar = findViewById(R.id.jugadorABMBotonEliminar);


        SpinnerClub = findViewById(R.id.SpinnerClub);
        MyAdapter = new ClubAdapter(getApplicationContext());
        SpinnerClub.setAdapter(MyAdapter);


        PK = getIntent().getLongExtra("PK",Long.valueOf(0));
        Posicion = getIntent().getIntExtra("Posicion",0);
        Toast.makeText(getApplicationContext(),"Los Son " + PK + " Y " + Posicion, Toast.LENGTH_LONG).show();


        if (PK > 0)
        {
            JugadorPrincipal = EntornoDeDatos.ListaJugador.get(Posicion);

            jugadornombre.setText(JugadorPrincipal.getJugadornombre());
            jugadorapellido.setText(JugadorPrincipal.getJugadorapellido());
            jugadoredad.setText(JugadorPrincipal.getJugadoredad());
            jugadorposicion.setText(JugadorPrincipal.getJugadorposicion());
            jugadorsexo.setText(JugadorPrincipal.getJugadorsexo());
            jugadornacionalidad.setText(JugadorPrincipal.getJugadornacionalidad());


            int PosicionDeClub = 0;
            for (int i = 0; i  < EntornoDeDatos.ListaClub.size();i++)
            {
                if (EntornoDeDatos.ListaClub.get(i).getClubpk() == JugadorPrincipal.getClubpk())
                {
                    PosicionDeClub = i;
                }
            }

            SpinnerClub.setSelection(PosicionDeClub,true);
            AgregarJugador.setEnabled(false);
        }
        else
        {
            JugadorPrincipal = new Jugador();
            jugadorBotonEditar.setEnabled(false);
            jugadorABMBotonEliminar.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == AgregarJugador.getId())
        {
            Long PK = Long.valueOf(0);
            String Nombre = jugadornombre.getText().toString();
            String Apellido = jugadorapellido.getText().toString();
            String Edad = jugadoredad.getText().toString();
            String Posicion = jugadorposicion.getText().toString();
            String Sexo = jugadorsexo.getText().toString();
            String Nacionalidad = jugadornacionalidad.getText().toString();
            Long idclub = SpinnerClub.getSelectedItemId();

            JugadorPrincipal = new Jugador(PK,Nombre,Apellido,Edad,Posicion,Sexo,Nacionalidad,idclub);
        }

    }
}
