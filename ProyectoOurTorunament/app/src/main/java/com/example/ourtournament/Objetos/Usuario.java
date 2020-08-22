package com.example.ourtournament.Objetos;

import java.util.Date;

public class Usuario {

    public int IdUsuario;
    public String NombreUsuario;
    public String Contrasenia;
    public String FechaDeNacimiento;
    public String Email;
    public int GolesEnTorneo;
    public int IDEquipo;


    public Usuario(int idusuario, String nombreusuario, String contrasenia, String fechadenacimiento, String email, int golesentorneo, int idequipo)
    {
        IdUsuario = idusuario;
        NombreUsuario = nombreusuario;
        Contrasenia = contrasenia;
        FechaDeNacimiento = fechadenacimiento;
        Email = email;
        GolesEnTorneo = golesentorneo;
        IDEquipo = idequipo;
    }

    public Usuario()
    {

    }
}
