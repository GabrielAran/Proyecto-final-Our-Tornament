﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OurTournamentAPI.Controllers
{
    public class PartidosController : ApiController
    {
        [HttpGet]
        [Route("api/GetJornadas/Torneo/{IDTorneo}")]
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
                return Ok();
            }

        }

        [HttpGet]
        [Route("api/GetPartidos/Jornada/{IDJornada}/Torneo/{IDTorneo}")]
        public IHttpActionResult ObtenerPartidosPorJornadas(int IDJornada, int IDTorneo)
        {
            List<Models.Partido> Lista = new List<Models.Partido>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TraerPartidosPorJornada(IDJornada, IDTorneo);
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