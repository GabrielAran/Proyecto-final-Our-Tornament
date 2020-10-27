package com.example.ourtournament.Administracion.Usuario;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.Administracion.Administracion;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Perfil extends Fragment {
    FragmentManager AdminFragments;
    TextView Nombre, Edad, Email, Contrasenia, GolesEnTorneo;
    EditText EDTNombre, EDTEdad, EDTEmail, EDTContrasenia, EDTGolesEnTorneo;
    ImageView foto;
    Button Volver;
    View VistaADevolver;
    MainActivity Principal;
    Preferencias P;
    Usuario Usu;
    private FragmentTransaction TransaccionesDeFragment;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.un_usuario, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();

        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();

        LlenarDatos();
        SetearListeners();

        return VistaADevolver;
    }

    private void Referencias() {
        Nombre = VistaADevolver.findViewById(R.id.Nombre);
        Edad = VistaADevolver.findViewById(R.id.Edad);
        Email = VistaADevolver.findViewById(R.id.Email);
        Contrasenia = VistaADevolver.findViewById(R.id.Contrasenia);
        GolesEnTorneo = VistaADevolver.findViewById(R.id.GolesEnTorneo);
        foto = VistaADevolver.findViewById(R.id.foto);
        Volver = VistaADevolver.findViewById(R.id.Volver);

        EDTNombre = VistaADevolver.findViewById(R.id.EDTNombre);
    }

    private void SetearListeners(){
        Volver.setOnClickListener(Atras);
        Nombre.setOnClickListener(EditarNombre);
    }

    private View.OnClickListener Atras = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Administracion admin = new Administracion();
            Principal.IrAFragment(admin,true);
        }
    };

    private View.OnClickListener EditarNombre = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Nombre.setVisibility(View.INVISIBLE);
            EDTNombre.setText(Usu.NombreUsuario);
            EDTNombre.setSelection(EDTNombre.getText().length() - 1, 0);
            EDTNombre.setSelectAllOnFocus(true);
            v.clearFocus ();
            v.requestFocus ();
            v.setSelected(true);
            EDTNombre.setVisibility(View.VISIBLE);
        }
    };

    private void LlenarDatos(){
        Gson gson = new Gson();
        String usuario = P.ObtenerString("InformacionUsuario", "");
        Usu = gson.fromJson(usuario, Usuario.class);

        String contra = "";
        for (int i = 0; i < Usu.Contrasenia.length() ;i++)
        {
            contra += "*";
        }
        Contrasenia.setText("Contraseña: "+contra);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Usu.FechaDeNacimiento);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Period edad = Period.between(LocalDate.of(year, month, day), LocalDate.now());

        Nombre.setText("Nombre: "+Usu.NombreUsuario);
        Edad.setText("Edad: "+edad.getYears()+" años");
        Email.setText("Email: "+Usu.Email);
        GolesEnTorneo.setText("Goles: "+Usu.GolesEnTorneo+ " goles en torneo");
        String Ruta = "https://image.freepik.com/vector-gratis/perfil-empresario-dibujos-animados_18591-58479.jpg";
        Picasso.get().load(Ruta).into(foto);
    }

}
