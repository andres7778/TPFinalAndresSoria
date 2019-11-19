package com.example.tpfinalandressoria.Entidades.Club;

public class Club
{
    Long clubpk;
    String clubnombre;
    String clubpais;
    String clubcategoria;
    String clubdeporte;

    public Club(Long clubpk, String clubnombre, String clubpais, String clubcategoria, String clubdeporte)
    {
        this.clubpk = clubpk;
        this.clubnombre = clubnombre;
        this.clubpais = clubpais;
        this.clubcategoria = clubcategoria;
        this.clubdeporte = clubdeporte;
    }

    public Club()
    {
        this.clubpk = Long.valueOf(0);
        this.clubnombre = "";
        this.clubpais = "";
        this.clubcategoria = "";
        this.clubdeporte = "";

    }

    public Long getClubpk()
    {
        return clubpk;
    }

    public void setClubpk(Long clubpk)
    {
        this.clubpk = clubpk;
    }

    public String getClubnombre()
    {
        return clubnombre;
    }

    public void setClubnombre(String clubnombre)
    {
        this.clubnombre = clubnombre;
    }

    public String getClubpais()
    {
        return clubpais;
    }

    public void setClubpais(String clubpais)
    {
        this.clubpais = clubpais;
    }

    public String getClubcategoria()
    {
        return clubcategoria;
    }

    public void setClubcategoria(String clubcategoria)
    {
        this.clubcategoria = clubcategoria;
    }

    public String getClubdeporte()
    {
        return clubdeporte;
    }

    public void setClubdeporte(String clubdeporte)
    {
        this.clubdeporte = clubdeporte;
    }

    @Override
    public String toString()
    {
        return "Club{" + "clubpk=" + clubpk + ", clubnombre=" + clubnombre + ", clubpais=" + clubpais + ", clubcategoria=" + clubcategoria + ", clubdeporte=" + clubdeporte + '}';
    }




}
