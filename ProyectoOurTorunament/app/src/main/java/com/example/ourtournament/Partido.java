package com.example.ourtournament;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Partido extends AppCompatActivity {
    TextView Jorn,E1,E2;

    ArrayList<String> Goles1;
    ListView LosGoles1;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Goles2;
    ListView LosGoles2;
    ArrayAdapter<String> Adaptador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_partido);

        Jorn = findViewById(R.id.Jornada);
        E1 = findViewById(R.id.Equipo1);
        E2 = findViewById(R.id.Equipo2);

        Bundle Paquete = this.getIntent().getExtras();
        String Jornada = Paquete.getString("Jornada");
        String Equipo1 = Paquete.getString("Equipo1");
        String Equipo2 = Paquete.getString("Equipo2");

        Jorn.setText(Jornada);
        E1.setText(Equipo1);
        E2.setText(Equipo2);

        Goles1 = new ArrayList<>();
        LosGoles1 = findViewById(R.id.ListaGolesE1);
        Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Goles1);
        Goles1.add("No hay goles aun");
        LosGoles1.setAdapter(Adaptador);

        Goles2 = new ArrayList<>();
        LosGoles2 = findViewById(R.id.ListaGolesE2);
        Adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Goles2);
        Goles2.add("No hay goles aun");
        LosGoles2.setAdapter(Adaptador2);
    }

    public void Volver(View vista)
    {
        Intent LLamada;
        LLamada = new Intent(this,Fixture.class);
        startActivity(LLamada);
    }
}
