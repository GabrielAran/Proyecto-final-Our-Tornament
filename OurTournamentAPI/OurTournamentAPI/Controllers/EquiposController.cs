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
        [Route("api/GetEquipoPorID/Equipo/{IDEquipo}")]
        public IHttpActionResult TraerEquipo(int IDEquipo)
        {
            Models.Equipo UnEquipo = new Models.Equipo();
            QQSM Conexion = new QQSM();
            UnEquipo = Conexion.TraerEquipoPorIDEquipo(IDEquipo);
            if (UnEquipo != null)
            {
                return Ok(UnEquipo);
            }
            else
            {
                return NotFound();
            }

        }

        [System.Web.Http.Route("api/InsertEquipos")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarEquipos(List<int> ListaEquipos)
        {
            bool Devolver;
            QQSM Conexion = new QQSM();
            Devolver = Conexion.InsertarEquipos(ListaEquipos); //IDUsuario, IDTorneo,IDEquipo
            return Ok(Devolver);
        }
    }
}
