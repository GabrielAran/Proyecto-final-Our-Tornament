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
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorListaNoticias extends BaseAdapter
{
    private ArrayList<Noticia> _Noticias;
    private Context _Contexto;

    public AdaptadorListaNoticias(ArrayList<Noticia> Noticias, Context contexto)
    {
        _Noticias = Noticias;
        _Contexto = contexto;
    }
    public int getCount()
    {
        return _Noticias.size();
    }

    public Noticia getItem(int pos)
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
        TextView Titulo,Fecha,Descripcion;
        ImageView Destacada,imageView;
        Noticia Not;


        LayoutInflater MiInflador;
        MiInflador = (LayoutInflater)_Contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=MiInflador.inflate(R.layout.item_lista_noticias,GrupoActual,false);
        Titulo=VistaADevolver.findViewById(R.id.Titulo);
        Fecha=VistaADevolver.findViewById(R.id.Fecha);
        Descripcion=VistaADevolver.findViewById(R.id.Descripcion);
        Destacada=VistaADevolver.findViewById(R.id.Destacada);
        imageView =VistaADevolver.findViewById(R.id.imageView);

        Not = getItem(pos);
        if(pos != 0)
        {
            Destacada.setVisibility(View.INVISIBLE);
        }else
        {
        }
        Titulo.setText(Not.Titulo);
        Fecha.setText(Not.Fecha.toString());
        Descripcion.setText(Not.Descripcion);
        String Ruta = "https://upload.wikimedia.org/wikipedia/commons/b/b9/Football_iu_1996.jpg";
        Picasso.get().load(Ruta).into(imageView);
        return  VistaADevolver;
    }
}
