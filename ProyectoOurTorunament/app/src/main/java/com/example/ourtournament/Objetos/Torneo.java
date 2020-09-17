package com.example.ourtournament.Objetos;

public class Torneo
{
    public int IDTorneo;
    public String NombreTorneo;
    public String ContraseniaDeAdministrador;
    public String LinkParaUnirse;

    public Torneo(int idtorneo, String nombreTorneo, String contraseniaDeAdministrador, String linkParaUnirse)
    {
        IDTorneo = idtorneo;
        NombreTorneo= nombreTorneo;
        ContraseniaDeAdministrador = contraseniaDeAdministrador;
        LinkParaUnirse = linkParaUnirse;
    }

    public Torneo()
    {

    }

    public int getIDTorneo() {
        return IDTorneo;
    }

    public void setIDTorneo(int IDTorneo) {
        this.IDTorneo = IDTorneo;
    }

    public String getNombreTorneo() {
        return NombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        NombreTorneo = nombreTorneo;
    }

    public String getContraseniaDeAdministrador() {
        return ContraseniaDeAdministrador;
    }

    public void setContraseniaDeAdministrador(String contraseniaDeAdministrador) {
        ContraseniaDeAdministrador = contraseniaDeAdministrador;
    }

    public String getLinkParaUnirse() {
        return LinkParaUnirse;
    }

    public void setLinkParaUnirse(String linkParaUnirse) {
        LinkParaUnirse = linkParaUnirse;
    }


}