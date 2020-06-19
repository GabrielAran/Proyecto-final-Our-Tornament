package com.example.ourtournament.TablaGoleadores;

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

public class ListaGoleadores extends BaseAdapter
{
    private ArrayList<String> _listaJugadores;
    private ArrayList<String> _listaEquipos;
    private ArrayList<String> _listaGoles;
    private Context context;

    public ListaGoleadores(ArrayList<String> ListaJugadores, ArrayList<String> ListaEquipos, ArrayList<String> ListaGoles, Context contexto)
    {
        Log.d("Conexion", "llegue al adaptador");
        _listaJugadores = ListaJugadores;
        _listaEquipos = ListaEquipos;
        _listaGoles = ListaGoles;
        context = contexto;
    }

    public int getCount()
    {
        return _listaJugadores.size();
    }

    public ArrayList<String> getItem(int pos)
    {
        ArrayList<String> Goleadores;
        Goleadores = new ArrayList<String>();

        String jugadores;
        jugadores = _listaJugadores.get(pos);
        Goleadores.add(jugadores);

        String equipos;
        equipos = _listaEquipos.get(pos);
        Goleadores.add(equipos);

        String goles;
        goles = _listaGoles.get(pos);
        Goleadores.add(goles);

        return Goleadores;
    }

    public long getItemId(int pos)
    {
        return pos;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        TextView pos1, jug1, eqp1, Goles;
        int posj = pos + 1;

        ArrayList<String> ListaDeLaPosicionActual;
        
        LayoutInflater MiInflador;
        MiInflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver = MiInflador.inflate(R.layout.item_tabla_goleadores,GrupoActual,false);

        pos1 = VistaADevolver.findViewById(R.id.pos1);
        jug1 = VistaADevolver.findViewById(R.id.jug1);
        eqp1 = VistaADevolver.findViewById(R.id.eqp1);
        Goles = VistaADevolver.findViewById(R.id.Goles);

        ListaDeLaPosicionActual = getItem(pos);

        Log.d("Gabi", "pos es: " + pos);
        pos1.setText("" + posj);
        jug1.setText(ListaDeLaPosicionActual.get(0));
        eqp1.setText(ListaDeLaPosicionActual.get(1));
        Goles.setText(ListaDeLaPosicionActual.get(2));

        return  VistaADevolver;
    }
}
