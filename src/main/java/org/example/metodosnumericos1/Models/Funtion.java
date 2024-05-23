package org.example.metodosnumericos1.Models;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.text.DecimalFormat;
public class Funtion{
    protected String a_funtion[];
    protected DecimalFormat a_formato;

    public Funtion(){
        a_funtion=null;
        a_formato=new DecimalFormat("0.000000");

    }


    public String[] getA_funtion() {
        return a_funtion;
    }

    /*apoyado de casi todos los metodos genera un arreglo tokenizando de una
          exprecion en forma postfija en caso de no poderse generar devuelve un false*/
    public boolean m_cargFuncion(String p_funtion){
        int v_contador,v_anteGerarquia;
        boolean v_estado;
        String v_token[];
        Stack  v_pilaOperandores;
        Queue    v_queuResultado;
        v_token=m_toquenizar(m_estandarizar(p_funtion));




        v_queuResultado=new LinkedList<String>();
        v_pilaOperandores=new Stack<String>();



        for(v_contador=0, v_estado=true;v_contador<v_token.length && v_estado;v_contador++)

            if(m_veriCaracter(v_token[v_contador]) )
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
            //caliz en caso de error sintactico o caracter desconocido
            if(m_evaluar(0.245617f).equals("ErrorCHD") || m_evaluar(0.245617f).equals("SintaxError") )
                v_estado=false;
            //
        }




