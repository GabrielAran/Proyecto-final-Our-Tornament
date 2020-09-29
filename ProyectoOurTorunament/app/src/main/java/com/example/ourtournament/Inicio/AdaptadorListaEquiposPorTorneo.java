package com.example.ourtournament.Inicio;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdaptadorListaEquiposPorTorneo extends ArrayAdapter<Equipo>
{
    private ArrayList<Equipo> _ListaEquipos;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaEquiposPorTorneo(Context contexto,int Resource,ArrayList<Equipo> lista)
    {
        super(contexto,Resource,lista);
        this._ListaEquipos = lista;
        this._Contexto = contexto;
        this._Resource = Resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaADevolver, ViewGroup GrupoActual)
    {
        final ImageView[] Foto = new ImageView[1];
        final Button Destacada;
        TextView NombreEquipo,Renglon;
        LayoutInflater MiInflador;
        if(VistaADevolver == null)
        {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource,null);
        }

        NombreEquipo = VistaADevolver.findViewById(R.id.Nombre);
        Renglon = VistaADevolver.findViewById(R.id.renglon);
        Foto[0] = VistaADevolver.findViewById(R.id.foto);
        Destacada = VistaADevolver.findViewById(R.id.Destacada);

        if(pos!= 0)
        {
            Renglon.setVisibility(View.GONE);
        }
        Equipo E = getItem(pos);
        NombreEquipo.setText(E.Nombre);
        String Ruta = "https://as.com/futbol/imagenes/2019/08/28/videos/1567005433_173028_1567005806_noticia_normal.jpg";
        Picasso.get().load(Ruta).into(Foto[0]);
        final Boolean[] Destacado = {false};
        Destacada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Destacado[0] == false)
                {
                    Destacada.setBackgroundResource(R.drawable.estrella_equipo_favorito);
                    Destacado[0] =true;
                }else
                {
                    Destacada.setBackgroundResource(R.drawable.estrella_equipos);
                    Destacado[0] =false;
                }
            }
        });

        return  VistaADevolver;
    }


}
