﻿using System;
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
            bool Devolver;
            QQSM Conexion = new QQSM();
            Devolver = Conexion.InsertarTorneoSeguidoPorUsuario(LISTA); //IDUsuario, IDTorneo,IDEquipo
            return Ok(Devolver);
        }

        [HttpGet]
        [Route("api/GetTorneosSeguidosPorUsuario/Usuario/{IDUsuario}")]
        public IHttpActionResult ObtenerTorneosSeguidosPorUsuario(int IDUsuario)
        {
            List<Models.Torneo> Lista = new List<Models.Torneo>();
            QQSM Conexion = new QQSM();
            Lista = Conexion.TorneosSeguidosPorUsuario(IDUsuario);
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

        
        [HttpGet]
        [Route("api/GetUsuario/Usuario/{IDUsuario}")]
        public IHttpActionResult ObtenerUsuario(int IDUsuario)
        {
            QQSM Conexion = new QQSM();
            Models.Usuario U = Conexion.TraerUsuariosPorID(IDUsuario);
            if (U != null)
            {
                return Ok(U);
            }
            else
            {
                return NotFound();
            }
        }

    }
}
