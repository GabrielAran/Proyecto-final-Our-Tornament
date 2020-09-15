using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Usuario
    {
        private int _IdUsuario;
        private String _NombreUsuario;
        private String _Contrasenia;
        private DateTime _FechaDeNacimiento;
        private String _Email;
        private int _GolesEnTorneo;

        public int IdUsuario { get => _IdUsuario; set => _IdUsuario = value; }
        public String NombreUsuario { get => _NombreUsuario; set => _NombreUsuario = value; }
        public String Contrasenia { get => _Contrasenia; set => _Contrasenia = value; }
        public DateTime FechaDeNacimiento { get => _FechaDeNacimiento; set => _FechaDeNacimiento = value; }
        public String Email { get => _Email; set => _Email = value; }
        public int GolesEnTorneo { get => _GolesEnTorneo; set => _GolesEnTorneo = value; }

        public Usuario(int IdUsuario, String NombreUsuario, String Contrasenia, DateTime FechaDeNacimiento, String Email, int GolesEnTorneo)
        {
            _IdUsuario = IdUsuario;
            _NombreUsuario = NombreUsuario;
            _Contrasenia = Contrasenia;
            _FechaDeNacimiento = FechaDeNacimiento;
            _Email = Email;
            _GolesEnTorneo = GolesEnTorneo;
        }

        public Usuario()
        {
        }
    }
}