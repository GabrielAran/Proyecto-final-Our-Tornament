using System;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.Data.SqlClient;

namespace OurTournamentAPI
{
    public class QQSM
    {
        private SqlConnection Conectar()
        {
            //string constring = @"Server=LAPTOP-4HDMLNB7\SQLEXPRESS;Database=OurTournament;Trusted_Connection=True;";
            string constring = @"Server=DESKTOP-F0QOOGP\AAA;Database=OurTournament;Trusted_Connection=True;";
            SqlConnection a = new SqlConnection(constring);
            a.Open();
            return a;
        }

        private void Desconectar(SqlConnection con)
        {
            con.Close();
        }

        public List<Models.Torneo> TraerTorneosPorNombre(String Nombre)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            if (Nombre == "()")
            {
                Consulta.CommandText = "SELECT * FROM Torneos order by NombreTorneo ASC";
            } else
            {
                Consulta.CommandText = "SELECT * FROM Torneos where NombreTorneo LIKE '%" + Nombre + "%'order by NombreTorneo ASC";
            }
            SqlDataReader Lector = Consulta.ExecuteReader();
            Models.Torneo UnTorneo;
            List<Models.Torneo> ListaTorneos = new List<Models.Torneo>();
            while (Lector.Read())
            {
                int idtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                string nombretorneo = Lector["NombreTorneo"].ToString();
                string contraseniadeadministrador = Lector["ContraseniaDeAdministrador"].ToString();
                string linkparaunirse = Lector["LinkParaUnirse"].ToString();
                UnTorneo = new Models.Torneo(idtorneo, nombretorneo, contraseniadeadministrador, linkparaunirse);
                ListaTorneos.Add(UnTorneo);
            }
            Desconectar(con);
            return ListaTorneos;
        }

