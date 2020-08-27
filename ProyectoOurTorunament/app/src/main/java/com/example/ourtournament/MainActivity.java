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
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Preferencias DatosGenerales;
    int CodeElegirFoto = 3,RequestCode;
    Button BTNFixture;
    Button BTNTablaDePosiciones;
    Button BTNInicio;
    Button BTNTablaDeGoleadores;
    Button BTNAdministracion;
    //fixture
    Fixture fixture = new Fixture();
    boolean Volver= false;

    //Tabla de Goleadores
    ArrayList<String> ListaJugador;
    ArrayList<String> ListaGoles;

    // Tabla de Posiciones
    ArrayList<Equipo> ListaPosiciones;
    //Inicio

    ArrayList<Noticia> ListaNoticias;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdminFragments=getFragmentManager();
        DatosGenerales = CargarSharedPreferences();
        DatosGenerales.EliminarString("contrasenia");
        String Nombre = DatosGenerales.ObtenerString("contrasenia","no hay contrasenia");
        if(Nombre=="no hay contrasenia")
        {
            setContentView(R.layout.pantalla_vacia_con_fragment);
            Loguear logueo = new Loguear();
            TransaccionesDeFragment=AdminFragments.beginTransaction();
            TransaccionesDeFragment.replace(R.id.fragmentodepantallacompleta,logueo);
            TransaccionesDeFragment.commit();
            TransaccionesDeFragment.addToBackStack(null);
        }else
        {
            Entrar();
        }

    }
    public Preferencias CargarSharedPreferences()
    {
        SharedPreferences aux = getSharedPreferences("DatosGenerales",MODE_PRIVATE);
        SharedPreferences.Editor editor = null;
        Preferencias P = new Preferencias(aux,editor);
        return P;
    }
    public void Entrar()
    {
        setContentView(R.layout.activity_main);
        CargarGeneral();
        CargarInicio();
    }
    //  GENERAL
    public void CargarGeneral()
    {
        BTNFixture = findViewById(R.id.Fixture);
        BTNTablaDePosiciones = findViewById(R.id.TablaDePosiciones);
        BTNInicio = findViewById(R.id.Inicio);
        BTNTablaDeGoleadores = findViewById(R.id.TablaDeGoleadores);
        BTNAdministracion = findViewById(R.id.Administracion);
    }

    public Bitmap BuscarImagenEnCarrete(String Ubicacion)
    {
        Bitmap Imagen = null;
        try {
            Imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(Ubicacion));
        }catch (Exception Error)
        {
            Log.d("conexion", "Hay un error: "+Error);
        }
        return Imagen;
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

        IrAInicio(null);
    }

    public void PartidoSeleccionado()
    {
        MostrarPartido partido = new MostrarPartido();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,partido);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }


    public void Volver(){
        IrAFragment(fixture);
    }
    //Tabla de goleadores

    public ArrayList<String> getListaJugador() {return ListaJugador;}
    public ArrayList<String> getListaGoles() {return ListaGoles;}

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

        TablaDeGoleadores tablaDeGoleadores = new TablaDeGoleadores();
        IrAFragment(tablaDeGoleadores);
    }

    public void IrAInicio(View vista) {
        CambiarColor();
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio_verde);

        Inicio inicio = new Inicio();
        IrAFragment(inicio);
    }

    public void IrATablaPosiciones(View vista) {
        CambiarColor();
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones_verde);

        TablaPosiciones tabladeposiciones = new TablaPosiciones();
        IrAFragment(tabladeposiciones);
    }

    public void IrAAdministracion(View vista) {
        CambiarColor();
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin_verde);

        Administracion admin = new Administracion();
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

}
