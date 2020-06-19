using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Torneo
    {
        private int _IDTorneo;
        private string _nombreTorneo;
        private string _contraseniaDeAdministrador;
        private string _linkParaUnirse;

        public int IDTorneo { get => this._IDTorneo; set => this._IDTorneo = value; }
        public String _NombreTorneo { get => _nombreTorneo; set => _nombreTorneo = value; }
        private String _ContraseniaDeAdministrador { get => _contraseniaDeAdministrador; set => _contraseniaDeAdministrador = value; }
        private String _LinkParaUnirse { get => _linkParaUnirse; set => _linkParaUnirse = value; }


        public Torneo(int idtorneo, string nombreTorneo, string contraseniaDeAdministrador, string linkParaUnirse)
        {
            _IDTorneo = idtorneo;
            _nombreTorneo= nombreTorneo;
            _contraseniaDeAdministrador = contraseniaDeAdministrador;
           _linkParaUnirse = linkParaUnirse;
        }

        public Torneo()
        {

        }



    }
}