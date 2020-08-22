package com.example.ourtournament.Objetos;

import android.widget.ImageView;

import java.sql.Date;

public class Noticia {
    public int Idnoticia;
    public String Torneo;
    public String Titulo;
    public String Descripcion;
    public Boolean Destacada;
    public ImageView Foto;
    public Date Fecha;
    public Noticia(int idNoticia, String torneo, String titulo, String descripcion, Boolean destacada, ImageView foto, Date fecha)
    {
        Idnoticia = idNoticia;
        Torneo = torneo;
        Titulo = titulo;
        Descripcion = descripcion;
        Destacada = destacada;
        Foto = foto;
        Fecha = fecha;
    }
    public Noticia() {
    }

}
