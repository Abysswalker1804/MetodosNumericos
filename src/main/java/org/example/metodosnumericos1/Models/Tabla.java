package org.example.metodosnumericos1.Models;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class Tabla {
    DecimalFormat a_formato;

    float  [] a_valoIniciales;

    float []a_valoOptenidos;

   private String[]a_errores;

    Tabla(float[] p_valoIniciales){
        a_formato=new DecimalFormat("0.000000");
        a_valoIniciales=new float[p_valoIniciales.length];
        a_valoOptenidos=new float[p_valoIniciales.length];
        a_errores=new String[p_valoIniciales.length];
        setA_valoIniciales(p_valoIniciales);
    }


    public String[] getA_errores() {
        return a_errores;
    }

    public boolean m_veriError(float p_error){
        int v_contador;
        boolean v_bandera;

        for(v_contador=0,v_bandera=true;v_bandera && v_contador<a_errores.length;v_contador++)

            if(Float.parseFloat(a_errores[v_contador])>p_error)
                v_bandera=false;

        return v_bandera;
  }

    public float[] getA_valoIniciales() {
        return a_valoIniciales;
    }

    public float[] getA_valoOptenidos() {
        return a_valoOptenidos;
    }



    public void setA_valoIniciales(float[] p_valoIniciales) {
        int v_contador;
        a_valoIniciales = p_valoIniciales;
        for(v_contador=0;v_contador<p_valoIniciales.length;v_contador++)
            a_valoOptenidos[v_contador]=a_valoIniciales[v_contador];
    }


    public void setA_valoOptenidos(String p_valor,int p_indice) {
        float v_auxiliar;
        a_valoOptenidos[p_indice]=Float.parseFloat(p_valor);
        v_auxiliar=Math.abs(Math.abs(a_valoOptenidos[p_indice]-a_valoIniciales[p_indice])/a_valoOptenidos[p_indice]*100);
        setA_errores(a_formato.format(v_auxiliar),p_indice);
    }

    public void setA_errores(String p_valor,int p_indice) {
        a_errores [p_indice]=p_valor;
    }
}
