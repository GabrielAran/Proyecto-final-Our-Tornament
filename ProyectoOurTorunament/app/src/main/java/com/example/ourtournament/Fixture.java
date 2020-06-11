package com.example.ourtournament;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourtournament.ListaPartidos;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Fixture extends AppCompatActivity {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    ArrayList<String> Jornadas;
    Spinner LasJornadas;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Equipo1;
    ArrayList<String> Equipo2;

    String Jornada;
    Button Fixture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixture);
        Fixture = findViewById(R.id.Fixture);
        AdminFragments=getFragmentManager();

        Jornadas = new ArrayList<>();
        LasJornadas = findViewById(R.id.Jornadas);
        Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Jornadas);
        Jornadas.add("Jornada 1");
        Jornadas.add("Jornada 2");
        Jornadas.add("Jornada 3");
        Jornadas.add("Jornada 4");
        Jornadas.add("Jornada 5");
        Jornadas.add("Jornada 6");
        LasJornadas.setAdapter(Adaptador);

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

        MostrarListaPartidos();
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

    public void MostrarListaPartidos()
    {
        FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame, lista);
        TransaccionesDeFragment.commit();
    }
    public ArrayList<String> getEquipos1() {return Equipo1;}
    public ArrayList<String> getEquipos2() {return Equipo2;}
}
