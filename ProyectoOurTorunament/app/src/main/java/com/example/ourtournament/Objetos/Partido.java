package com.example.ourtournament.Objetos;

import java.util.Date;

public class Partido
{

    public int _IDPartido;
    public Date _FechaDeEncuentro;
    public int _IDEquipoLocal;
    public int _IDEquipoVisitante;
    public int _GolesLocal;
    public int _GolesVisitante;
    public int _IDTorneo;
    public int _Jornada;


    public Partido(int IDPartido, Date FechaDeEncuentro, int IDEquipoLocal, int IDEquipoVisitante, int GolesLocal, int GolesVisitante, int IDTorneo, int Jornada)
    {
        _IDPartido = IDPartido;
        _IDEquipoLocal = IDEquipoLocal;
        _IDEquipoVisitante = IDEquipoVisitante;
        _GolesLocal = GolesLocal;
        _GolesVisitante = GolesVisitante;
        _FechaDeEncuentro = FechaDeEncuentro;
        _Jornada = Jornada;
        _IDTorneo = IDTorneo;
    }

    public Partido()
    {

    }



}