using OurTournamentAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OurTournamentAPI.Controllers
{
    public class NoticiaController : ApiController
    {

        [HttpGet]
        [Route("api/GetNoticiasPorTorneo/Torneo/{IDTorneo}")]
        public IHttpActionResult TraerNoticiasPorTorneo(int IDTorneo)
        {
            List<Models.Noticia> Lista = new List<Models.Noticia>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerNoticiasPorTorneo(IDTorneo);
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
