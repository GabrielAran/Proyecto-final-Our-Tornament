package com.example.ourtournament;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

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
    ArrayList<String> ListaJugador;
    ArrayList<String> ListaGoles;
    String JornadaElegida;
    String EquipoElegido1;
    String EquipoElegido2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdminFragments=getFragmentManager();
        CargarGeneral();
        CargarInicio();
        CargarFixture();
    }

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
        Inicio inicio = new Inicio();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,inicio);
        TransaccionesDeFragment.commit();
    }
    //Fixture
    public void CargarFixture()
    {
        Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaJornadas);
        ListaJornadas = new ArrayList<>();
        ListaJornadas.add(0,"Jornadas");
        ListaJornadas.add("Jornada 1");
        ListaJornadas.add("Jornada 2");
        ListaJornadas.add("Jornada 3");
        ListaJornadas.add("Jornada 4");
        ListaJornadas.add("Jornada 5");

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
    public void CargarTabla()
    {
        Adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaJornadas);
        ListaJugador = new ArrayList<>();
        ListaJornadas.add(0,"Jornadas");
        ListaJornadas.add("Bochini");
        ListaJornadas.add("Maradona");
        ListaJornadas.add("Pumpido");
        ListaJornadas.add("Batistuta");
        ListaJornadas.add("Messi");

        ListaEquipos1 = new ArrayList<>();
        ListaEquipos1.add("San Lorenzo");
        ListaEquipos1.add("Boca Juniors");
        ListaEquipos1.add("River Plate");
        ListaEquipos1.add("Independiente");
        ListaEquipos1.add("Racing");

        ListaGoles = new ArrayList<>();
        ListaEquipos2.add("3");
        ListaEquipos2.add("10");
        ListaEquipos2.add("18");
        ListaEquipos2.add("8");
        ListaEquipos2.add("6");
    }
    public void PartidoSeleccionado(int i)
    {
        EquipoElegido1 = ListaEquipos1.get(i);
        EquipoElegido2 = ListaEquipos2.get(i);
        Partido partido = new Partido();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,partido);
        TransaccionesDeFragment.commit();
    }
    public void SetJornadaElegida(int i)
    {
        Log.d("conexion", JornadaElegida + "va a ser " +ListaJornadas.get(i));
        JornadaElegida = ListaJornadas.get(i);

    }
    public String GetJornadaElegida()
    {
        Log.d("conexion", "devuelvo "+JornadaElegida);
        return JornadaElegida;
    }
    public String GetEquipoElegido1(){return EquipoElegido1;}
    public String GetEquipoElegido2(){return EquipoElegido2;}
    
    public ArrayList<String> getListaJornadas() {return ListaJornadas;}
    public ArrayList<String> getListaEquipos1() {return ListaEquipos1;}
    public ArrayList<String> getListaEquipos2() {return ListaEquipos2;}
    public void Volver(){
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
    }

    public void IrAFixture(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture_verde);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_inicio);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_inicio);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_inicio);
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
    }

    public void IrATablaGoleadores(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_inicio_verde);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_inicio);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_inicio);
        TablaDeGoleadores TablaGol = new TablaDeGoleadores();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,TablaGol);
        TransaccionesDeFragment.commit();
    }

    public void IrAInicio(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_inicio);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio_verde);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_inicio);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_inicio);
        Inicio incio = new Inicio();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,incio);
        TransaccionesDeFragment.commit();
    }
    public void IrATablaPosiciones(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_inicio);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_inicio_verde);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_inicio);
    }
    public void IrAAdministracion(View vista) {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_inicio);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_inicio);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_inicio_verde);
    }
}
