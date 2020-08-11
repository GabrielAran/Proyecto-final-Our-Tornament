package com.example.ourtournament;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.Fixture.MostrarPartido;
import com.example.ourtournament.Inicio.Inicio;
import com.example.ourtournament.Loguearse.Loguear;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.TablaGoleadores.TablaDeGoleadores;
import com.example.ourtournament.TablaPosiciones.TablaPosiciones;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Preferencias DatosGenerales;
    int CodeElegirFoto = 3,RequestCode;
    int IDTorneo=1;
    Button BTNFixture;
    Button BTNTablaDePosiciones;
    Button BTNInicio;
    Button BTNTablaDeGoleadores;
    Button BTNAdministracion;
    //fixture
    ArrayList<String> ListaJornadas;
    ArrayList<String> ListaEquipos1;
    ArrayList<String> ListaEquipos2;
    int IDJornadaElegida;
    String JornadaElegida;
    String EquipoElegido1;
    String EquipoElegido2;
    boolean Volver= false;

    //Tabla de Goleadores
    ArrayList<String> ListaJugador;
    ArrayList<String> ListaGoles;

    // Tabla de Posiciones
    ArrayList<Equipo> ListaPosiciones;
    //Inicio

    ArrayList<Noticia> ListaNoticias;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdminFragments=getFragmentManager();
        DatosGenerales = CargarSharedPreferences();
        DatosGenerales.EliminarDato("contrasenia");
        String Nombre = DatosGenerales.ObtenerString("contrasenia","no hay contrasenia");
        if(Nombre=="no hay contrasenia")
        {
            setContentView(R.layout.pantalla_vacia_con_fragment);
            Loguear logueo = new Loguear();
            TransaccionesDeFragment=AdminFragments.beginTransaction();
            TransaccionesDeFragment.replace(R.id.fragmentodepantallacompleta,logueo);
            TransaccionesDeFragment.commit();
            TransaccionesDeFragment.addToBackStack(null);
        }else
        {
            Entrar();
        }

    }
    public Preferencias CargarSharedPreferences()
    {
        SharedPreferences aux = getSharedPreferences("DatosGenerales",MODE_PRIVATE);
        SharedPreferences.Editor editor = null;
        Preferencias P = new Preferencias(aux,editor);
        return P;
    }
    public void Entrar()
    {
        setContentView(R.layout.activity_main);
        CargarGeneral();
        CargarInicio();
        CargarFixture();
        CargarTablaGoleadores();
        CargarTablaPosiciones();

    }
    //  GENERAL
    public int getIDTorneo(){return IDTorneo;}
    public void CargarGeneral()
    {
        BTNFixture = findViewById(R.id.Fixture);
        BTNTablaDePosiciones = findViewById(R.id.TablaDePosiciones);
        BTNInicio = findViewById(R.id.Inicio);
        BTNTablaDeGoleadores = findViewById(R.id.TablaDeGoleadores);
        BTNAdministracion = findViewById(R.id.Administracion);
    }

    public void PedirPermisoParaCarrete()
    {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestCode);
    }

    public Bitmap BuscarImagenEnCarrete(String Ubicacion)
    {
        Bitmap Imagen = null;
        try {
            Imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(Ubicacion));
        }catch (Exception Error)
        {
            Log.d("conexion", "Hay un error: "+Error);
        }
        return Imagen;
    }

    //Inicio
    public void CargarInicio()
    {
        int IDNoticia=1;
        String Torneo = "Europa league";
        String Titulo = "River le gana a boca";
        String Descripcion = "el partido se llevo a cabo en una maniana calurosa donde river convertia 2 goles y boca 0";
        Boolean Destacada = true;
        ImageView Foto = null;
        Date Fecha = new Date(2/2/2020);
        Noticia UnaNoticia = new Noticia(IDNoticia,Torneo,Titulo,Descripcion,Destacada,Foto,Fecha);
        ListaNoticias = new ArrayList<>();
        ListaNoticias.add(UnaNoticia);
        ListaNoticias.add(UnaNoticia);
        ListaNoticias.add(UnaNoticia);

        IrAInicio(null);
    }
    //Fixture
    public void CargarFixture()
    {
        ListaJornadas = new ArrayList<>();

        ListaEquipos1 = new ArrayList<>();
        ListaEquipos1.add("San Lorenzo");
        ListaEquipos1.add("Boca Juniors");
        ListaEquipos1.add("River Plate");
        ListaEquipos1.add("Independiente");
        ListaEquipos1.add("Racing");

        ListaEquipos2 = new ArrayList<>();
        ListaEquipos2.add("San Lorenzo");
        ListaEquipos2.add("Boca Juniors");
        ListaEquipos2.add("River Plate");
        ListaEquipos2.add("Independiente");
        ListaEquipos2.add("Racing");
    }

    public void PartidoSeleccionado(int i)
    {
        EquipoElegido1 = ListaEquipos1.get(i);
        EquipoElegido2 = ListaEquipos2.get(i);
        MostrarPartido partido = new MostrarPartido();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,partido);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    public void SetJornadaElegida(int i)
    {
        JornadaElegida = ListaJornadas.get(i);
        IDJornadaElegida = i;
    }
    public void SetListaJornadas(ArrayList<String> J)
    {
        ListaJornadas =J;
    }
    public String GetJornadaElegida()
    {
        return JornadaElegida;
    }

    public String GetEquipoElegido1(){return EquipoElegido1;}
    public String GetEquipoElegido2(){return EquipoElegido2;}
    
    public ArrayList<String> getListaJornadas() {return ListaJornadas;}
    public ArrayList<String> getListaEquipos1() {return ListaEquipos1;}
    public ArrayList<String> getListaEquipos2() {return ListaEquipos2;}
    public boolean getVolver(){return Volver;};

    public void Volver(){
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
        Volver = true;
    }
    //Tabla de goleadores
    public void CargarTablaGoleadores()
    {
        ListaJugador = new ArrayList<>();
        ListaJugador.add("Bochini");
        ListaJugador.add("Maradona");
        ListaJugador.add("Pumpido");
        ListaJugador.add("Batistuta");
        ListaJugador.add("Messi");

        ListaGoles = new ArrayList<>();
        ListaGoles.add("10");
        ListaGoles.add("8");
        ListaGoles.add("7");
        ListaGoles.add("3");
        ListaGoles.add("1");
    }

    public ArrayList<String> getListaJugador() {return ListaJugador;}
    public ArrayList<String> getListaGoles() {return ListaGoles;}

    //TablaPosiciones
    public void CargarTablaPosiciones()
    {
        ListaPosiciones = new ArrayList<>();
        Equipo E = new Equipo(1,"Boca",3,9,8,4,1);
        Equipo A = new Equipo(1,"River",3,9,8,4,1);
        ListaPosiciones.add(E);
        ListaPosiciones.add(A);
        ListaPosiciones.add(E);
        ListaPosiciones.add(A);
        ListaPosiciones.add(E);
    }
    public ArrayList<Equipo> getListaPosiciones(){return ListaPosiciones;}

    //Navegacion
    public void IrAFixture(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture_verde);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    public void IrATablaGoleadores(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores_verde);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        TablaDeGoleadores tablaDeGoleadores = new TablaDeGoleadores();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,tablaDeGoleadores);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    public void IrAInicio(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio_verde);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        Inicio inicio = new Inicio();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,inicio);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    public void IrATablaPosiciones(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones_verde);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        TablaPosiciones tabladeposiciones = new TablaPosiciones();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,tabladeposiciones);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    public void IrAAdministracion(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin_verde);
    }
    //Inicio
    public ArrayList<Noticia> getNoticias(){return ListaNoticias;}

}
