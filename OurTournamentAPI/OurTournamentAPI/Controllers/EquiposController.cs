using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OurTournamentAPI.Controllers
{
    public class EquiposController : ApiController
    {
        [HttpGet]
        [Route("api/GetPosiciones/Torneo/{IDTorneo}")]
        public IHttpActionResult TraerListaDePosiciones(int IDTorneo)
        {
            List<Models.Equipo> Lista = new List<Models.Equipo>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerListaDePosiciones(IDTorneo);
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
        [Route("api/GetEquipoPorID/Torneo/{IDTorneo}/Equipo/{IDEquipo}")]
        public IHttpActionResult TraerEquipo(int IDTorneo,int IDEquipo)
        {
            List<Models.Equipo> Lista = new List<Models.Equipo>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerListaDePosiciones(IDTorneo);
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
