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
        [Route("api/Torneos/{id}")]
        public IHttpActionResult ObtenerTorneoPorID(int id)
        {
            Models.Torneo T = new Models.Torneo();
            QQSM Conexion = new QQSM();
            T = Conexion.TraerTorneo(id);
            if(T!= null)
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
