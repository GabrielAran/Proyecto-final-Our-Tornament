package com.example.ourtournament.Administracion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorTorneos extends ArrayAdapter<TorneoSeguido> {
    private ArrayList<TorneoSeguido> _ListaTorneos;
    private Context _Contexto;
    private int _Resource;
    private int _IDTorneo;

    public AdaptadorTorneos(Context contexto, int Resource, ArrayList<TorneoSeguido> ListaTorneos) {
        super(contexto, Resource, ListaTorneos);
        this._ListaTorneos = ListaTorneos;
        this._Contexto = contexto;
        this._Resource = Resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(final int pos, View VistaADevolver, ViewGroup GrupoActual) {
        LayoutInflater MiInflador;
        if (VistaADevolver == null) {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource, null);
        }
        final CircleImageView FotoPerfil;
        TextView NombreTorneo;

        FotoPerfil = VistaADevolver.findViewById(R.id.PerfilTorneo);
        NombreTorneo = VistaADevolver.findViewById(R.id.Torneo);
        final Torneo T = getItem(pos);

        String Ruta = "http://10.0.2.2:55859/Imagenes/Torneos/ID" + T.IDTorneo + "_Perfil.JPG";
        Picasso.get().load(Ruta)
                .into(FotoPerfil, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        FotoPerfil.setImageResource(R.drawable.icono_torneo);
                    }

                });
        NombreTorneo.setText(T.NombreTorneo);

        return VistaADevolver;
    }
}
