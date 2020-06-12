package com.example.ourtournament;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    //fixture
    ArrayList<String> Jornadas;
    ArrayAdapter<String> Adaptador;
    ArrayList<String> Equipo1;
    ArrayList<String> Equipo2;
    String Jornada;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdminFragments=getFragmentManager();

        CargarFixture();
    }

    public void CargarFixture()
    {
        Adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Jornadas);
        Jornadas = new ArrayList<>();
        Jornadas.add("Jornada 1");
        Jornadas.add("Jornada 2");
        Jornadas.add("Jornada 3");
        Jornadas.add("Jornada 4");
        Jornadas.add("Jornada 5");
        Jornadas.add("Jornada 6");

        Equipo1 = new ArrayList<>();
        Equipo1.add("San Lorenzo");
        Equipo1.add("Boca Juniors");
        Equipo1.add("River Plate");
        Equipo1.add("Independiente");
        Equipo1.add("Racing");

        Equipo2 = new ArrayList<>();
        Equipo2.add("San Lorenzo");
        Equipo2.add("Boca Juniors");
        Equipo2.add("River Plate");
        Equipo2.add("Independiente");
        Equipo2.add("Racing");
    }
    public void PartidoSeleccionado(String Equipo1,String Equipo2)
    {
        Jornada = LasJornadas.getSelectedItem().toString();
        Intent LLamada;
        LLamada = new Intent(this,Partido.class);
        Bundle paquete = new Bundle();
        paquete.putString("Jornada", Jornada);
        paquete.putString("Equipo1", Equipo1);
        paquete.putString("Equipo2", Equipo2);
        LLamada.putExtras(paquete);
        startActivity(LLamada);
    }
    public ArrayAdapter<String> getAdaptador() {return Adaptador;}
    public ArrayList<String> getJornadas() {return Jornadas;}
    public ArrayList<String> getEquipos1() {return Equipo1;}
    public ArrayList<String> getEquipos2() {return Equipo2;}
    public void IrAFixture(View vista)
    {
        FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame, lista);
        TransaccionesDeFragment.commit();
    }
}
