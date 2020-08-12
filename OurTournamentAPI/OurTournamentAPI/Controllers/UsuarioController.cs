using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Mvc;

namespace OurTournamentAPI.Controllers
{
    public class UsuarioController : ApiController
    {
        [System.Web.Http.Route("api/InsertTorneosSeguidos/Usuario/{IDUsuario}/Torneo/{IdTorneo}/Equipo/{IDEquipo}")]
        [System.Web.Http.HttpPost]
        [System.Web.Http.AcceptVerbs("POST")]
        public System.Web.Http.HttpPostAttribute InsertarTorneoSeguidoPorUsuario(int IDUsuario,int IDTorneo,int IDEquipo)
        {
            QQSM Conexion = new QQSM();
            Conexion.InsertarTorneoSeguidoPorUsuario(IDUsuario,IDTorneo,IDEquipo);
        }
    }
}
