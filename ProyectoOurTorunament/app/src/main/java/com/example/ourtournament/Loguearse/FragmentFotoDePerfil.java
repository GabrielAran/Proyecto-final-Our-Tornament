package com.example.ourtournament.Loguearse;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentFotoDePerfil extends Fragment{
    FragmentManager AdminFragments;
    Button Agregar,Quitar;
    CircleImageView Foto;
    Bitmap Imagen = null;
    int RequestCode,CodeElegirFoto = 23;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.armar_foto_perfil, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        Quitar = VistaADevolver.findViewById(R.id.Quitar);
        Agregar = VistaADevolver.findViewById(R.id.Agregar);
        Foto = VistaADevolver.findViewById(R.id.Foto);


        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                if (ContextCompat.checkSelfPermission(Principal,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("conexion", "entre a pedir el permiso");
                    Principal.PedirPermisoParaCarrete();
                    Log.d("conexion", "ya lo pedi");
                }else {
                    Log.d("conexion","entre a buscar la foto");
                    Intent ObtenerFoto = new Intent(Intent.ACTION_GET_CONTENT);
                    ObtenerFoto.setType("image/*");
                    startActivityForResult(Intent.createChooser(ObtenerFoto,"Seleccione una foto"),CodeElegirFoto);
                }
            }
        });

        return VistaADevolver;
    }

    public void onActivityResult(int RequestCode, int ResultCode, @NonNull Intent DatosRecibidos) {
        super.onActivityResult(RequestCode, ResultCode, DatosRecibidos);

        if(RequestCode == CodeElegirFoto && ResultCode == -1)
        {
            MainActivity Principal = (MainActivity) getActivity();
            String Ubicacion = String.valueOf(DatosRecibidos.getData());
            Imagen = Principal.BuscarImagenEnCarrete(Ubicacion);
            Foto.setImageBitmap(Imagen);
        }
    }
}


