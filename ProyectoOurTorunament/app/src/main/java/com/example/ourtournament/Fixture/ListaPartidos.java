package com.example.ourtournament.Fixture;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ourtournament.R;

import java.util.ArrayList;

public class ListaPartidos extends BaseAdapter
{
    private ArrayList<String> _MilistaDeEquipos1;
    private ArrayList<String> _MilistaDeEquipos2;
    private Context _Contexto;

    public ListaPartidos(ArrayList<String> ListaEquipos1, ArrayList<String> ListaEquipos2,Context contexto)
    {
        _MilistaDeEquipos1 = ListaEquipos1;
        _MilistaDeEquipos2= ListaEquipos2;
        _Contexto = contexto;
    }
    public int getCount()
    {
        return _MilistaDeEquipos1.size();
    }

    public ArrayList<String> getItem(int pos)
    {
        ArrayList<String> Partido;
        Partido = new ArrayList<String>();

        String Equipo1;
        Equipo1=_MilistaDeEquipos1.get(pos);
        Partido.add(Equipo1);

        String Equipo2;
        Equipo2=_MilistaDeEquipos2.get(pos);
        Partido.add(Equipo2);

        return Partido;
    }

    public long getItemId(int pos)
    {
        return pos;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        TextView Equipo1,Equipo2,renglon;

        ArrayList<String> ListaDeLaPosicionActual;


        LayoutInflater MiInflador;
        MiInflador = (LayoutInflater)_Contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=MiInflador.inflate(R.layout.item_lista_partidos,GrupoActual,false);

        Equipo1=VistaADevolver.findViewById(R.id.Equipo1);
        Equipo2=VistaADevolver.findViewById(R.id.Equipo2);
        renglon=VistaADevolver.findViewById(R.id.renglonArriba);

        ListaDeLaPosicionActual = getItem(pos);
        if(pos == 0)
        {
            renglon.setVisibility(View.GONE);
        }

        Equipo1.setText(ListaDeLaPosicionActual.get(0));
        Equipo2.setText(ListaDeLaPosicionActual.get(1));

        return  VistaADevolver;
    }
}
