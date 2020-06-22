package com.example.ourtournament.Inicio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class ListaNoticias extends BaseAdapter
{
    private ArrayList<MainActivity.Noticia> _Noticias;
    private Context _Contexto;

    public ListaNoticias(ArrayList<MainActivity.Noticia> Noticias, Context contexto)
    {
        Log.d("Conexion", "llegue al adaptador");
        _Noticias = Noticias;
        _Contexto = contexto;
    }
    public int getCount()
    {
        return _Noticias.size();
    }

    public MainActivity.Noticia getItem(int pos)
    {
        return _Noticias.get(pos);
    }

    public long getItemId(int pos)
    {
        return pos;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        TextView Titulo,Fecha;
        ImageView Destacada;
        MainActivity.Noticia Not;


        LayoutInflater MiInflador;
        MiInflador = (LayoutInflater)_Contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=MiInflador.inflate(R.layout.item_lista_noticias,GrupoActual,false);
        Titulo=VistaADevolver.findViewById(R.id.Titulo);
        Fecha=VistaADevolver.findViewById(R.id.Fecha);
        Destacada=VistaADevolver.findViewById(R.id.Destacada);


        Not = getItem(pos);
        if(pos != 0)
        {
            Destacada.setVisibility(View.INVISIBLE);
        }
        String a = "Boca le gana a river";
        Titulo.setText(a);
        String b = "06/08";
        Fecha.setText(b);
        return  VistaADevolver;
    }
}
