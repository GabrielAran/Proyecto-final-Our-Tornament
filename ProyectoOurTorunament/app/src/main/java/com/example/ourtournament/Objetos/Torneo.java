package com.example.ourtournament.Objetos;

public class Torneo
{
    public int _IDTorneo;
    public String _nombreTorneo;
    public String _contraseniaDeAdministrador;
    public String _linkParaUnirse;
    public int _TipoDeTorneo;

    public Torneo(int idtorneo, String nombreTorneo, String contraseniaDeAdministrador, String linkParaUnirse,int tipodetorneo)
    {
        _IDTorneo = idtorneo;
        _nombreTorneo= nombreTorneo;
        _contraseniaDeAdministrador = contraseniaDeAdministrador;
        _linkParaUnirse = linkParaUnirse;
        _TipoDeTorneo = tipodetorneo;
    }

    public Torneo()
    {

    }



}