package com.example.ourtournament;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ourtournament.Administracion.Administracion;
import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.Fixture.MostrarPartido;
import com.example.ourtournament.Inicio.Inicio;
import com.example.ourtournament.Loguearse.Loguear;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.TablaGoleadores.TablaDeGoleadores;
import com.example.ourtournament.TablaPosiciones.TablaPosiciones;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager AdminFragments=getFragmentManager();
    FragmentTransaction TransaccionesDeFragment;
    Preferencias P;

    Button BTNFixture;
    Button BTNTablaDePosiciones;
    Button BTNInicio;
    Button BTNTablaDeGoleadores;
    Button BTNAdministracion;

    Fixture fixture = new Fixture();
    TablaDeGoleadores tablaDeGoleadores = new TablaDeGoleadores();
    Inicio inicio = new Inicio();
    TablaPosiciones tabladeposiciones = new TablaPosiciones();
    Administracion admin = new Administracion();

    ArrayList<Noticia> ListaNoticias;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        P = CargarSharedPreferences();
        //P.EliminarString("contrasenia");
        String Nombre = P.ObtenerString("contrasenia","no hay contrasenia");

        if(Nombre=="no hay contrasenia")
        {
            Loguear logueo = new Loguear();
            IrAFragmentDePantallaVacia(logueo);
        }else
        {
            CargarGeneral();
        }

    }
    public Preferencias CargarSharedPreferences()
    {
        SharedPreferences aux = getSharedPreferences("DatosGenerales",MODE_PRIVATE);
        SharedPreferences.Editor editor = null;
        Preferencias P = new Preferencias(aux,editor);
        return P;
    }
    //  GENERAL
    public void CargarGeneral()
    {
        setContentView(R.layout.activity_main);
        BTNFixture = findViewById(R.id.Fixture);
        BTNTablaDePosiciones = findViewById(R.id.TablaDePosiciones);
        BTNInicio = findViewById(R.id.Inicio);
        BTNTablaDeGoleadores = findViewById(R.id.TablaDeGoleadores);
        BTNAdministracion = findViewById(R.id.Administracion);
        CargarInicio();
        IrAInicio(null);
    }

    //Inicio
    public void CargarInicio()
    {
        int IDNoticia=1;
        String Torneo = "Europa league";
        String Titulo = "Boca y river empatan 8 a 8";
        String Descripcion = "el partido se llevo a cabo en una maniana calurosa donde river convertia 2 goles y boca 0";
        Boolean Destacada = true;
        ImageView Foto = null;
        Date Fecha = new Date(2/2/2020);
        Noticia UnaNoticia = new Noticia(IDNoticia,Torneo,Titulo,Descripcion,Destacada,Foto,Fecha);
        ListaNoticias = new ArrayList<>();
        ListaNoticias.add(UnaNoticia);
        ListaNoticias.add(UnaNoticia);
        ListaNoticias.add(UnaNoticia);
    }

    //Navegacion
    public void IrAFixture(View vista)
    {
        CambiarColor();
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture_verde);

        IrAFragment(fixture);
    }

    public void IrATablaGoleadores(View vista)
    {
        CambiarColor();
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores_verde);

        IrAFragment(tablaDeGoleadores);
    }

    public void IrAInicio(View vista) {
        CambiarColor();
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio_verde);

        IrAFragment(inicio);
    }

    public void IrATablaPosiciones(View vista) {
        CambiarColor();
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones_verde);

        IrAFragment(tabladeposiciones);
    }

    public void IrAAdministracion(View vista) {
        CambiarColor();
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin_verde);

        IrAFragment(admin);
    }

    //Inicio
    public ArrayList<Noticia> getNoticias(){return ListaNoticias;}

    public void CambiarColor()
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
    }

    public void IrAFragment(Fragment fragment){
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fragment);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }
    public void IrAFragmentDePantallaVacia(Fragment fragment){
        setContentView(R.layout.pantalla_vacia_con_fragment);
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.fragmentodepantallacompleta,fragment);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

}
