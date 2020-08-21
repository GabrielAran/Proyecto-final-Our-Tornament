package com.example.ourtournament.Objetos;

import java.util.Date;

public class Usuarios {

    public int _IdUsuario;
    public String _NombreUsuario;
    public String _Contrasenia;
    public String _FechaDeNacimiento;
    public String _Email;
    public int _GolesEnTorneo;
    public int _IDEquipo;


    public Usuarios(int IdUsuario, String NombreUsuario, String Contrasenia, String FechaDeNacimiento, String Email, int GolesEnTorneo,int IDEquipo)
    {
        _IdUsuario = IdUsuario;
        _NombreUsuario = NombreUsuario;
        _Contrasenia = Contrasenia;
        _FechaDeNacimiento = FechaDeNacimiento;
        _Email = Email;
        _GolesEnTorneo = GolesEnTorneo;
        _IDEquipo = IDEquipo;
    }

    public Usuarios()
    {

    }
}
