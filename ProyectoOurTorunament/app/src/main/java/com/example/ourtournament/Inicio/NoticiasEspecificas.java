package com.example.ourtournament.Inicio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;
import com.squareup.picasso.Picasso;

public class NoticiasEspecificas extends Fragment {

    FragmentManager AdminFragments;
    TextView Titulo, Descripcion, Fecha, renglon;
    ImageView imageView;

    Button Volver;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.interior_noticias, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        imageView = VistaADevolver.findViewById(R.id.imageView);
        Titulo = VistaADevolver.findViewById(R.id.Titulo);
        Descripcion = VistaADevolver.findViewById(R.id.Descripcion);
        Fecha = VistaADevolver.findViewById(R.id.Fecha);
        renglon = VistaADevolver.findViewById(R.id.renglon);
        Volver = VistaADevolver.findViewById(R.id.Volver);

        String Ruta = "http://10.0.2.2:55859/Imagenes/Noticias/ID" + 1 +"_1.JPG";
        Picasso.get().load(Ruta).into(imageView);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Inicio ini = new Inicio();
                Principal.IrAFragment(ini,false);
            }
        });

        return VistaADevolver;
    }
}

