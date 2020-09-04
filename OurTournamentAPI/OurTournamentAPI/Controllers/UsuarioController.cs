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
        [System.Web.Http.Route("api/InsertTorneosSeguidos")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarTorneoSeguidoPorUsuario(List<int> LISTA)
        {
            QQSM Conexion = new QQSM();
            Conexion.InsertarTorneoSeguidoPorUsuario(LISTA); //IDUsuario, IDTorneo,IDEquipo
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

        /*
        [HttpGet]
        [Route("api/GetUsuario/Usuario/{IDUsuario}")]
        public IHttpActionResult ObtenerGolesXPartido(int IDUsuario)
        {
            List<Models.GolesXUsuario> Lista = new List<Models.GolesXUsuario>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerUsuariosPorID(IDUsuario);
            if (Lista != null)
            {
                return Ok(Lista);
            }
            else
            {
                return NotFound();
            }
        }
        */

    }
}
