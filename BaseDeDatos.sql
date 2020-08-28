USE [master]
GO
/****** Object:  Database [OurTournament]    Script Date: 27/8/2020 20:01:52 ******/
CREATE DATABASE [OurTournament]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'OurTournament', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\OurTournament.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'OurTournament_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\OurTournament_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [OurTournament] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [OurTournament].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [OurTournament] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [OurTournament] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [OurTournament] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [OurTournament] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [OurTournament] SET ARITHABORT OFF 
GO
ALTER DATABASE [OurTournament] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [OurTournament] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [OurTournament] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [OurTournament] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [OurTournament] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [OurTournament] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [OurTournament] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [OurTournament] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [OurTournament] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [OurTournament] SET  DISABLE_BROKER 
GO
ALTER DATABASE [OurTournament] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [OurTournament] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [OurTournament] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [OurTournament] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [OurTournament] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [OurTournament] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [OurTournament] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [OurTournament] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [OurTournament] SET  MULTI_USER 
GO
ALTER DATABASE [OurTournament] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [OurTournament] SET DB_CHAINING OFF 
GO
ALTER DATABASE [OurTournament] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [OurTournament] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [OurTournament] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [OurTournament] SET QUERY_STORE = OFF
GO
USE [OurTournament]
GO
/****** Object:  Table [dbo].[Equipos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Equipos](
	[IDEquipo] [int] NOT NULL,
	[NombreEquipo] [varchar](20) NULL,
	[IDTorneo] [int] NULL,
	[Puntos] [int] NULL,
	[GolesAFavor] [int] NULL,
	[GolesEnContra] [int] NULL,
	[PartidosJugados] [int] NULL,
 CONSTRAINT [PK_Equipos] PRIMARY KEY CLUSTERED 
(
	[IDEquipo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Fotos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Fotos](
	[IDFoto] [int] IDENTITY(1,1) NOT NULL,
	[Direccion] [varchar](200) NULL,
	[IDTorneo] [int] NULL,
 CONSTRAINT [PK_Fotos] PRIMARY KEY CLUSTERED 
(
	[IDFoto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GolesXUsuarioXPartidos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GolesXUsuarioXPartidos](
	[IDUsuario] [int] NOT NULL,
	[IDPartido] [int] NOT NULL,
	[CantidadGoles] [int] NULL,
 CONSTRAINT [PK_GolesXPartidos] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC,
	[IDPartido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JugadoresXEquipos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JugadoresXEquipos](
	[IDEquipo] [int] NOT NULL,
	[IDUsuario] [int] NOT NULL,
 CONSTRAINT [PK_JugadoresXEquipos] PRIMARY KEY CLUSTERED 
(
	[IDEquipo] ASC,
	[IDUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ListaDeEsperaParaTorneo]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ListaDeEsperaParaTorneo](
	[IDUsuario] [int] NOT NULL,
	[IDTorneo] [int] NOT NULL,
 CONSTRAINT [PK_ListaDeEsperaParaTorneo] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC,
	[IDTorneo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Noticias]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Noticias](
	[IDNoticia] [int] IDENTITY(1,1) NOT NULL,
	[Titulo] [varchar](40) NULL,
	[Descripcion] [varchar](300) NULL,
	[IDFoto] [int] NULL,
	[IDTorneo] [int] NULL,
	[Fecha] [date] NULL,
	[Destacada] [bit] NULL,
 CONSTRAINT [PK_Noticias] PRIMARY KEY CLUSTERED 
(
	[IDNoticia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Partidos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Partidos](
	[IDPartido] [int] IDENTITY(1,1) NOT NULL,
	[FechaDeEncuentro] [date] NULL,
	[JornadaDelTorneo] [int] NULL,
	[NombreEquipoLocal] [varchar](30) NULL,
	[NombreEquipoVisitante] [varchar](30) NULL,
	[GolesLocal] [int] NULL,
	[GolesVisitante] [int] NULL,
	[IDTorneo] [int] NULL,
 CONSTRAINT [PK_Partidos] PRIMARY KEY CLUSTERED 
(
	[IDPartido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SeguidoresXTorneos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeguidoresXTorneos](
	[IDUsuario] [int] NOT NULL,
	[IDTorneo] [int] NOT NULL,
	[IDEquipoFavorito] [int] NULL,
 CONSTRAINT [PK_SeguidoresXTorneos] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC,
	[IDTorneo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Torneos]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Torneos](
	[IDTorneo] [int] IDENTITY(1,1) NOT NULL,
	[NombreTorneo] [varchar](20) NULL,
	[ContraseniaDeAdministrador] [varchar](20) NULL,
	[LinkParaUnirse] [varchar](40) NULL,
 CONSTRAINT [PK_Torneos] PRIMARY KEY CLUSTERED 
(
	[IDTorneo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TorneosParticipadosXUsuario]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TorneosParticipadosXUsuario](
	[IDUsuario] [int] NOT NULL,
	[IDTorneo] [int] NOT NULL,
	[Administrador] [bit] NULL,
 CONSTRAINT [PK_TorneosParticipadosXUsuario] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC,
	[IDTorneo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 27/8/2020 20:01:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[IDUsuario] [int] IDENTITY(1,1) NOT NULL,
	[NombreDeUsuario] [varchar](20) NULL,
	[Contrasenia] [varchar](20) NULL,
	[FechaDeNacimiento] [date] NULL,
	[Email] [varchar](40) NULL,
	[GolesEnTorneo] [int] NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (1, N'River Plate', 1, 1, 3, 2, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (2, N'Boca Juniors', 1, 2, 2, 4, 6)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (3, N'Independiente', 1, 3, 6, 5, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (4, N'Racing', 1, 4, 4, 6, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (5, N'SanLorenzo', 2, 5, 6, 1, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (6, N'Huracan', 2, 6, 16, 2, 6)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (7, N'Barcelona', 2, 7, 5, 3, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (8, N'Real Madrid', 2, 8, 7, 2, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (9, N'Atletico Madrid', 2, 2, 9, 3, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (10, N'Nueva Chicago', 2, 5, 5, 1, 5)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (11, N'Bayern Munich', 5, 12, 20, 7, 3)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (12, N'Leipzig', 5, 9, 10, 4, 3)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (13, N'Dortmund', 5, 8, 13, 5, 3)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (14, N'Wolfburgo', 5, 10, 10, 10, 3)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (15, N'bokita', 10, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (16, N'Riveeer', 9, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (17, N'RushearInsano', 8, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (18, N'Juventus', 6, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (19, N'Aleeeti', 4, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (20, N'LosBohe', 7, 6, 6, 3, 2)
INSERT [dbo].[Equipos] ([IDEquipo], [NombreEquipo], [IDTorneo], [Puntos], [GolesAFavor], [GolesEnContra], [PartidosJugados]) VALUES (21, N'Chaca', 7, 2, 1, 2, 1)
GO
SET IDENTITY_INSERT [dbo].[Fotos] ON 

INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (1, N'https://es.wikipedia.org/wiki/F%C3%BAtbol#/media/Archivo:Football_iu_1996.jpg', 1)
INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (2, N'https://www.mundodeportivo.com/rf/image_large/GODO/MD/p7/Futbol/Imagenes/2020/08/20/Recortada/20200820-637334974852781493_20200820052502-kufF-U48291400994803D-980x554@MundoDeportivo-Web.jpg', 1)
INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (3, N'https://www.rushbet.co/blog/wp-content/uploads/2019/12/shutterstock_1113319799-1000x605.jpg', 1)
INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (4, N'https://a.espncdn.com/combiner/i?img=/media/motion/2020/0826/evc_FUTBOL_20200826_no_event_name_3a87d4b3_692a_4e1141/evc_FUTBOL_20200826_no_event_name_3a87d4b3_692a_4e1141.jpg', 2)
INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (5, N'https://www.telam.com.ar/advf/imagenes/2020/03/5e691ced2fd99_1004x565.jpg', 2)
INSERT [dbo].[Fotos] ([IDFoto], [Direccion], [IDTorneo]) VALUES (6, N'https://cdn.aarp.net/content/dam/aarp/entertainment/television/2017/07/1140-world-cup-trophy-ball-trivia-esp.imgcache.rev4469921697c064fd0c53617c437e67d8.jpg', 2)
SET IDENTITY_INSERT [dbo].[Fotos] OFF
GO
INSERT [dbo].[GolesXUsuarioXPartidos] ([IDUsuario], [IDPartido], [CantidadGoles]) VALUES (1, 1, 3)
INSERT [dbo].[GolesXUsuarioXPartidos] ([IDUsuario], [IDPartido], [CantidadGoles]) VALUES (1, 2, 4)
INSERT [dbo].[GolesXUsuarioXPartidos] ([IDUsuario], [IDPartido], [CantidadGoles]) VALUES (2, 1, 3)
INSERT [dbo].[GolesXUsuarioXPartidos] ([IDUsuario], [IDPartido], [CantidadGoles]) VALUES (2, 2, 5)
GO
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (1, 1)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (1, 4)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (2, 2)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (2, 5)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (2, 6)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (2, 7)
INSERT [dbo].[JugadoresXEquipos] ([IDEquipo], [IDUsuario]) VALUES (3, 3)
GO
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (1, 2)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (2, 3)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (3, 2)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (4, 5)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (5, 1)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (6, 2)
INSERT [dbo].[ListaDeEsperaParaTorneo] ([IDUsuario], [IDTorneo]) VALUES (7, 1)
GO
SET IDENTITY_INSERT [dbo].[Partidos] ON 

INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (1, CAST(N'2020-04-05' AS Date), 1, N'River Plate', N'Boca juniors', 2, 1, 1)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (2, CAST(N'2020-11-10' AS Date), 1, N'Independiente', N'Racing', 6, 1, 1)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (3, CAST(N'2020-03-27' AS Date), 2, N'Boca Juniors', N'Racing', 2, 7, 1)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (4, CAST(N'2020-07-05' AS Date), 2, N'River Plate', N'Independiente', 1, 2, 1)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (5, CAST(N'2020-07-04' AS Date), 1, N'San Lorenzo', N'Huracan', 6, 5, 1)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (6, CAST(N'2020-04-23' AS Date), 1, N'Barcelona', N'Real Madrid', 1, 1, 2)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (7, CAST(N'2020-04-24' AS Date), 1, N'Dortmund', N'Bayern Munich', 1, 3, 5)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (8, CAST(N'2020-04-25' AS Date), 1, N'Leipzig', N'Wolfsburgo', 2, 3, 5)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (9, CAST(N'2020-04-26' AS Date), 2, N'Nueva Chicago', N'Atletico Madrid', 1, 2, 2)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (10, CAST(N'2020-11-21' AS Date), 1, N'LosBohe', N'Chaca', 2, 1, 7)
INSERT [dbo].[Partidos] ([IDPartido], [FechaDeEncuentro], [JornadaDelTorneo], [NombreEquipoLocal], [NombreEquipoVisitante], [GolesLocal], [GolesVisitante], [IDTorneo]) VALUES (11, CAST(N'2020-11-25' AS Date), 2, N'Chaca', N'LosBohe', -1, -1, 1)
SET IDENTITY_INSERT [dbo].[Partidos] OFF
GO
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (1, 1, 2)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (2, 2, 2)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (3, 4, 3)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (4, 4, 4)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (5, 5, 5)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (6, 5, 5)
INSERT [dbo].[SeguidoresXTorneos] ([IDUsuario], [IDTorneo], [IDEquipoFavorito]) VALUES (7, 7, 7)
GO
SET IDENTITY_INSERT [dbo].[Torneos] ON 

INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (1, N'Liga1', N'Admin', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (2, N'Liga2', N'Admin2', N'234')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (3, N'Superliga', N'Admin3', N'567')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (4, N'Santander', N'Admin4', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (5, N'Bundesliga', N'Admin5', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (6, N'CalcioA', N'Admin6', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (7, N'GambetaCorta', N'Admin7', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (8, N'Fortnite', N'Admin8', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (9, N'Los Riverplatenses', N'Admin9', N'123')
INSERT [dbo].[Torneos] ([IDTorneo], [NombreTorneo], [ContraseniaDeAdministrador], [LinkParaUnirse]) VALUES (10, N'Bokitapasiooon', N'Admin10', N'123')
SET IDENTITY_INSERT [dbo].[Torneos] OFF
GO
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (1, 1, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (1, 2, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (1, 3, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (1, 4, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (2, 1, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (2, 2, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (2, 3, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (2, 4, 0)
INSERT [dbo].[TorneosParticipadosXUsuario] ([IDUsuario], [IDTorneo], [Administrador]) VALUES (3, 1, NULL)
GO
SET IDENTITY_INSERT [dbo].[Usuarios] ON 

INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (1, N'DamianGluk', N'Dami123', CAST(N'2020-05-08' AS Date), N'damigluk@gmail.com', 10)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (2, N'Ale', N'ale123', CAST(N'2003-11-11' AS Date), N'ale@gmail.com', 20)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (3, N'gabo', N'gabo123', CAST(N'2004-11-11' AS Date), N'jojo@gmail.com', 2)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (4, N'dale', N'dale123', CAST(N'1999-11-11' AS Date), N'dale@gmail.com', 1)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (5, N'goldeboca', N'gol123', CAST(N'1990-12-10' AS Date), N'gol@gmail.com', 5)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (6, N'campeon', N'camp123', CAST(N'2000-12-10' AS Date), N'camp@gmail.com', 6)
INSERT [dbo].[Usuarios] ([IDUsuario], [NombreDeUsuario], [Contrasenia], [FechaDeNacimiento], [Email], [GolesEnTorneo]) VALUES (7, N'fallguys', N'fallguys123', CAST(N'2020-10-08' AS Date), N'fall@gmail.com', 0)
SET IDENTITY_INSERT [dbo].[Usuarios] OFF
GO
ALTER TABLE [dbo].[Equipos]  WITH CHECK ADD  CONSTRAINT [FK_Equipos_Torneos] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[Equipos] CHECK CONSTRAINT [FK_Equipos_Torneos]
GO
ALTER TABLE [dbo].[Fotos]  WITH CHECK ADD  CONSTRAINT [FK_Fotos_Torneos1] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[Fotos] CHECK CONSTRAINT [FK_Fotos_Torneos1]
GO
ALTER TABLE [dbo].[GolesXUsuarioXPartidos]  WITH CHECK ADD  CONSTRAINT [FK_GolesXPartidos_Partidos] FOREIGN KEY([IDPartido])
REFERENCES [dbo].[Partidos] ([IDPartido])
GO
ALTER TABLE [dbo].[GolesXUsuarioXPartidos] CHECK CONSTRAINT [FK_GolesXPartidos_Partidos]
GO
ALTER TABLE [dbo].[GolesXUsuarioXPartidos]  WITH CHECK ADD  CONSTRAINT [FK_GolesXPartidos_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[GolesXUsuarioXPartidos] CHECK CONSTRAINT [FK_GolesXPartidos_Usuarios]
GO
ALTER TABLE [dbo].[JugadoresXEquipos]  WITH CHECK ADD  CONSTRAINT [FK_JugadoresXEquipos_Equipos] FOREIGN KEY([IDEquipo])
REFERENCES [dbo].[Equipos] ([IDEquipo])
GO
ALTER TABLE [dbo].[JugadoresXEquipos] CHECK CONSTRAINT [FK_JugadoresXEquipos_Equipos]
GO
ALTER TABLE [dbo].[JugadoresXEquipos]  WITH CHECK ADD  CONSTRAINT [FK_JugadoresXEquipos_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[JugadoresXEquipos] CHECK CONSTRAINT [FK_JugadoresXEquipos_Usuarios]
GO
ALTER TABLE [dbo].[ListaDeEsperaParaTorneo]  WITH CHECK ADD  CONSTRAINT [FK_ListaDeEsperaParaTorneo_Torneos] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[ListaDeEsperaParaTorneo] CHECK CONSTRAINT [FK_ListaDeEsperaParaTorneo_Torneos]
GO
ALTER TABLE [dbo].[ListaDeEsperaParaTorneo]  WITH CHECK ADD  CONSTRAINT [FK_ListaDeEsperaParaTorneo_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[ListaDeEsperaParaTorneo] CHECK CONSTRAINT [FK_ListaDeEsperaParaTorneo_Usuarios]
GO
ALTER TABLE [dbo].[Noticias]  WITH CHECK ADD  CONSTRAINT [FK_Noticias_Fotos] FOREIGN KEY([IDFoto])
REFERENCES [dbo].[Fotos] ([IDFoto])
GO
ALTER TABLE [dbo].[Noticias] CHECK CONSTRAINT [FK_Noticias_Fotos]
GO
ALTER TABLE [dbo].[Noticias]  WITH CHECK ADD  CONSTRAINT [FK_Noticias_Torneos] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[Noticias] CHECK CONSTRAINT [FK_Noticias_Torneos]
GO
ALTER TABLE [dbo].[Partidos]  WITH CHECK ADD  CONSTRAINT [FK_Partidos_Torneos] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[Partidos] CHECK CONSTRAINT [FK_Partidos_Torneos]
GO
ALTER TABLE [dbo].[SeguidoresXTorneos]  WITH CHECK ADD  CONSTRAINT [FK_SeguidoresXTorneos_Equipos] FOREIGN KEY([IDEquipoFavorito])
REFERENCES [dbo].[Equipos] ([IDEquipo])
GO
ALTER TABLE [dbo].[SeguidoresXTorneos] CHECK CONSTRAINT [FK_SeguidoresXTorneos_Equipos]
GO
ALTER TABLE [dbo].[SeguidoresXTorneos]  WITH CHECK ADD  CONSTRAINT [FK_SeguidoresXTorneos_Torneos] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[SeguidoresXTorneos] CHECK CONSTRAINT [FK_SeguidoresXTorneos_Torneos]
GO
ALTER TABLE [dbo].[SeguidoresXTorneos]  WITH CHECK ADD  CONSTRAINT [FK_SeguidoresXTorneos_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[SeguidoresXTorneos] CHECK CONSTRAINT [FK_SeguidoresXTorneos_Usuarios]
GO
ALTER TABLE [dbo].[TorneosParticipadosXUsuario]  WITH CHECK ADD  CONSTRAINT [FK_TorneosParticipadosXUsuario_Torneos1] FOREIGN KEY([IDTorneo])
REFERENCES [dbo].[Torneos] ([IDTorneo])
GO
ALTER TABLE [dbo].[TorneosParticipadosXUsuario] CHECK CONSTRAINT [FK_TorneosParticipadosXUsuario_Torneos1]
GO
ALTER TABLE [dbo].[TorneosParticipadosXUsuario]  WITH CHECK ADD  CONSTRAINT [FK_TorneosParticipadosXUsuario_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[TorneosParticipadosXUsuario] CHECK CONSTRAINT [FK_TorneosParticipadosXUsuario_Usuarios]
GO
USE [master]
GO
ALTER DATABASE [OurTournament] SET  READ_WRITE 
GO
