package org.example.metodosnumericos1.Models;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class GaussSeider {
   private List<Tabla> a_respuesta=new ArrayList<>();

    private Multivariable []a_funciones;

    public boolean m_cargar(TextField[]p_funciones){
        boolean v_bandera;
        int v_contador;

        a_funciones=new Multivariable[p_funciones.length];

        for(v_contador=0,v_bandera=true;v_bandera && v_contador<p_funciones.length ;v_contador++){
           a_funciones[v_contador]=new Multivariable();
           if(!p_funciones[v_contador].getText().equals(""))
           v_bandera=a_funciones[v_contador].m_cargFuncion(p_funciones[v_contador].getText(),(byte)p_funciones.length);
           else
               v_bandera=false;
        }


        return v_bandera;
    }



    public void m_start(float []p_valores, float p_error){
          int v_contador;
          boolean v_bandera;
          Tabla v_fila;
        v_fila=new Tabla(p_valores);


        do {
            for (v_contador = 0; v_contador < p_valores.length; v_contador++)
                v_fila.setA_valoOptenidos(a_funciones[v_contador].m_evaluar(v_fila.getA_valoOptenidos()), v_contador);

             a_respuesta.add(v_fila);
             v_bandera=v_fila.m_veriError(p_error);
             v_fila=new Tabla(v_fila.getA_valoOptenidos());
          }while(!v_bandera);


    }


    public List<Tabla> getA_respuesta() {
        return a_respuesta;
    }
}
