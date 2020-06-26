using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OurTournamentAPI.Models
{
    public class Torneo
    {
  


        private int _IDTorneo;
        private String _nombreTorneo;
        private String _contraseniaDeAdministrador;
        private String _linkParaUnirse;
        private int _TipoDeTorneo;

        public int IDTorneo { get => this._IDTorneo; set => this._IDTorneo = value; }
        public String NombreTorneo { get => _nombreTorneo; set => _nombreTorneo = value; }
        public String ContraseniaDeAdministrador { get => _contraseniaDeAdministrador; set => _contraseniaDeAdministrador = value; }
        public String LinkParaUnirse { get => _linkParaUnirse; set => _linkParaUnirse = value; }
        public int TipoDeTorneo { get => _TipoDeTorneo; set => _TipoDeTorneo = value; }

        public Torneo(int idtorneo, string nombreTorneo, string contraseniaDeAdministrador, string linkParaUnirse,int tipodetorneo)
        {
            _IDTorneo = idtorneo;
            _nombreTorneo= nombreTorneo;
            _contraseniaDeAdministrador = contraseniaDeAdministrador;
           _linkParaUnirse = linkParaUnirse;
            _TipoDeTorneo = tipodetorneo;
        }

        public Torneo()
        {

        }



    }
}