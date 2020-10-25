package com.example.ourtournament.Administracion.Torneos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorTorneos extends ArrayAdapter<Torneo> {
    private ArrayList<Torneo> _ListaTorneos;
    private Context _Contexto;
    private int _Resource;
    private int _IDUsuario;
    MainActivity _Principal;

    public AdaptadorTorneos(Context contexto, int Resource, ArrayList<Torneo> ListaTorneos,int IDUsuario,MainActivity Principal) {
        super(contexto, Resource, ListaTorneos);
        this._ListaTorneos = ListaTorneos;
        this._Contexto = contexto;
        this._Resource = Resource;
        this._IDUsuario = IDUsuario;
        _Principal = Principal;
    }

    @SuppressLint("ViewHolder")
    public View getView(final int pos, View VistaADevolver, ViewGroup GrupoActual) {
        LayoutInflater MiInflador;
        final ConstraintLayout Item;
        if (VistaADevolver == null) {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource, null);
        }

        Item = VistaADevolver.findViewById(R.id.item);
        Button Administrar = VistaADevolver.findViewById(R.id.Administrar);
        Button Eliminar = VistaADevolver.findViewById(R.id.Eliminar);
        EditText Contra = VistaADevolver.findViewById(R.id.Contra);
        final CircleImageView FotoPerfil = VistaADevolver.findViewById(R.id.PerfilTorneo);
        TextView NombreTorneo = VistaADevolver.findViewById(R.id.Torneo);
        final boolean[] Admnistrando = {false};

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

        final EditText finalContra = Contra;
        Administrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdministrarTorneo VT = new AdministrarTorneo();
                VT.setTorneoElegido(T);
                _Principal.IrAFragment(VT,true);
                /*
                if (!Admnistrando[0])
                {
                    finalContra.setVisibility(View.VISIBLE);
                    Admnistrando[0]=true;
                }else if (Admnistrando[0])
                {
                    finalContra.setVisibility(View.GONE);
                    Admnistrando[0]=false;
                }

                 */
            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarTorneoSeguido Tarea = new EliminarTorneoSeguido();
                Tarea.execute(_IDUsuario,T.IDTorneo);
                _ListaTorneos.remove(pos);
                notifyDataSetChanged();
            }
        });

        if (T.NombreTorneo == "No sigues ningun torneo")
        {
            Administrar.setVisibility(View.GONE);
            Eliminar.setVisibility(View.GONE);
            FotoPerfil.setVisibility(View.GONE);
            Item.setBackgroundColor(Color.rgb(33,36,35));
            NombreTorneo.setTextColor(Color.rgb(150,150,150));
            NombreTorneo.setTextSize(16);
            NombreTorneo.setGravity(View.SCROLL_INDICATOR_RIGHT);
        }

        return VistaADevolver;
    }


    private class EliminarTorneoSeguido extends AsyncTask<Integer, Void, Boolean> {
        Boolean Resultado;
        @Override
        protected Boolean doInBackground(Integer... IDS) {
            try {
                String miURL = "http://10.0.2.2:55859/api/DeleteTorneosSeguidos";
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setDoInput(true);
                miConexion.setDoOutput(true);
                miConexion.setRequestProperty("Content-Type", "application/json");
                miConexion.setRequestProperty("Accept", "application/json");
                miConexion.setRequestMethod("DELETE");

                Gson gson = new Gson();
                String json = gson.toJson(IDS);
                OutputStream OS = miConexion.getOutputStream();
                OS.write(json.getBytes());
                OS.flush();

                int ResponseCode = miConexion.getResponseCode();
                Log.d("conexion", String.valueOf(ResponseCode));

                InputStream lector = miConexion.getInputStream();
                InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                JsonParser parseador = new JsonParser();
                Resultado = parseador.parse(lectorJSon).getAsBoolean();
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return Resultado;
        }

        protected void onPostExecute(Boolean R) { }
    }

}
