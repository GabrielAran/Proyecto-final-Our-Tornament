package com.example.ourtournament.Objetos;

import java.util.Date;

public class Partido
{

    public int _IDPartido;
    public Date _FechaDeEncuentro;
    public String _NombreELocal;
    public String _NombreEVisitante;
    public int _GolesLocal;
    public int _GolesVisitante;


    public Partido(int IDPartido, Date FechaDeEncuentro, String NombreLocal, String NombreVisitante, int GolesLocal, int GolesVisitante)
    {
        _IDPartido = IDPartido;
        _NombreELocal = NombreLocal;
        _NombreEVisitante = NombreVisitante;
        _GolesLocal = GolesLocal;
        _GolesVisitante = GolesVisitante;
        _FechaDeEncuentro = FechaDeEncuentro;
    }

    public Partido()
    {

    }



}