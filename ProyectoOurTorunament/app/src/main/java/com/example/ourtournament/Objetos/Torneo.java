package com.example.ourtournament.Objetos;

public class Torneo
{
    public int _IDTorneo;
    public String _nombreTorneo;
    public String _contraseniaDeAdministrador;
    public String _linkParaUnirse;


    public Torneo(int idtorneo, String nombreTorneo, String contraseniaDeAdministrador, String linkParaUnirse)
    {
        _IDTorneo = idtorneo;
        _nombreTorneo= nombreTorneo;
        _contraseniaDeAdministrador = contraseniaDeAdministrador;
        _linkParaUnirse = linkParaUnirse;
    }

    public Torneo()
    {

    }



}