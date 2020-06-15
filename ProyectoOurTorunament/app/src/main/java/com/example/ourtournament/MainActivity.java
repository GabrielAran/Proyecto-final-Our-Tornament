package com.example.ourtournament;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
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
    ArrayList<String> ListaEquipos1;
    ArrayList<String> ListaEquipos2;
    String JornadaElegida;
    String EquipoElegido1;
    String EquipoElegido2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdminFragments=getFragmentManager();
        CargarGeneral();
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
        ListaJornadas.add("Jornada 6");

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
    }
    public void SetJornada(int i){JornadaElegida = ListaJornadas.get(i);}
    public String GetJornadaElegida(){return JornadaElegida;}
    public String GetEquipoElegido1(){return EquipoElegido1;}
    public String GetEquipoElegido2(){return EquipoElegido2;}
    
    public ArrayList<String> getListaJornadas() {return ListaJornadas;}
    public ArrayList<String> getListaEquipos1() {return ListaEquipos1;}
    public ArrayList<String> getListaEquipos2() {return ListaEquipos2;}
    public void Volver(){
        BTNFixture.setTextSize(20);
        BTNFixture.setWidth(105);
        BTNInicio.setTextSize(12);
        BTNInicio.setWidth(80);
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
    }
    public void IrAFixture(View vista)
    {
        BTNFixture.setTextSize(16);
        BTNInicio.setTextSize(10);
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
    }
}
