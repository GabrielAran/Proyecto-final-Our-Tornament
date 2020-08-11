package com.example.ourtournament.ClasesBases;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.R;

public class MainBase extends AppCompatActivity {

    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    public void CambiarDeFragment(View vista)
    {
        BTNFixture.setBackgroundResource(R.drawable.icono_fixture_verde);
        BTNTablaDeGoleadores.setBackgroundResource(R.drawable.icono_tabla_goleadores);
        BTNInicio.setBackgroundResource(R.drawable.icono_inicio);
        BTNTablaDePosiciones.setBackgroundResource(R.drawable.icono_tabla_posiciones);
        BTNAdministracion.setBackgroundResource(R.drawable.icono_admin);
        Fixture fixture = new Fixture();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fixture);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }
}