        return v_estado;

    }
    //***************************************************************************

    public String m_evaluar(float p_valor){
        int v_contador;
        boolean v_bandera;
        Stack v_pilaOperandos;

        v_pilaOperandos=new Stack<String>();

        try{
            for(v_contador=0, v_bandera=true;v_contador<a_funtion.length && v_bandera; v_contador++, v_bandera=(v_pilaOperandos.peek().equals("error"))? false:true)

                if(m_veriCaracter(a_funtion[v_contador]))

                    if (!a_funtion[v_contador].equals("x"))
                        v_pilaOperandos.push(a_funtion[v_contador]);
                    else
                        v_pilaOperandos.push(a_formato.format(p_valor));

                else
                    m_haceOperacion(v_pilaOperandos,a_funtion[v_contador]);

        }catch(Exception e){
            v_pilaOperandos.push("ErrorCHD");
        }

        if(v_pilaOperandos.size()>1)
            v_pilaOperandos.push("SintaxError");

        return v_pilaOperandos.pop()+"";
    }


    protected void m_haceOperacion(Stack p_operandos, String p_operador){
        String v_respuesta;
        float v_numero1,v_numero2;
        v_numero1=v_numero2=-27;
        v_respuesta="";

        try{

            switch(p_operador){

                case "+":
                    v_numero2=Float.parseFloat(p_operandos.pop()+"");
                    v_numero1=Float.parseFloat(p_operandos.pop()+"");
                    v_respuesta=a_formato.format(v_numero1+v_numero2);
                    break;
                case "-":
                    v_numero2=Float.parseFloat(p_operandos.pop()+"");
                    v_numero1=Float.parseFloat(p_operandos.pop()+"");
                    v_respuesta=a_formato.format(v_numero1-v_numero2);

                    break;


                case "*":
                    v_numero2=Float.parseFloat(p_operandos.pop()+"");
                    v_numero1=Float.parseFloat(p_operandos.pop()+"");
                    v_respuesta=a_formato.format(v_numero1*v_numero2);
                    break;

                case "/":
                    v_numero2=Float.parseFloat(p_operandos.pop()+"");
                    v_numero1=Float.parseFloat(p_operandos.pop()+"");
                    v_respuesta=a_formato.format(v_numero1/v_numero2);

                    break;
                case "^":
                    v_numero2=Float.parseFloat(p_operandos.pop()+"");
                    v_numero1=Float.parseFloat(p_operandos.pop()+"");
                    v_respuesta=a_formato.format(Math.pow(v_numero1,v_numero2) );


                    break;

                case "~":
                    v_respuesta=a_formato.format( (-1)*Float.parseFloat(p_operandos.pop()+"") );

                    break;

                case "|":
                    v_numero1=(float)Math.sin(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "°":
                    v_numero1=(float)Math.cos(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "¬":
                    v_numero1=(float)Math.tan(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "$":
                    v_numero1=(float)Math.asin(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "%":
                    v_numero1=(float)Math.acos(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "&":
                    v_numero1=(float)Math.atan(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "¿":
                    v_numero1=(float)Math.log(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "?":
                    v_numero1=(float)Math.log10(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

                case "=":
                    v_numero1=(float)Math.exp(Float.parseFloat(p_operandos.pop()+""));
                    v_respuesta=a_formato.format(v_numero1);
                    break;

            }

        }catch(Exception e){
            v_respuesta="Error";
        }

        if(v_respuesta.equals("NaN"))
            v_respuesta="error";

        if(!v_respuesta.equals(""))
            p_operandos.push(v_respuesta);




    }


    //verifica la insercion de los operadores en la pila para crear la exprecion polaca
    protected void m_buscPosicion(Queue p_respuesta, Stack p_operadores, String p_caracter){

        if(p_operadores.isEmpty())
            p_operadores.push(p_caracter);

        else if(p_caracter.equals("(") || m_veriFuncion(p_caracter))
            p_operadores.push(p_caracter);
        else{

            while(!p_operadores.isEmpty() && m_veriGerarquia(p_caracter)<=m_veriGerarquia(p_operadores.peek()+""))
                p_respuesta.add(p_operadores.pop());

            p_operadores.push(p_caracter);
        }



    }

    //caso de encontrar un parentesis que sierra buscara a su hermano )-> busca (
    protected boolean m_buscParentesis(Queue p_respuesta, Stack p_operadores){
        boolean v_respuesta;
        v_respuesta=false;

        while (!p_operadores.isEmpty() && !v_respuesta)

            if( (p_operadores.peek()+"").equals("(")){
                v_respuesta=true;
                p_operadores.pop();
            }else if(m_veriFuncion(p_operadores.peek()+"")){
                v_respuesta=true;
                p_respuesta.add(p_operadores.pop()+"");
            }else
                p_respuesta.add(p_operadores.pop()+"");


        return v_respuesta;
    }
    //**************************************************************************


    // Convierte el String ingresado en un arreglo donde cada campo es un operador o operando
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


            if(v_anterior.equals(")") && v_actual.equals("(") ){
                v_pila.push(v_anterior);
                v_pila.push("*");
                v_pila.push(v_actual);
            }
            else if( (m_veriCaracter(v_anterior) && v_actual.equals("x")) ){
                v_pila.push(v_anterior);
                v_pila.push("*");
                v_pila.push("x");

            }else if ( ( m_veriCaracter(v_anterior) ||v_anterior.equals("x") || (v_anterior.equals(")")) ) && m_veriFuncion(v_actual) ){
                v_pila.push(v_anterior);
                v_pila.push("*");
                v_pila.push(v_actual);

            }else if(m_veriCaracter(v_anterior) && m_veriCaracter(v_actual) && !v_anterior.equals("x"))
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
    //***********************************************************************************


    //Metodo escencil para verificar si un token es un operador o operando
    private boolean m_veriCaracter(String p_caracter){
        boolean v_respuesta;

        try{
            Float.parseFloat(p_caracter);
            v_respuesta=true;


        } catch(Exception e){
            if(p_caracter.equals("x"))
                v_respuesta=true;
            else if(p_caracter.contains("."))
                v_respuesta=true;
            else
                v_respuesta=false;

        }


        return v_respuesta;
    }
    //********************************************************************************


    //invierte la pila y la castea a un arreglo de tipo String ****************
    protected String[] m_cargar(Stack p_pila){
        String v_respuesta[];
        int v_contador;
        v_respuesta=new String[p_pila.size()];
        v_contador=0;

        p_pila=m_invertir(p_pila);
        while(!p_pila.isEmpty())
            v_respuesta[v_contador++]=p_pila.pop()+"";




        return v_respuesta;
    }
    //***********************************************************************

    //castea una Queue a un arreglo de String
    protected String[] m_cargar(Queue p_exprecion){
        String []v_resultado;
        int v_contador;
        v_resultado=new String[p_exprecion.size()];

        for(v_contador=0;v_contador<v_resultado.length; v_contador++)
            v_resultado[v_contador]=p_exprecion.poll()+"";

        return v_resultado;
    }
    //************************************************************************


    //invierte la pila********************************************************
    protected Stack m_invertir(Stack p_pila){
        Stack  v_pilaAuxiliar;
        v_pilaAuxiliar=new Stack<String>();

        while(!p_pila.isEmpty())
            v_pilaAuxiliar.push(p_pila.pop()+"");



        return v_pilaAuxiliar;
    }
    //*************************************************************************

    //quita a la cadena caracteres de espacio y pone un * entre numeros y variable x NX -> N*X
    protected String m_estandarizar(String p_formula){
        int v_contador;
        p_formula= p_formula.toLowerCase();
        p_formula=p_formula.replace(" ","");


        //estandarizando funciones

        p_formula=p_formula.replace("arcsen(","$");
        p_formula=p_formula.replace("arccos(","%");
        p_formula=p_formula.replace("arctan(","&");

        p_formula=p_formula.replace("sen(","|");
        p_formula=p_formula.replace("cos(","°");
        p_formula=p_formula.replace("tan(","¬");



        p_formula=p_formula.replace("ln(","¿");
        p_formula=p_formula.replace("log(","?");

        p_formula=p_formula.replace("exp(","=");








        return p_formula;
    }
    //**************************************************************************************

    //verifica si el operador es una funcion trigonometrica, logaritmica etc
    protected boolean m_veriFuncion(String p_operador){
        boolean v_respuesta;

        switch(p_operador){
            case "|":

            case "°":

            case "¬":

            case "$":

            case "%":

            case "&":

            case "¿":

            case "?":

            case "=":

                v_respuesta=true;
                break;

            default:
                v_respuesta=false;

        }

        return v_respuesta;
    }

    // Asigna un numero de gerarquia al operador
    protected int m_veriGerarquia(String p_operador){
        int v_respuesta;

        if(p_operador.equals("(") || m_veriFuncion(p_operador))
            v_respuesta=0;
        else if(p_operador.equals("+") || p_operador.equals("-"))
            v_respuesta=1;
        else if(p_operador.equals("*") || p_operador.equals("/"))
            v_respuesta=2;
        else if (p_operador.equals("^"))
            v_respuesta=4;
        else
            v_respuesta=5;

        return v_respuesta;
    }
    //*****************************************************************************

    //este metodo cambia la suma y resta en caso de que se presente un operador unitario
    protected String m_asigCaraUnitario(String p_caracter){
        if(p_caracter.equals("+"))
            p_caracter="#";
        else if(p_caracter.equals("-"))
            p_caracter="~";

        return p_caracter;
    }
    //***************************************************************************

}