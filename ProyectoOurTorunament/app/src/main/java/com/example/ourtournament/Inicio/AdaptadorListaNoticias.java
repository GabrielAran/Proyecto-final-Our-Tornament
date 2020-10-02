package com.example.ourtournament.Inicio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdaptadorListaNoticias extends ArrayAdapter<Noticia>
{
    private ArrayList<Noticia> _Noticias;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaNoticias(Context contexto, int Resource,ArrayList<Noticia> Noticias)
    {
        super(contexto,Resource,Noticias);
        this._Contexto = contexto;
        this._Resource = Resource;
        this._Noticias = Noticias;
    }
    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver = VistaActual;
        LayoutInflater MiInflador;
        if (VistaActual == null) {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource, null);
        }

        Log.d("conexion","Entre al getview");
        TextView Titulo,Fecha,Descripcion;
        ImageView Destacada,imageView;
        Noticia Not;

        Titulo=VistaADevolver.findViewById(R.id.Titulo);
        Fecha=VistaADevolver.findViewById(R.id.Fecha);
        Descripcion=VistaADevolver.findViewById(R.id.Descripcion);
        Destacada=VistaADevolver.findViewById(R.id.Destacada);
        imageView =VistaADevolver.findViewById(R.id.imageView);

        Not = getItem(pos);
        if(Not.Destacada)
        {
            Destacada.setVisibility(View.VISIBLE);
        }else
        {
            Destacada.setVisibility(View.INVISIBLE);
        }
        Titulo.setText(Not.Titulo);
        Fecha.setText(Not.Fecha.toString());
        Descripcion.setText(Not.Descripcion);

        if (pos%2 ==0)
        {
            String Ruta = "https://img.fifa.com/image/upload/t_s3/mk7m1dqgxma3pgrdkbem.jpg";
            Picasso.get().load(Ruta).into(imageView);
        }else
        {
            String Ruta = "https://upload.wikimedia.org/wikipedia/commons/b/b9/Football_iu_1996.jpg";
            Picasso.get().load(Ruta).into(imageView);
        }
        return  VistaADevolver;
    }
}
