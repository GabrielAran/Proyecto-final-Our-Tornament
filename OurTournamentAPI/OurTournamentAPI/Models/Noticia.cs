using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Noticia
    {
        private int idnoticia;
        private String torneo;
        private String titulo;
        private String descripcion;
        private Boolean destacada;
        private String foto;
        private int fecha;

        public int Idnoticia { get => idnoticia; set => idnoticia = value; }
        public string Torneo { get => torneo; set => torneo = value; }
        public string Titulo { get => titulo; set => titulo = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
        public bool Destacada { get => destacada; set => destacada = value; }
        public string Foto { get => foto; set => foto = value; }
        public int Fecha { get => fecha; set => fecha = value; }

        public Noticia(int idNoticia, String torneo, String titulo, String descripcion, Boolean destacada, String foto, int fecha)
        {
            idnoticia = idNoticia;
            torneo = torneo;
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