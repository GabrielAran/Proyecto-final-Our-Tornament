using System;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.Data.SqlClient;

namespace OurTournamentAPI
{
    public class QQSM
    {
        private SqlConnection con;
        private SqlConnection Conectar()
        {
            string constring = @"Server=LAPTOP-4HDMLNB7\SQLEXPRESS;Database=OurTournament;Trusted_Connection=True;";
            //string constring = @"Server=DESKTOP-F0QOOGP\AAA;Database=OurTournament;Trusted_Connection=True;";
            SqlConnection a = new SqlConnection(constring);
            a.Open();
            return a;
        }

        private void Desconectar(SqlConnection con)
        {
            con.Close();
        }

        private SqlDataReader HacerSelect(String STRConsulta)
        {
            con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            Consulta.CommandText = STRConsulta;
            SqlDataReader Lector = Consulta.ExecuteReader();
            return Lector;
        }

        private bool HacerInsertODelete(String STRConsulta)
        {
            bool Devolver = false;
            con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = CommandType.Text;
            try
            {
                Consulta.CommandText = STRConsulta;
                Consulta.ExecuteNonQuery();
                Devolver = true;
            }
            catch (Exception)
            {
            }
            Desconectar(con);
            return Devolver;
        }

        public List<Models.TorneoSeguido> TraerTorneosPorNombre(String Nombre,int IDUsuario)
        {
            String C;
            if (Nombre == "()")
            {
                C = "SELECT Torneos.*, CASE WHEN SeguidoresXTorneos.IDUsuario IS NOT NULL THEN 1 ELSE 0 END AS Siguiendo FROM Torneos LEFT JOIN SeguidoresXTorneos ON Torneos.IDTorneo = SeguidoresXTorneos.IDTorneo AND SeguidoresXTorneos.IDUsuario = " +IDUsuario+ " order by Torneos.NombreTorneo ASC";
            } else
            {
                C = "SELECT Torneos.*, CASE WHEN SeguidoresXTorneos.IDUsuario IS NOT NULL THEN 1 ELSE 0 END AS Siguiendo" +
                    " FROM Torneos LEFT JOIN SeguidoresXTorneos ON Torneos.IDTorneo = SeguidoresXTorneos.IDTorneo AND SeguidoresXTorneos.IDUsuario = " + IDUsuario +
                    " where NombreTorneo LIKE '%" + Nombre + "%'" +
                    " order by Torneos.NombreTorneo ASC";
            }
            SqlDataReader Lector = HacerSelect(C);
            Models.TorneoSeguido UnTorneo;
            List<Models.TorneoSeguido> ListaTorneos = new List<Models.TorneoSeguido>();
            while (Lector.Read())
            {
                int idtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                string nombretorneo = Lector["NombreTorneo"].ToString();
                string contraseniadeadministrador = Lector["ContraseniaDeAdministrador"].ToString();
                string linkparaunirse = Lector["LinkParaUnirse"].ToString();
                Boolean Siguiendo = Convert.ToBoolean(Lector["Siguiendo"]);
                UnTorneo = new Models.TorneoSeguido(idtorneo, nombretorneo, contraseniadeadministrador, linkparaunirse,Siguiendo);
                ListaTorneos.Add(UnTorneo);
            }
            Desconectar(con);
            return ListaTorneos;
        }

