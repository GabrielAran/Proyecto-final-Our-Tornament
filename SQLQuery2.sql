USE [master]
GO
/****** Object:  Database [OurTorunament]    Script Date: 05/06/2020 9:53:28 ******/
CREATE DATABASE [OurTorunament]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'OurTorunament', FILENAME = N'C:\Users\Damian\Desktop\SQL\MSSQL14.MSSQLSERVER01\MSSQL\DATA\OurTorunament.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'OurTorunament_log', FILENAME = N'C:\Users\Damian\Desktop\SQL\MSSQL14.MSSQLSERVER01\MSSQL\DATA\OurTorunament_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [OurTorunament] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [OurTorunament].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [OurTorunament] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [OurTorunament] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [OurTorunament] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [OurTorunament] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [OurTorunament] SET ARITHABORT OFF 
GO
ALTER DATABASE [OurTorunament] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [OurTorunament] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [OurTorunament] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [OurTorunament] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [OurTorunament] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [OurTorunament] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [OurTorunament] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [OurTorunament] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [OurTorunament] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [OurTorunament] SET  DISABLE_BROKER 
GO
ALTER DATABASE [OurTorunament] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [OurTorunament] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [OurTorunament] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [OurTorunament] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [OurTorunament] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [OurTorunament] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [OurTorunament] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [OurTorunament] SET RECOVERY FULL 
GO
ALTER DATABASE [OurTorunament] SET  MULTI_USER 
GO
ALTER DATABASE [OurTorunament] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [OurTorunament] SET DB_CHAINING OFF 
GO
ALTER DATABASE [OurTorunament] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [OurTorunament] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [OurTorunament] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'OurTorunament', N'ON'
GO
ALTER DATABASE [OurTorunament] SET QUERY_STORE = OFF
GO
USE [OurTorunament]
GO
/****** Object:  Table [dbo].[Equipos]    Script Date: 05/06/2020 9:53:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Equipos](
	[IDEquipo] [int] IDENTITY(1,1) NOT NULL,
	[NombreEquipo] [varchar](20) NULL,
	[IDTorneo] [int] NULL,
 CONSTRAINT [PK_Equipos] PRIMARY KEY CLUSTERED 
(
	[IDEquipo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Fotos]    Script Date: 05/06/2020 9:53:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Fotos](
	[IDFoto] [int] IDENTITY(1,1) NOT NULL,
	[Direccion] [varchar](40) NULL,
	[IDTorneo] [int] NULL,
 CONSTRAINT [PK_Fotos] PRIMARY KEY CLUSTERED 
(
	[IDFoto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GolesXPartidos]    Script Date: 05/06/2020 9:53:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GolesXPartidos](
	[IDUsuario] [int] NOT NULL,
	[IDPartido] [int] NOT NULL,
 CONSTRAINT [PK_GolesXPartidos] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC,
	[IDPartido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ListaDeEsperaParaTorneo]    Script Date: 05/06/2020 9:53:28 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Noticias]    Script Date: 05/06/2020 9:53:28 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Partidos]    Script Date: 05/06/2020 9:53:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Partidos](
	[IDPartido] [int] IDENTITY(1,1) NOT NULL,
	[FechaDeEncuentro] [date] NULL,
	[JornadaDelTorneo] [int] NULL,
	[IDEquipoLocal] [int] NULL,
	[IDEquipoVisitante] [int] NULL,
	[GolesLocal] [int] NULL,
	[GolesVisitante] [int] NULL,
	[IDTorneo] [int] NULL,
 CONSTRAINT [PK_Partidos] PRIMARY KEY CLUSTERED 
(
	[IDPartido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SeguidoresXTorneos]    Script Date: 05/06/2020 9:53:28 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Torneos]    Script Date: 05/06/2020 9:53:28 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TorneosParticipadosXUsuario]    Script Date: 05/06/2020 9:53:28 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 05/06/2020 9:53:28 ******/
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
	[IDEquipo] [int] NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[IDUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
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
ALTER TABLE [dbo].[GolesXPartidos]  WITH CHECK ADD  CONSTRAINT [FK_GolesXPartidos_Partidos] FOREIGN KEY([IDPartido])
REFERENCES [dbo].[Partidos] ([IDPartido])
GO
ALTER TABLE [dbo].[GolesXPartidos] CHECK CONSTRAINT [FK_GolesXPartidos_Partidos]
GO
ALTER TABLE [dbo].[GolesXPartidos]  WITH CHECK ADD  CONSTRAINT [FK_GolesXPartidos_Usuarios] FOREIGN KEY([IDUsuario])
REFERENCES [dbo].[Usuarios] ([IDUsuario])
GO
ALTER TABLE [dbo].[GolesXPartidos] CHECK CONSTRAINT [FK_GolesXPartidos_Usuarios]
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
ALTER TABLE [dbo].[Partidos]  WITH CHECK ADD  CONSTRAINT [FK_Partidos_Equipos] FOREIGN KEY([IDEquipoLocal])
REFERENCES [dbo].[Equipos] ([IDEquipo])
GO
ALTER TABLE [dbo].[Partidos] CHECK CONSTRAINT [FK_Partidos_Equipos]
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
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Equipos] FOREIGN KEY([IDEquipo])
REFERENCES [dbo].[Equipos] ([IDEquipo])
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_Equipos]
GO
USE [master]
GO
ALTER DATABASE [OurTorunament] SET  READ_WRITE 
GO
