using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OurTournamentAPI.Controllers
{
    public class TorneosController : ApiController
    {
        [HttpGet]
        [Route("api/GetTorneoPorID/Torneo/{id}")]
        public IHttpActionResult ObtenerTorneoPorID(int id)
        {
            Models.Torneo T = new Models.Torneo();
            QQSM Conexion = new QQSM();
            T = Conexion.TraerTorneoPorID(id);
            if(T!= null)
            {
                return Ok(T);
            }

            else
            {
                return NotFound();
            }
            
        }

        [HttpGet]
        [Route("api/GetTorneosPorNombre/Nombre/{Nombre}")]
        public IHttpActionResult ObtenerTorneosPorNombre(String Nombre)
        {
            List<Models.Torneo> T = new List<Models.Torneo>();
            QQSM Conexion = new QQSM();
            T = Conexion.TraerTorneosPorNombre(Nombre);
            if (T != null)
            {
                return Ok(T);
            }
            else
            {
                return NotFound();
            }

        }
    }
}
