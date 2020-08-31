package com.example.ourtournament.Objetos;

public class GolesXUsuario {

    int IdPartido;
    int IdUsuario;
    String NombreUsuario;
    int Cantgoles;
    String Nombreequipo;

    public GolesXUsuario(int iDPartido, int iDUsuario, String nombre, int cantGoles,String nombreequipo)
    {
        IdPartido = iDPartido;
        IdUsuario = iDUsuario;
        NombreUsuario = nombre;
        Cantgoles = cantGoles;
        Nombreequipo = nombreequipo;
    }
    public GolesXUsuario()
    {
    }
}
