package com.example.ourtournament.Objetos;

import java.util.Date;

public class Usuario {

    public int IdUsuario;
    public String NombreUsuario;
    public String Contrasenia;
    public Date FechaDeNacimiento;
    public String Email;
    public int GolesEnTorneo;


    public Usuario(int idusuario, String nombreusuario, String contrasenia, Date fechadenacimiento, String email, int golesentorneo)
    {
        IdUsuario = idusuario;
        NombreUsuario = nombreusuario;
        Contrasenia = contrasenia;
        FechaDeNacimiento = fechadenacimiento;
        Email = email;
        GolesEnTorneo = golesentorneo;
    }

    public Usuario()
    {

    }
}
