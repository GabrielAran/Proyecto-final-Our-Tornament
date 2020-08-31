using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
namespace OurTournamentAPI.Controllers
{
    public class UsuarioController : ApiController
    {
        [System.Web.Http.Route("api/InsertTorneosSeguidos/Usuario/{IDUsuario}/Torneo/{IDTorneo}/Equipo/{IDEquipo}")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarTorneoSeguidoPorUsuario(int IDUsuario,int IDTorneo,int IDEquipo)
        {
            QQSM Conexion = new QQSM();
            Conexion.InsertarTorneoSeguidoPorUsuario(IDUsuario,IDTorneo,IDEquipo);
            return Ok();
        }

        [HttpGet]
        [Route("api/GetGoleadores/Torneo/{IDTorneo}")]
        public IHttpActionResult TraerListaGoleadores(int IDTorneo)
        {
            List<Models.Goleadores> Lista = new List<Models.Goleadores>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerListaGoleadores(IDTorneo);
            if (Lista != null)
            {
                return Ok(Lista);
            }
            else
            {
                return NotFound();
            }
        }

    }
}
