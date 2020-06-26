using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Noticia
    {
        private int IDNoticia;
        private String Torneo;
        private String Titulo;
        private String Descripcion;
        private Boolean Destacada;
        private String Foto;
        private DateTime Fecha;

        public int IDNoticia1 { get => IDNoticia; set => IDNoticia = value; }
        public string Torneo1 { get => Torneo; set => Torneo = value; }
        public string Titulo1 { get => Titulo; set => Titulo = value; }
        public string Descripcion1 { get => Descripcion; set => Descripcion = value; }
        public bool Destacada1 { get => Destacada; set => Destacada = value; }
        public string Foto1 { get => Foto; set => Foto = value; }
        public DateTime Fecha1 { get => Fecha; set => Fecha = value; }

        private Noticia(int idNoticia, String torneo, String titulo, String descripcion, Boolean destacada, String foto, DateTime fecha)
        {
            IDNoticia1 = idNoticia;
            Torneo1 = torneo;
            Titulo1 = titulo;
            Descripcion1 = descripcion;
            Destacada1 = destacada;
            Foto1 = foto;
            Fecha1 = fecha;
        }
        public Noticia()
        {
        }

    }
}