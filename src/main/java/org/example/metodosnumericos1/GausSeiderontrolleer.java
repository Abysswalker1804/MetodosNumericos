package org.example.metodosnumericos1;

import org.example.metodosnumericos1.Models.GaussSeider;
import org.example.metodosnumericos1.Models.Tabla;
import javafx.beans.property.FloatProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public class GausSeiderontrolleer {

    @FXML
    BorderPane scene1;

    @FXML
    TextField numeVariables;

    @FXML
    Button btnStart;

    @FXML
    Button btnGaussSeider;

    @FXML
    VBox vboxRight;

    @FXML
    TextField txtError;

    TextField []a_funciones;

    TextField []a_valoIniciales;

    @FXML
    public void m_inicCampos(){

        if(numeVariables.getText().equals(""))
            m_alert("Errro","No ha ingresado ningun valor inicial", Alert.AlertType.WARNING);
        else
            try{

               if(Integer.parseInt(numeVariables.getText())<=26 && Integer.parseInt(numeVariables.getText())>=0)
                  m_cargInfo();
               else
                   m_alert("Error","El numero de funciones debe estar entre 0 y 26", Alert.AlertType.WARNING);


            }catch (Exception e){
               m_alert("Error","Has ingresado un caracter no alfanumerico", Alert.AlertType.WARNING);
            }



    }

    private void m_cargInfo(){
        int v_contador;
        a_funciones=new TextField[Integer.parseInt(numeVariables.getText())];
        a_valoIniciales=new TextField[Integer.parseInt(numeVariables.getText())];
        vboxRight.setVisible(true);
        VBox v_vbox=new VBox((a_funciones.length<=17)? 20:5);
        HBox v_hbox;
         v_hbox=new HBox(150);
         v_hbox.setAlignment(Pos.CENTER);
         v_hbox.getChildren().addAll(new Label("Funciones"),new Label("Valores Iniciales"));

         v_vbox.getChildren().setAll(v_hbox);
        for(v_contador=0;v_contador<a_funciones.length;v_contador++) {
            v_hbox = new HBox(30);
            v_hbox.setAlignment(Pos.CENTER);
            a_funciones[v_contador]=new TextField();
            a_valoIniciales[v_contador]=new TextField();
            v_hbox.getChildren().setAll( new Label( (char)(v_contador+'a')+"="),a_funciones[v_contador],new Label( (char)(v_contador+'a')+"->"),a_valoIniciales[v_contador]);
            v_vbox.getChildren().addAll(v_hbox);
        }

      scene1.setLeft(v_vbox);
    }

    private void m_alert(String p_tittle, String p_msg, Alert.AlertType p_type){
        Alert v_alert=new Alert(p_type);
         v_alert.setTitle(p_tittle);
         v_alert.setContentText(p_msg);
         v_alert.show();
    }

    private float[] m_getDatos(){
        float v_respuesta[];
        int v_contador;
        v_respuesta=new float[a_valoIniciales.length];
        v_contador=0;

        try {
            for (TextField v_dato : a_valoIniciales)
                v_respuesta[v_contador++] = Float.parseFloat(v_dato.getText());

        }catch (Exception e){
            v_respuesta=null;
        }

        return v_respuesta;
    }

    @FXML
    private void StartGauss(){
        GaussSeider v_tabla=new GaussSeider();
        float[] v_castDatos;
        if(v_tabla.m_cargar(a_funciones)) {
            v_castDatos=m_getDatos();


            if(v_castDatos!=null) {
                try{
                v_tabla.m_start(v_castDatos, Float.parseFloat(txtError.getText()));
                m_cargTabla(v_tabla.getA_respuesta());
               }catch (Exception e){
                    m_alert("Error","caracter no numerico en el error permitido", Alert.AlertType.WARNING);
                }


            } else
                m_alert("Error","caracter no numerico en los vaores iniciales", Alert.AlertType.WARNING);

        }else
            m_alert("Error","Parece que una de las funciones esta mal escrita", Alert.AlertType.WARNING);


    }


    private void m_cargTabla(List<Tabla> p_tabla){
        int v_fila,v_columna;
        VBox v_box=new VBox(10);
        GridPane v_gripPane=new GridPane();
        DecimalFormat v_formato =new DecimalFormat("0.000000");
        v_box.getChildren().setAll(v_gripPane);

        v_box.setAlignment(Pos.TOP_CENTER);
        v_gripPane.setAlignment(Pos.CENTER);
        v_gripPane.setVgap(10);
        v_gripPane.setHgap(50);
        v_gripPane.setStyle("-fx-background-color: #efd4b0");





        v_gripPane.add(new Label("No"),0,0);
        for(v_columna=0; v_columna<p_tabla.get(0).getA_valoIniciales().length;v_columna++){
           v_gripPane.add(new Label((char)(v_columna+'A')+""),v_columna+1,0);
           v_gripPane.add(new Label((char)(v_columna+'A')+" n"),v_columna+p_tabla.get(0).getA_valoIniciales().length+1,0);
            v_gripPane.add(new Label("E"+(char)(v_columna+'A')),v_columna+2*p_tabla.get(0).getA_valoIniciales().length+1,0);
        }


        for(v_fila=0;v_fila<p_tabla.size();v_fila++){
            v_gripPane.add(new Label(v_fila+""),0,v_fila+1);


            for(v_columna=0; v_columna<p_tabla.get(0).getA_valoOptenidos().length;v_columna++){
                v_gripPane.add(new Label(v_formato.format(p_tabla.get(v_fila).getA_valoIniciales()[v_columna])),v_columna+1,v_fila+1);
                v_gripPane.add(new Label(v_formato.format(p_tabla.get(v_fila).getA_valoOptenidos()[v_columna])),v_columna+p_tabla.get(0).getA_valoIniciales().length+1,v_fila+1);
                v_gripPane.add(new Label(p_tabla.get(v_fila).getA_errores()[v_columna]),v_columna+2*p_tabla.get(0).getA_valoIniciales().length+1,v_fila+1);

            }

   }





       scene1.setCenter(v_box);
    }





}