        public Models.Torneo TraerTorneoPorID(int ID)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Torneos where Torneos.IDTorneo = " + ID;
            SqlDataReader Lector = Consulta.ExecuteReader();
            Models.Torneo UnTorneo = new Models.Torneo();
            if (Lector.Read())
            {
                int idtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                string nombretorneo = Lector["NombreTorneo"].ToString();
                string contraseniadeadministrador = Lector["ContraseniaDeAdministrador"].ToString();
                string linkparaunirse = Lector["LinkParaUnirse"].ToString();
                UnTorneo = new Models.Torneo(idtorneo, nombretorneo, contraseniadeadministrador, linkparaunirse);
            }
            Desconectar(con);
            return UnTorneo;
        }

        public List<int> TraerJornadasPorTorneo(int IDTorneo)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "SELECT Distinct JornadaDelTorneo FROM Partidos where Partidos.IDTorneo = " + IDTorneo;
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<int> ListaJornadas = new List<int>();
            int jornada;
            while (Lector.Read())
            {
                jornada = Convert.ToInt32(Lector["JornadaDelTorneo"]);
                ListaJornadas.Add(jornada);
            }
            Desconectar(con);
            return ListaJornadas;
        }

        public List<Models.Partido> TraerPartidosPorJornada(int IDJornada, int IDTorneo)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Partidos where Partidos.JornadaDelTorneo = " + IDJornada + "and Partidos.IDTorneo = " + IDTorneo;
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.Partido> ListaPartidos = new List<Models.Partido>();
            Models.Partido UnPartido = new Models.Partido();
            while (Lector.Read())
            {
                int IDPartido = Convert.ToInt32(Lector["IDPartido"]);
                DateTime FechaDeEncuentro = Convert.ToDateTime(Lector["FechaDeEncuentro"]);
                String NobreEquipoLocal = Lector["NombreEquipoLocal"].ToString();
                String NombreEquipoVisitante = Lector["NombreEquipoVisitante"].ToString();
                int GolesLocal = Convert.ToInt32(Lector["GolesLocal"]);
                int GolesVisitante = Convert.ToInt32(Lector["GolesVisitante"]);
                int IDtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                int IDjornada = Convert.ToInt32(Lector["JornadaDelTorneo"]);
                UnPartido = new Models.Partido(IDPartido, FechaDeEncuentro, NobreEquipoLocal, NombreEquipoVisitante, GolesLocal, GolesVisitante, IDtorneo, IDjornada);
                ListaPartidos.Add(UnPartido);
            }
            Desconectar(con);
            return ListaPartidos;
        }
        public List<Models.Equipo> TraerListaDePosiciones(int IDTorneo)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Equipos where Equipos.IDTorneo = " + IDTorneo + " order by Puntos desc";
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.Equipo> ListaPosiciones = new List<Models.Equipo>();
            Models.Equipo UnEquipo = new Models.Equipo();
            while (Lector.Read())
            {
                int IDEquipo = Convert.ToInt32(Lector["IDEquipo"]);
                String NobreEquipo = Lector["NombreEquipo"].ToString();
                int IDtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                int Puntos = Convert.ToInt32(Lector["Puntos"]);
                int GolesAFavor = Convert.ToInt32(Lector["GolesAFavor"]);
                int GolesEnContra = Convert.ToInt32(Lector["GolesEnContra"]);
                int PartidosJugados = Convert.ToInt32(Lector["PartidosJugados"]);
                UnEquipo = new Models.Equipo(IDEquipo, NobreEquipo, PartidosJugados, Puntos, GolesAFavor, GolesEnContra, IDtorneo);
                ListaPosiciones.Add(UnEquipo);
            }
            Desconectar(con);
            return ListaPosiciones;
        }

        public void InsertarTorneoSeguidoPorUsuario(List<int> lista)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "insert into SeguidoresXTorneos(IDUsuario,IDTorneo,IDEquipoFavorito) values (" + lista[0] + "," + lista[1] + "," + lista[2] + ")";
            Consulta.ExecuteNonQuery();
            Desconectar(con);
        }

        public List<Models.Goleadores> TraerListaGoleadores(int IDTorneo) {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "select Usuarios.IDUsuario,NombreDeUsuario, Equipos.NombreEquipo,GolesEnTorneo from Usuarios inner join TorneosParticipadosXUsuario on Usuarios.IDUsuario = TorneosParticipadosXUsuario.IDUsuario inner join JugadoresXEquipos on JugadoresXEquipos.IDUsuario = Usuarios.IDUsuario inner join Equipos on Equipos.IDEquipo = JugadoresXEquipos.IDEquipo where TorneosParticipadosXUsuario.IDTorneo = " + IDTorneo + " order by Usuarios.GolesEnTorneo desc";

            SqlDataReader Lector = Consulta.ExecuteReader();

            List<Models.Goleadores> Tablagoleadores = new List<Models.Goleadores>();
            Models.Goleadores UnGoleador = new Models.Goleadores();
            while (Lector.Read())
            {
                int IDUsuario = Convert.ToInt32(Lector["IdUsuario"]);
                String NombreUsuario = Lector["NombreDeUsuario"].ToString();
                String NombreEquipo = Lector["NombreEquipo"].ToString();
                int Goles = Convert.ToInt32(Lector["GolesEnTorneo"]);

                UnGoleador = new Models.Goleadores(IDUsuario, NombreUsuario, NombreEquipo, Goles);
                Tablagoleadores.Add(UnGoleador);
            }
            Desconectar(con);
            return Tablagoleadores;
        }

        public List<Models.GolesXUsuario> TraerGolesXusuario(int IDPartido)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = "select IDPartido, GolesXUsuarioXPartidos.IDUsuario,NombreDeUsuario,CantidadGoles," +
                "Equipos.NombreEquipo from GolesXUsuarioXPartidos inner join Usuarios on Usuarios.IDUsuario = " +
                "GolesXUsuarioXPartidos.IDUsuario inner join JugadoresXEquipos on JugadoresXEquipos.IDUsuario = " +
                "Usuarios.IDUsuario inner join Equipos on JugadoresXEquipos.IDEquipo = Equipos.IDEquipo where " +
                "GolesXUsuarioXPartidos.IDPartido = " + IDPartido;
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.GolesXUsuario> Goles = new List<Models.GolesXUsuario>();
            Models.GolesXUsuario Gol = new Models.GolesXUsuario();
            while (Lector.Read())
            {
                int IDpartido = Convert.ToInt32(Lector["IDPartido"]);
                int IDUsuario = Convert.ToInt32(Lector["IDUsuario"]);
                String NombreUsuario = Lector["NombreDeUsuario"].ToString();
                int CantidadGoles = Convert.ToInt32(Lector["CantidadGoles"]);
                String NombreEquipo = Lector["NombreEquipo"].ToString();

                Gol = new Models.GolesXUsuario(IDpartido, IDUsuario, NombreUsuario, CantidadGoles, NombreEquipo);
                Goles.Add(Gol);
            }
            Desconectar(con);
            return Goles;
        }

        public List<Models.Equipo> TraerEquiposPorIDTorneoYIDEquipo(int IDTorneo, int IDEquipo)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "Select NombreEquipo from Equipos where Equipos.IDTorneo =+ IDTorneo  and Equipos.IDEquipo = +IDEquipo";
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.Equipo> TraerEquiposPorIDTorneoYIDEquipo = new List<Models.Equipo>();
            Models.Equipo ElEquipo = new Models.Equipo();
            while (Lector.Read())
            {
                int IDEquipos = Convert.ToInt32(Lector["IDEquipo"]);
                String NobreEquipo = Lector["NombreEquipo"].ToString();
                int PartidosJugados = Convert.ToInt32(Lector["PartidosJugados"]);
                int Puntos = Convert.ToInt32(Lector["Puntos"]);
                int GolesAFavor = Convert.ToInt32(Lector["GolesAFavor"]);
                int GolesEnContra = Convert.ToInt32(Lector["GolesEnContra"]);
                int IDTorneos = Convert.ToInt32(Lector["IDTorneo"]);

                ElEquipo = new Models.Equipo(IDEquipos, NobreEquipo, PartidosJugados, Puntos, GolesAFavor, GolesEnContra, IDTorneos);
                TraerEquiposPorIDTorneoYIDEquipo.Add(ElEquipo);
            }
            Desconectar(con);
            return TraerEquiposPorIDTorneoYIDEquipo;
        }

        public List<Models.Usuario> TraerUsuariosPorUsuarioContrasenia(string NombreDeUsuario, string Contrasenia)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = " Select Usuario.IDUsuario,NombreDeUsuario,FechaDeNacimiento,Email,GolesEnTorneo,IDTorneo,NombreTorneo,IDEquipo) from Usuarios inner join SeguidoresXTorneos on Usuarios.IDUsuario = SeguidoresXTorneos.IDUsuariuo where Usuarios.NombreDeUsuario = " + NombreDeUsuario + " && Usuarios.Contrasenia = " + Contrasenia + "";
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.Usuario> ListaUsuarios = new List<Models.Usuario>();
            Models.Usuario ElUsuario = new Models.Usuario();
            while (Lector.Read())
            {

                int IDUsuarios = Convert.ToInt32(Lector["IdUsuario"]);
                string NombreUsuario = Convert.ToString(Lector["NombreUsuario"]);
                string contrasenia = Convert.ToString(Lector["Comtrasenia"]);
                DateTime FechaDeNacimiento = Convert.ToDateTime(Lector["FechaDeNacimiento"]);
                string Email = Convert.ToString(Lector["Email"]);
                int GolesEnTorneo = Convert.ToInt32(Lector["GolesEnTorneo"]);
                int IDEquipo = Convert.ToInt32(Lector["IDEquipo"]);

                ElUsuario = new Models.Usuario(IDUsuarios, NombreUsuario, contrasenia, FechaDeNacimiento, Email, GolesEnTorneo, IDEquipo);
                ListaUsuarios.Add(ElUsuario);
            }
            Desconectar(con);
            return ListaUsuarios;
        }

        public List<Models.Usuario> TraerUsuariosPorID(int IDUsuario)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "Select * from Usuarios where Usuarios.IDUsuario =+ " + IDUsuario;
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Models.Usuario> ListaUsuariosPorID = new List<Models.Usuario>();
            Models.Usuario ElUUsuario = new Models.Usuario();
            while (Lector.Read())
            {
                int IDUsuarios = Convert.ToInt32(Lector["IdUsuario"]);
                string NombreUsuario = Convert.ToString(Lector["NombreUsuario"]);
                string Contrasenia = Convert.ToString(Lector["Comtrasenia"]);
                DateTime FechaDeNacimiento = Convert.ToDateTime(Lector["FechaDeNacimiento"]);
                string Email = Convert.ToString(Lector["Email"]);
                int GolesEnTorneo = Convert.ToInt32(Lector["GolesEnTorneo"]);
                int IDEquipo = Convert.ToInt32(Lector["IDEquipo"]);

                ElUUsuario = new Models.Usuario(IDUsuarios, NombreUsuario, Contrasenia, FechaDeNacimiento, Email, GolesEnTorneo, IDEquipo);
                ListaUsuariosPorID.Add(ElUUsuario);
            }
            Desconectar(con);
            return ListaUsuariosPorID;
        }
    }
}