        public bool InsertarTorneos(List<int> ListaTorneos)
        {
            bool Devolver;
            String C = "insert into Torneos(IDTorneo,NombreTorneo,ContraseniaDeAdministrador,LinkParaUnirse) " +
                "values (" + ListaTorneos[0] + "," + ListaTorneos[1] + "," + ListaTorneos[2] + "," + ListaTorneos[3] + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public Models.Torneo TraerTorneoPorID(int ID)
        {
            String C = "SELECT * FROM Torneos where Torneos.IDTorneo = " + ID;
            SqlDataReader Lector = HacerSelect(C);
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
            String C = "SELECT Distinct JornadaDelTorneo FROM Partidos where Partidos.IDTorneo = " + IDTorneo;
            SqlDataReader Lector = HacerSelect(C);
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
            String C = "SELECT * FROM Partidos where Partidos.JornadaDelTorneo = " + IDJornada + "and Partidos.IDTorneo = " + IDTorneo;
            SqlDataReader Lector = HacerSelect(C);
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

        public bool InsertarPartidos(Models.Partido Partidos)
        {
            bool Devolver;
            String C = "insert into Partidos(IDPartido,FechaDeEncuentro,JornadaDelTorneo,NombreEquipoLocal," +
                "NombreEquipoVisitante,GolesLocal,GolesVisitante,IDTorneo) values ('" + Partidos.IDPartido 
                + "','" + Partidos.FechaDeEncuentro + "','" + Partidos.Jornada + "','" + Partidos.NombreEquipoLocal 
                + "','" + Partidos.NombreEquipoVisitante + "','" + Partidos.GolesLocal + "','" + Partidos.GolesVisitante 
                + "','" + Partidos.IDTorneo + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public List<Models.Equipo> TraerListaDePosiciones(int IDTorneo)
        {
            String C = "SELECT * FROM Equipos where Equipos.IDTorneo = " + IDTorneo + " order by Puntos desc";
            SqlDataReader Lector = HacerSelect(C);
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

        public bool InsertarEquipos(List<int> ListaEquipos)
        {
            bool Devolver;
            String C = "insert into Equipos(IDEquipo,Nombre,PartidosJugados,Puntos,GolesAFavor,GolesEnContra,IDTorneo) " +
                "values (" + ListaEquipos[0] + "," + ListaEquipos[1] + "," + ListaEquipos[2] 
                + "," + ListaEquipos[3] + "," + ListaEquipos[4] + "," + ListaEquipos[5] + "," + ListaEquipos[6] + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public bool InsertarTorneoSeguidoPorUsuario(List<int> lista)
        {
            bool Devolver;
            String EFav = "null";
            if(lista[2]==-1)
            {
                EFav = "NULL";
            }else
            {
                EFav = lista[2].ToString();
            }
            String C = "insert into SeguidoresXTorneos(IDUsuario,IDTorneo,IDEquipoFavorito) values" +
                " (" + lista[0] + "," + lista[1] + "," + EFav + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public bool DeleteTorneoSeguidoPorUsuario(List<int> lista)
        {
            bool Devolver;
            String C = "delete from SeguidoresXTorneos where IDUsuario = " + lista[0] + " and IDTorneo = " + lista[1];
            return Devolver = HacerInsertODelete(C);
        }

        public List<Models.Goleadores> TraerListaGoleadores(int IDTorneo) {
            String C = "select Usuarios.IDUsuario,NombreDeUsuario, Equipos.NombreEquipo,GolesEnTorneo from Usuarios inner join" +
                " TorneosParticipadosXUsuario on Usuarios.IDUsuario = TorneosParticipadosXUsuario.IDUsuario inner join JugadoresXEquipos" +
                " on JugadoresXEquipos.IDUsuario = Usuarios.IDUsuario inner join Equipos on Equipos.IDEquipo = JugadoresXEquipos.IDEquipo" +
                " where TorneosParticipadosXUsuario.IDTorneo = " + IDTorneo + " order by Usuarios.GolesEnTorneo desc";
            SqlDataReader Lector = HacerSelect(C);
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
            String C = "select IDPartido, GolesXUsuarioXPartidos.IDUsuario,NombreDeUsuario,CantidadGoles," +
                "Equipos.NombreEquipo from GolesXUsuarioXPartidos inner join Usuarios on Usuarios.IDUsuario = " +
                "GolesXUsuarioXPartidos.IDUsuario inner join JugadoresXEquipos on JugadoresXEquipos.IDUsuario = " +
                "Usuarios.IDUsuario inner join Equipos on JugadoresXEquipos.IDEquipo = Equipos.IDEquipo where " +
                "GolesXUsuarioXPartidos.IDPartido = " + IDPartido;
            SqlDataReader Lector = HacerSelect(C);
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

        public Models.Equipo TraerEquipoPorIDEquipo(int IDEquipo)
        {
            String C = "Select * from Equipos where Equipos.IDEquipo = " + IDEquipo;
            SqlDataReader Lector = HacerSelect(C);
            Models.Equipo ElEquipo = new Models.Equipo();
            if (Lector.Read())
            {
                int IDEquipos = Convert.ToInt32(Lector["IDEquipo"]);
                String NobreEquipo = Lector["NombreEquipo"].ToString();
                int PartidosJugados = Convert.ToInt32(Lector["PartidosJugados"]);
                int Puntos = Convert.ToInt32(Lector["Puntos"]);
                int GolesAFavor = Convert.ToInt32(Lector["GolesAFavor"]);
                int GolesEnContra = Convert.ToInt32(Lector["GolesEnContra"]);
                int IDTorneos = Convert.ToInt32(Lector["IDTorneo"]);

                ElEquipo = new Models.Equipo(IDEquipos, NobreEquipo, PartidosJugados, Puntos, GolesAFavor, GolesEnContra, IDTorneos);
            }
            Desconectar(con);
            return ElEquipo;
        }

        public List<Models.Usuario> TraerUsuariosPorUsuarioContrasenia(string NombreDeUsuario, string Contrasenia)
        {
            String C = " Select Usuario.IDUsuario,NombreDeUsuario,FechaDeNacimiento,Email,GolesEnTorneo,IDTorneo,NombreTorneo,IDEquipo)" +
                " from Usuarios inner join SeguidoresXTorneos on Usuarios.IDUsuario = SeguidoresXTorneos.IDUsuariuo" +
                " where Usuarios.NombreDeUsuario = " + NombreDeUsuario + " && Usuarios.Contrasenia = " + Contrasenia + "";
            SqlDataReader Lector = HacerSelect(C);
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

                ElUsuario = new Models.Usuario(IDUsuarios, NombreUsuario, contrasenia, FechaDeNacimiento, Email, GolesEnTorneo);
                ListaUsuarios.Add(ElUsuario);
            }
            Desconectar(con);
            return ListaUsuarios;
        }

        public bool InsertarUsuario(Models.Usuario Usuario)
        {
            bool Devolver;
            String C = "insert into Usuarios(NombreDeUsuario,Contrasenia,FechaDeNacimiento,Email,GolesEnTorneo) values " +
                "('"+Usuario.NombreUsuario + "','" + Usuario.Contrasenia + "','" + Usuario.FechaDeNacimiento.ToString("yyyy-MM-dd")
                + "','" + Usuario.Email + "'," + Usuario.GolesEnTorneo + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public Models.Usuario TraerUsuariosPorID(int IDUsuario)
        {
            String C = "Select * from Usuarios where Usuarios.IDUsuario =+ " + IDUsuario;
            SqlDataReader Lector = HacerSelect(C);
            Models.Usuario UnUsuario = new Models.Usuario();
            while (Lector.Read())
            {
                int IDUsuarios = Convert.ToInt32(Lector["IdUsuario"]);
                string NombreUsuario = Convert.ToString(Lector["NombreDeUsuario"]);
                string Contrasenia = Convert.ToString(Lector["Contrasenia"]);
                DateTime FechaDeNacimiento = Convert.ToDateTime(Lector["FechaDeNacimiento"]);
                string Email = Convert.ToString(Lector["Email"]);
                int GolesEnTorneo = Convert.ToInt32(Lector["GolesEnTorneo"]);

                UnUsuario = new Models.Usuario(IDUsuarios, NombreUsuario, Contrasenia, FechaDeNacimiento, Email, GolesEnTorneo);
            }
            Desconectar(con);
            return UnUsuario;
        }

        public List<Models.Torneo> TorneosSeguidosPorUsuario(int IDUsuario)
        {
            String C = "Select Torneos.IDTorneo,NombreTorneo,ContraseniaDeAdministrador,LinkParaUnirse,TorneosParticipadosXUsuario.IDUsuario from Torneos inner join TorneosParticipadosXUsuario on Torneos.IDTorneo = TorneosParticipadosXUsuario.IDTorneo where TorneosParticipadosXUsuario.IDUsuario =  " + IDUsuario;
            SqlDataReader Lector = HacerSelect(C);
            List<Models.Torneo> TorneosSeguidosPorUsuario = new List<Models.Torneo>();
            Models.Torneo UnTorneo = new Models.Torneo();

            while (Lector.Read())
            {
                int IdTorneo = Convert.ToInt32(Lector["IDTorneo"]);
                string NombreTorneo = Convert.ToString(Lector["NombreTorneo"]);
                string ContraseniaDeAdministrador = Convert.ToString(Lector["ContraseniaDeAdministrador"]);
                string LinkParaUnirse = Convert.ToString(Lector["LinkParaUnirse"]);
                int IdUsuario = Convert.ToInt32(Lector["IDUsuario"]);

                UnTorneo = new Models.Torneo(IdTorneo, NombreTorneo, ContraseniaDeAdministrador, LinkParaUnirse);
                TorneosSeguidosPorUsuario.Add(UnTorneo);
            }
            Desconectar(con);
            return TorneosSeguidosPorUsuario;
        }

        public List<Models.Noticia> TraerNoticiasPorTorneo (int IDTorneo)  
        {
            String C = "Select * from Noticias where IDTorneo = "+ IDTorneo +"order by Destacada DESC";
            SqlDataReader Lector = HacerSelect(C);
            Models.Noticia UnaNoticia = new Models.Noticia();

            List<Models.Noticia> TraerNoticiasPorTorneo = new List<Models.Noticia>();
            while(Lector.Read())
            {
                int IDtorneo = Convert.ToInt32(Lector["IDTorneo"]);
                int IDnoticia = Convert.ToInt32(Lector["IDNoticia"]);
                string Titulo = Lector["Titulo"].ToString();
                string Descripcion = Lector["Descripcion"].ToString();
                bool Destacada = Convert.ToBoolean(Lector["Destacada"]);
                int Foto = Convert.ToInt32(Lector["IDFoto"]);
                DateTime Fecha = Convert.ToDateTime(Lector["Fecha"]);
                UnaNoticia = new Models.Noticia(IDnoticia, IDtorneo, Titulo, Descripcion, Destacada, Foto, Fecha);
                TraerNoticiasPorTorneo.Add(UnaNoticia);
            }
            Desconectar(con);

            return TraerNoticiasPorTorneo;
        }

        public bool InstertarNoticias(Models.Noticia Noticia)
        {
            bool Devolver;
            String C = "insert into Noticias (IDNoticia,Titulo,Descripcion,IDFoto,IDTorneo,Fecha,Destacada) values " +
                "('" + Noticia.IDTorneo + "','" + Noticia.IDNoticia + "','" + Noticia.Titulo + "','" + Noticia.Descripcion 
                + "','" + Noticia.Destacada + "','" + Noticia.Foto + "','" + Noticia.Fecha + ")";
            return Devolver = HacerInsertODelete(C);
        }

        public List<Models.Usuario> TraerJugadoresXEquipos(int IDEquipo)
        {
            String C = "select * from Usuarios inner join JugadoresXEquipos on Usuarios.IDUsuario = JugadoresXEquipos.IDUsuario where JugadoresXEquipos.IDEquipo = " + IDEquipo;
            SqlDataReader Lector = HacerSelect(C);
            List<Models.Usuario> ListaUsuarios = new List<Models.Usuario>();
            Models.Usuario UnUsuario = new Models.Usuario();
            while (Lector.Read())
            {
                int IDUsuarios = Convert.ToInt32(Lector["IDUsuario"]);
                string NombreUsuario = Convert.ToString(Lector["NombreDeUsuario"]);
                string Contrasenia = Convert.ToString(Lector["Contrasenia"]);
                DateTime FechaDeNacimiento = Convert.ToDateTime(Lector["FechaDeNacimiento"]);
                string Email = Convert.ToString(Lector["Email"]);
                int GolesEnTorneo = Convert.ToInt32(Lector["GolesEnTorneo"]);

                UnUsuario = new Models.Usuario(IDUsuarios, NombreUsuario, Contrasenia, FechaDeNacimiento, Email, GolesEnTorneo);
                ListaUsuarios.Add(UnUsuario);
            }
            Desconectar(con);
            return ListaUsuarios;
        }

        public bool InstertarJugadoresXEquipos(List<int> lista)
        {
            bool Devolver;
            String C = "insert into JugadoresXEquipos (IDUsuario,IDEquipo) values (IDUsuario =  " + lista[0] + " and IDEquipo = " + lista[1] + ")";
            return Devolver = HacerInsertODelete(C);
        }
    }
}


