package com.example.tpfinalandressoria.Entidades.Jugadores;

public class Jugador
{
    Long jugadorpk;
    String jugadornombre;
    String jugadorapellido;
    String jugadoredad;
    String jugadorposicion;
    String jugadorsexo;
    String jugadornacionalidad;
    Long clubpk;


    public Jugador(Long jugadorpk, String jugadornombre, String jugadorapellido, String jugadoredad, String jugadorposicion, String jugadorsexo, String jugadornacionalidad,Long clubpk)
    {
        this.jugadorpk = jugadorpk;
        this.jugadornombre = jugadornombre;
        this.jugadorapellido = jugadorapellido;
        this.jugadoredad = jugadoredad;
        this.jugadorposicion = jugadorposicion;
        this.jugadorsexo = jugadorsexo;
        this.jugadornacionalidad = jugadornacionalidad;
        this.clubpk = clubpk;

    }

    public Jugador()
    {
        this.jugadorpk = Long.valueOf(0);
        this.jugadornombre = "";
        this.jugadorapellido= "";
        this.jugadoredad = "";
        this.jugadorposicion = "";
        this.jugadorsexo= "";
        this.jugadornacionalidad = "";
        this.clubpk = Long.valueOf(0);


    }

    public Long getJugadorpk()
    {
        return jugadorpk;
    }

    public void setJugadorpk(Long jugadorpk)
    {
        this.jugadorpk = jugadorpk;
    }

    public String getJugadornombre()
    {
        return jugadornombre;
    }

    public void setJugadornombre(String jugadornombre)
    {
        this.jugadornombre = jugadornombre;
    }

    public String getJugadorapellido()
    {
        return jugadorapellido;
    }

    public void setJugadorapellido(String jugadorapellido)
    {
        this.jugadorapellido = jugadorapellido;
    }

    public String getJugadoredad()
    {
        return jugadoredad;
    }

    public void setJugadoredad(String jugadoredad)
    {
        this.jugadoredad = jugadoredad;
    }

    public String getJugadorposicion()
    {
        return jugadorposicion;
    }

    public void setJugadorposicion(String jugadorposicion)
    {
        this.jugadorposicion = jugadorposicion;
    }

    public String getJugadorsexo()
    {
        return jugadorsexo;
    }

    public void setJugadorsexo(String jugadorsexo)
    {
        this.jugadorsexo = jugadorsexo;
    }

    public Long getClubpk()
    {
        return clubpk;
    }

    public void setClubpk(Long clubpk)
    {
        this.clubpk = clubpk;
    }

    public String getJugadornacionalidad()
    {
        return jugadornacionalidad;
    }

    public void setJugadornacionalidad(String jugadornacionalidad)
    {
        this.jugadornacionalidad = jugadornacionalidad;
    }

    @Override
    public String toString()
    {
        return "Jugador{" + "jugadorpk= " + jugadorpk + ", jugadornombre= " + jugadornombre + ", jugadorapellido= " + jugadorapellido + ", jugadoredad= " + jugadoredad + ", jugadorposicion= " + jugadorposicion + ", jugadorsexo= " + jugadorsexo + ", jugadornacionalidad= " + jugadornacionalidad + ", clubpk= " + clubpk + '}';
    }
}
