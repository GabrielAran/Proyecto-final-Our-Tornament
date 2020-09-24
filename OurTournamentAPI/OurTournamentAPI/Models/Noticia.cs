using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Noticia
    {
        private int idnoticia;
        private int idtorneo;
        private String titulo;
        private String descripcion;
        private Boolean destacada;
        private String foto;
        private int fecha;

        public int Idnoticia { get => idnoticia; set => idnoticia = value; }
        public int Idtorneo { get => idtorneo; set => idtorneo = value; }
        public string Titulo { get => titulo; set => titulo = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
        public bool Destacada { get => destacada; set => destacada = value; }
        public string Foto { get => foto; set => foto = value; }
        public int Fecha { get => fecha; set => fecha = value; }

        public Noticia(int idNoticia, int Idtorneo, String titulo, String descripcion, Boolean destacada, String foto, int fecha)
        {
            idnoticia = idNoticia;
            idtorneo = Idtorneo;
            titulo = titulo;
            descripcion = descripcion;
            destacada = destacada;
            foto = foto;
            fecha = fecha;
        }
        public Noticia()
        {
        }
    }
}