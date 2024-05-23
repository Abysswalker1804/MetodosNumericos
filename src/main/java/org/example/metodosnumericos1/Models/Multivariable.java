package org.example.metodosnumericos1.Models;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.text.DecimalFormat;
class Multivariable extends Funtion{
    private byte a_numeVariables;

    Multivariable(){
        a_numeVariables=-1;
        a_formato=new DecimalFormat("0.000000");

    }





    public boolean m_cargFuncion(String p_funtion, byte p_numeVariables){
        int v_contador,v_anteGerarquia;
        boolean v_estado;
        String v_token[];
        Stack  v_pilaOperandores;
        Queue    v_queuResultado;
        v_token=m_toquenizar(m_estandarizar(p_funtion));


        v_queuResultado=new LinkedList<String>();
        v_pilaOperandores=new Stack<String>();

        if(p_numeVariables>=1 && p_numeVariables<=26){
            v_estado=true;
            a_numeVariables=(byte)(p_numeVariables);

        }else
            v_estado=false;


        for(v_contador=0;v_contador<v_token.length && v_estado;v_contador++)

            if(m_veriVariDesconocido(v_token[v_contador].charAt(0)))
                v_estado=false;
            else if(m_veriCaracter(v_token[v_contador]) )
                v_queuResultado.add(v_token[v_contador]);
            else{

                if(v_token[v_contador].equals(")"))
                    v_estado=m_buscParentesis(v_queuResultado,v_pilaOperandores);
                else
                    m_buscPosicion(v_queuResultado,v_pilaOperandores,v_token[v_contador]);
            }



        if(v_estado){

            while(!v_pilaOperandores.isEmpty())
                v_queuResultado.add(v_pilaOperandores.pop()+"");

            a_funtion=m_cargar(v_queuResultado);


        }



        return v_estado;


    }


    public String m_evaluar(float p_valores[]){
        int v_contador;
        boolean v_bandera;
        Stack v_pilaOperandos;

        v_pilaOperandos=new Stack<String>();

        for(v_contador=0, v_bandera=true;v_contador<a_funtion.length && v_bandera; v_contador++, v_bandera=(v_pilaOperandos.peek().equals("error"))? false:true)

            if(m_veriCaracter(a_funtion[v_contador]))

                if( m_veriVariable(a_funtion[v_contador].charAt(0)) )
                    v_pilaOperandos.push( a_formato.format( p_valores[a_funtion[v_contador].charAt(0)-'a'] )  );
                else
                    v_pilaOperandos.push(a_funtion[v_contador]);

            else
                m_haceOperacion(v_pilaOperandos,a_funtion[v_contador]);





        return v_pilaOperandos.pop()+"";

    }


    private boolean m_veriVariDesconocido(char p_variable){
        boolean v_respuesta;

        if(m_veriVariable(p_variable))

            if( p_variable-'a'<a_numeVariables )
                v_respuesta=false;
            else
                v_respuesta=true;

        else
            v_respuesta=false;

        return v_respuesta;
    }


    private String[] m_toquenizar(String p_funtion){
        int v_contador;
        String v_respuesta[];
        Stack v_pila;
        String v_anterior ,v_actual;
        v_pila=new <String>Stack();


        if (!m_veriCaracter(p_funtion.charAt(0)+""))
            v_pila.push(m_asigCaraUnitario(p_funtion.charAt(0)+"" ) );
        else
            v_pila.push(p_funtion.charAt(0)+"");

        for(v_contador=1;v_contador<p_funtion.length();v_contador++){

            v_anterior=v_pila.pop()+"";
            v_actual=p_funtion.charAt(v_contador)+"";


            if(m_veriMultNecesaria(v_anterior,v_actual)){
                v_pila.push(v_anterior);
                v_pila.push("*");
                v_pila.push(v_actual);
            }
            else if(m_veriCaracter(v_anterior) && m_veriCaracter(v_actual) && !m_veriVariable(v_anterior.charAt(0)))
                v_pila.push(v_anterior+v_actual);
            else{
                v_pila.push(v_anterior);

                if ((!v_anterior.equals(")") || m_veriFuncion(v_anterior)) && !m_veriCaracter(v_anterior) && !m_veriCaracter(v_actual) )
                    v_pila.push(m_asigCaraUnitario(v_actual));

                else
                    v_pila.push(p_funtion.charAt(v_contador)+"");

            }

        }

        v_respuesta=m_cargar(v_pila);


        return v_respuesta;

    }



    private boolean m_veriMultNecesaria(String p_anterior,String p_actual){
        boolean v_respuesta;
        if(p_anterior.equals(")") && p_actual.equals("(") )
            v_respuesta=true;

        else if(m_veriCaracter(p_anterior) && m_veriVariable(p_actual.charAt(0)) )
            v_respuesta=true;

        else if ( ( m_veriCaracter(p_anterior) || m_veriVariable(p_anterior.charAt(0)) || (p_anterior.equals(")")) ) && m_veriFuncion(p_actual) )
            v_respuesta=true;
        else if (m_veriCaracter(p_anterior) && p_actual.equals("(") )
            v_respuesta=true;
        else
            v_respuesta=false;

        return v_respuesta;
    }

    //Actualizacion del metodo de verificacion de acracteres a multivariable
    private boolean m_veriCaracter(String p_caracter){
        boolean v_respuesta;

        try{
            Float.parseFloat(p_caracter);
            v_respuesta=true;


        } catch(Exception e){
            if(m_veriVariable(p_caracter.charAt(0)))
                v_respuesta=true;
            else if(p_caracter.contains("."))
                v_respuesta=true;
            else
                v_respuesta=false;

        }


        return v_respuesta;


    }

    //este metodo verifica si la variable esta dentro del rango
    private boolean m_veriVariable(char p_variable){
        boolean v_respuesta;

        if(p_variable-'a'>=0 && p_variable-'a'<=25)
            v_respuesta=true;
        else
            v_respuesta=false;

        return v_respuesta;
    }
    //*****************************************************
}
