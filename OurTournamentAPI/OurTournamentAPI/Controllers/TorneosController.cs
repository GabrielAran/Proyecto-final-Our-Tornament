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

        [HttpGet]
        [Route("api/OBTPartidos/Jornada={IDJornada}/Torneo/{IDTorneo}")]
        public IHttpActionResult ObtenerPartidosPorJornadas(int IDJornada, int IDTorneo)
        {
            List<Models.Partido> Lista = new List<Models.Partido>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerPartidosPorJornada(IDJornada,IDTorneo);
            if (Lista != null)
            {
                return Ok(Lista);
            }
            else
            {
                return NotFound();
            }

        }

        [HttpGet]
        [Route("api/OBTJornadas/Torneo/{IDTorneo}")]
        public IHttpActionResult TraerJornadasPorTorneo(int IDTorneo)
        {
            List<int> Lista = new List<int>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerJornadasPorTorneo(IDTorneo);
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
