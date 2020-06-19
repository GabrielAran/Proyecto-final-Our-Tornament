﻿using System;
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

            string constring = @"Server=LAPTOP-UAO80ANB\MSSQLSERVER01;Database=OurTorunament;Trusted_Connection=True;";
            SqlConnection a = new SqlConnection(constring);
            a.Open();
            return a;
        }

        private void Desconectar(SqlConnection con)
        {
            con.Close();
        }

        public Models.Torneo TraerTorneo(int ID)
        {
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Torneos where Torneos.IDTorneo = " + ID;
            SqlDataReader Lector = Consulta.ExecuteReader();
            Models.Torneo UnTorneo = new Models.Torneo();
            while (Lector.Read())
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

        /*
        public static List<Respuestas> TraerRespuestas()
        {

            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Respuestas where Respuestas.IDPregunta =" + PreguntaActual;
            SqlDataReader Lector = Consulta.ExecuteReader();
            List<Respuestas> aux = new List<Respuestas>();
            Respuestas A = new Respuestas();
            while (Lector.Read())
            {
                int idrespuesta = Convert.ToInt32(Lector["IDRespuesta"]);
                int idpregunta = Convert.ToInt32(Lector["IDPregunta"]);
                char opcionrespuesta = Convert.ToChar(Lector["OpcionRespuesta"]);
                string respuesta = Lector["Respuesta"].ToString();
                bool correcta = Convert.ToBoolean(Lector["Correcta"]);
                A = new Respuestas(idrespuesta, idpregunta, opcionrespuesta, respuesta, correcta);
                aux.Add(A);
            }
            Desconectar(con);
            return aux;
        }

        public static bool RespuestaUsuario(char Opción, char OpcionComodin)
        {

            Pozo PozoActual = new Pozo();
            SqlConnection con = Conectar();
            SqlCommand Consulta = con.CreateCommand();
            Consulta.CommandType = System.Data.CommandType.Text;
            Consulta.CommandText = "SELECT * FROM Respuestas where Respuestas.IDPregunta =" + PreguntaActual + "and Respuestas.Correcta = '1'";
            SqlDataReader Lector = Consulta.ExecuteReader();
            char OpResp = ' ';
            bool Respuesta;

            while (Lector.Read())
            {
                int idrespuesta = Convert.ToInt32(Lector["IDRespuesta"]);
                int idpregunta = Convert.ToInt32(Lector["IDPregunta"]);
                char opcionrespuesta = Convert.ToChar(Lector["OpcionRespuesta"]);
                string respuesta = Lector["Respuesta"].ToString();
                bool correcta = Convert.ToBoolean(Lector["Correcta"]);
                OpResp = opcionrespuesta;
            }
            if (OpcionComodin == ' ' & OpResp == Opción)
            {
                Respuesta = true;
                PosicionPozo++;
                PozoActual = ListaPozo[PosicionPozo];
                if (PozoActual.ValorSeguro == true)
                {
                    PozoGanado = PozoActual.Importe;
                }
                PreguntasRespondidas.Add(PreguntaActual);
                Random random2 = new Random();
                PreguntaActual = random2.Next(0, 16);
                bool existe = PreguntasRespondidas.Contains(PreguntaActual);
                while (existe == true)
                {
                    PreguntaActual = random2.Next(0, 16);
                }

            }
            else
            {
                if (OpcionComodin != ' ' & OpcionComodin == OpResp & ComodinDobleChance == true)
                {
                    ComodinDobleChance = false;
                    Respuesta = true;
                    PosicionPozo++;
                    PozoActual = ListaPozo[PosicionPozo];
                    if (PozoActual.ValorSeguro == true)
                    {
                        PozoGanado = PozoActual.Importe;
                    }
                    PreguntasRespondidas.Add(PreguntaActual);
                    Random random2 = new Random();
                    PreguntaActual = random2.Next(0, 16);
                    bool existe = PreguntasRespondidas.Contains(PreguntaActual);
                    while (existe == true)
                    {
                        PreguntaActual = random2.Next(0, 16);
                    }

                }
                else
                {
                    Respuesta = false;
                }
            }
            Desconectar(con);
            return Respuesta;
        }

        public static List<Pozo> ListarPozo()
        {
            return ListaPozo;
        }

        public static Pozo DevolverPozoJugadaActual()
        {
            Pozo PozoActual = new Pozo();
            PozoActual = ListaPozo[PosicionPozo];

            return PozoActual;
        }

        public static List<char> Descartar50()
        {
            List<char> CaracteresIncorrectos = new List<char>();
            if (Comodin5050 == false)
            {
                Comodin5050 = true;

                SqlConnection con = Conectar();
                SqlCommand Consulta = con.CreateCommand();
                Consulta.CommandType = System.Data.CommandType.Text;
                Consulta.CommandText = "SELECT * FROM Respuestas where Respuestas.IDPregunta =" + PreguntaActual + "and Respuestas.Correcta = '0'";
                SqlDataReader Lector = Consulta.ExecuteReader();
                while (Lector.Read())
                {
                    char opcionrespuesta = Convert.ToChar(Lector["OpcionRespuesta"]);
                    CaracteresIncorrectos.Add(opcionrespuesta);
                }
                Random ran = new Random();
                int CaracterQueDejo = ran.Next(0, 2);
                CaracteresIncorrectos.RemoveAt(CaracterQueDejo);
                Desconectar(con);
                return CaracteresIncorrectos;
            }
            else
            {
                return CaracteresIncorrectos;
            }
        }

        public static Preguntas SaltearPregunta()
        {
            Preguntas NuevaPregunta = new Preguntas();
            if (ComodinSaltearPregunta == false)
            {
                PreguntasRespondidas.Add(PreguntaActual);
                Random random2 = new Random();
                PreguntaActual = random2.Next(0, 16);
                bool existe = PreguntasRespondidas.Contains(PreguntaActual);
                while (existe == true)
                {
                    PreguntaActual = random2.Next(0, 16);
                }
                SqlConnection con = Conectar();
                SqlCommand Consulta = con.CreateCommand();
                Consulta.CommandType = System.Data.CommandType.Text;
                Consulta.CommandText = "SELECT * FROM Preguntas where Preguntas.IDPregunta =" + PreguntaActual;
                SqlDataReader Lector = Consulta.ExecuteReader();
                Preguntas Actual = new Preguntas();
                while (Lector.Read())
                {
                    int idpregunta = Convert.ToInt32(Lector["IDPregunta"]);
                    string textopregunta = Lector["TextoPregunta"].ToString();
                    int dificultad = Convert.ToInt32(Lector["DNI"]);
                    Actual = new Preguntas(idpregunta, textopregunta, dificultad);
                }
                Desconectar(con);
                return NuevaPregunta;
            }
            else
            {
                return NuevaPregunta;
            }
        }

        public static Dictionary<string, int> DevolverResultados()
        {
            Dictionary<string, int> DicResultados = new Dictionary<string, int>();
            DicResultados.Add("Cantidad de correctas", PosicionPozo);
            DicResultados.Add("Pozo Ganado ", PozoGanado);
            DicResultados.Add("Pozo a Ganar", PozoaGanar);
            return DicResultados;
        }
        */
    }
}
