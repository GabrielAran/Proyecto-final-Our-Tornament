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
        
        [System.Web.Http.Route("api/InsertTorneosSeguidos")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarTorneoSeguidoPorUsuario(List<int> LISTA)
        {
            bool Devolver;
            QQSM Conexion = new QQSM();
            Devolver = Conexion.InsertarTorneoSeguidoPorUsuario(LISTA); //IDUsuario, IDTorneo,IDEquipo
            return Ok(Devolver);
        }

        [System.Web.Http.Route("api/DeleteTorneosSeguidos")]
        [System.Web.Http.HttpDelete]
        public IHttpActionResult EliminarTorneoSeguidoPorUsuario(List<int> LISTA)
        {
            bool Devolver;
            QQSM Conexion = new QQSM();
            Devolver = Conexion.DeleteTorneoSeguidoPorUsuario(LISTA); //IDUsuario, IDTorneo
            return Ok(Devolver);
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
        
        [System.Web.Http.Route("api/InsertUsuario")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarUsuarios(Models.Usuario U)
        {
            String Devolver;
            QQSM Conexion = new QQSM();
            Models.Usuario US = new Models.Usuario(U.IdUsuario,U.NombreUsuario, U.Contrasenia, U.FechaDeNacimiento, U.Email, U.GolesEnTorneo);
            Devolver = Conexion.InsertarUsuario(US);
            return Ok(Devolver);
        }

        [System.Web.Http.Route("api/InsertJugadoresxEquipos")]
        [System.Web.Http.HttpPost]
        public IHttpActionResult InsertarJugadoresxEquipos(List<int> lista)
        {
            bool Devolver;
            QQSM Conexion = new QQSM();
            Devolver = Conexion.InstertarJugadoresXEquipos(lista);
            return Ok(Devolver);
        }
    }
}
