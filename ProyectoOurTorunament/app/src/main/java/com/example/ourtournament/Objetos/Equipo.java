package com.example.ourtournament.Objetos;

public class Equipo
{
    public int _IDEquipo;
    public String _Nombre;
    public int _PartidosJugados;
    public int _Puntos;
    public int _GolesAFavor;
    public int _GolesEnContra;
    public int _IDTorneo;

    public Equipo(int IDEquipo, String Nombre, int PartidosJugados, int Puntos, int GolesAFavor, int GolesEnContra, int IDTorneo)
    {
        _IDEquipo = IDEquipo;
        _Nombre = Nombre;
        _PartidosJugados = PartidosJugados;
        _Puntos = Puntos;
        _GolesAFavor = GolesAFavor;
        _GolesEnContra = GolesEnContra;
        _IDTorneo = IDTorneo;
    }
    public Equipo()
    {
    }
}