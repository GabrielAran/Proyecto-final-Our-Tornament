package com.example.ourtournament.Objetos;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

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
    public void EliminarString(String Clave)
    {
        editor.remove(Clave);
        editor.apply();
    }
    public void GuardarListaString(String Clave, ArrayList<String> dato)
    {
        Gson gson = new Gson();
        String json = gson.toJson(dato);
        editor.putString(Clave,json);
    }
    public void GuardarInt(String Clave,int dato)
    {
        editor.putInt(Clave, dato);
        editor.apply();
    }
    public int ObtenerInt(String Clave,int defaul)
    {
        return Datos.getInt(Clave,defaul);
    }
    public void EliminarInt(String Clave)
    {
        editor.remove(Clave);
        editor.apply();
    }
    //Listas de objetos

    public void GuardarListaPartidos(String Clave, ArrayList<Partido> dato)
    {
        Gson gson = new Gson();
        String json = gson.toJson(dato);
        editor.putString(Clave,json);
    }

    public void GuardarListaEquipos(String Clave, ArrayList<Equipo> dato)
    {
        Gson gson = new Gson();
        String json = gson.toJson(dato);
        editor.putString(Clave,json);
    }

    public void GuardarListaGoleadores(String Clave, ArrayList<Usuario> dato)
    {
        Gson gson = new Gson();
        String json = gson.toJson(dato);
        editor.putString(Clave,json);
    }

}
