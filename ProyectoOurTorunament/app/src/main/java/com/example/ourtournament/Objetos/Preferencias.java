package com.example.ourtournament.Objetos;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

public class Preferencias{

    SharedPreferences Datos;
    android.content.SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public Preferencias(SharedPreferences datos, android.content.SharedPreferences.Editor edit)
    {
        Datos = datos;
        editor = edit;
        editor = Datos.edit();
    }
    public Preferencias()
    {
    }
    public void GuardarString(String Clave,String dato)
    {
        editor.putString(Clave, dato);
        editor.apply();
    }
    public String ObtenerString(String Clave,String defaul)
    {
        return Datos.getString(Clave,defaul);
    }
    public void EliminarDato(String Clave)
    {
        editor.remove(Clave);
        editor.apply();
    }

}
