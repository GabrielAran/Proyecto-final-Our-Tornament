package com.example.ourtournament.Inicio;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.example.ourtournament.Objetos.Equipo;
        import com.example.ourtournament.Objetos.Torneo;
        import com.example.ourtournament.R;

        import java.util.ArrayList;

        import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorListaTorneos extends ArrayAdapter<Torneo>
{
    private ArrayList<Torneo> _ListaTorneos;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaTorneos(Context contexto,int Resource,ArrayList<Torneo> ListaTorneos)
    {
        super(contexto,Resource,ListaTorneos);
        this._ListaTorneos = ListaTorneos;
        this._Contexto = contexto;
        this._Resource = Resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaADevolver, ViewGroup GrupoActual)
    {
        CircleImageView FotoPerfil;
        TextView NombreTorneo;
        LayoutInflater MiInflador;
        if(VistaADevolver == null)
        {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource,null);
        }
        FotoPerfil = VistaADevolver.findViewById(R.id.PerfilTorneo);
        NombreTorneo = VistaADevolver.findViewById(R.id.Torneo);

        Torneo T = getItem(pos);

        FotoPerfil.setImageResource(R.drawable.icono_equipo);
        NombreTorneo.setText(T._nombreTorneo);

        return  VistaADevolver;
    }
}
