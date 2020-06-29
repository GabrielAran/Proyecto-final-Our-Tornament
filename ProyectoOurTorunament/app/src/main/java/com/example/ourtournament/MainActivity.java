package com.example.ourtournament;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.Fixture.Partido;
import com.example.ourtournament.Inicio.Inicio;
import com.example.ourtournament.Loguearse.Loguear;
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.TablaGoleadores.TablaDeGoleadores;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;


    static SharedPreferences DatosGenerales;

    int IDTorneo=1;
    Fixture fixture;
    TablaDeGoleadores tablaDeGoleadores;
    Inicio inicio;
    Button BTNFixture;
    Button BTNTablaDePosiciones;
    Button BTNInicio;
    Button BTNTablaDeGoleadores;
    Button BTNAdministracion;
    //fixture
    ArrayList<String> ListaJornadas;
    ArrayAdapter<String> Adaptador;
    ArrayAdapter<String> Adaptador2;
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

    //Inicio

    ArrayList<Noticia> ListaNoticias;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_de_carga);
        AdminFragments=getFragmentManager();

        DatosGenerales= getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=DatosGenerales.edit();
        editor.putString("NombreDeUsuario", "");
        editor.apply();
        String Nombre = DatosGenerales.getString("NombreDeUsuario", "" );
        if(Nombre=="")
        {
            setContentView(R.layout.pantalla_vacia_con_fragment);
            Loguear logueo = new Loguear();
            TransaccionesDeFragment=AdminFragments.beginTransaction();
            TransaccionesDeFragment.replace(R.id.fragmentodepantallacompleta,logueo);
            TransaccionesDeFragment.commit();
            TransaccionesDeFragment.addToBackStack(null);
        }else
        {
            setContentView(R.layout.activity_main);
            CargarGeneral();
            CargarInicio();
            CargarFixture();
            CargarTabla();
        }

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

        setContentView(R.layout.activity_main);
        inicio = new Inicio();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,inicio);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }
    //Fixture
    public void CargarFixture()
    {
        Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaJornadas);
        ListaJornadas = new ArrayList<>();
        ListaJornadas.add(0,"Jornadas");

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
        Partido partido = new Partido();
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
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
        Volver = true;
    }
    //Tabla de goleadores
    public void CargarTabla()
    {
        Adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaJornadas);
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

    //Navegacion

    public void IrAFixture(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture_verde);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        fixture = new Fixture();
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
        tablaDeGoleadores = new TablaDeGoleadores();
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
        inicio = new Inicio();
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
