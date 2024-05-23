package org.example.metodosnumericos1;

import org.example.metodosnumericos1.Models.Funtion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Secante {
    private VBox vPrincipal;
    private HBox hEcuacion, hError, hVInicialAnt, hVInicial, hBotones;
    private TextField txtEcuacion, txtError, txtVInicialAnt, txtVInicial;
    private Label lblEcuacion, lblValidacion, lblError, lblValidacionError, lblVInicialAnt, lblValidacionValorAnt, lblVInicial, lblValidacionValor;
    private Button btnCalcular, btnBorrar, btnSalir, btnTabla;
    private double porError, valorInicialAnt, valorInicial;
    public Secante(){
        crearIU();
        Stage stage=new Stage();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene=new Scene(vPrincipal, 600,400);
        stage.setTitle("Secante");
        stage.setScene(scene);
        stage.show();

    }
    private void crearIU(){
        //Labels
        lblEcuacion=new Label("Ecuación:");
        lblValidacion=new Label();
        lblError=new Label("Porcentaje Error:");
        lblValidacionError=new Label();
        lblVInicialAnt=new Label("Valor inicial anterior:");
        lblValidacionValorAnt=new Label();
        lblVInicial=new Label("Valor inicial:");
        lblValidacionValor=new Label();

        //TextFields
        txtEcuacion=new TextField();
        txtEcuacion.setMaxWidth(700);
        txtError=new TextField();
        txtError.setMaxWidth(70);
        txtVInicialAnt=new TextField();
        txtVInicialAnt.setMaxWidth(70);
        txtVInicial=new TextField();
        txtVInicial.setMaxWidth(70);

        //Botones
        btnCalcular=new Button("Calcular");
        btnCalcular.setOnAction(event -> Secante());
        btnBorrar=new Button("Borrar campos");
        btnBorrar.setOnAction(event -> clearTextFields());
        btnSalir=new Button("Salir");
        btnSalir.setOnAction(event -> System.exit(0));
        //btnTabla=new Button("Ver Tabla");
        //btnTabla.setVisible(false);

        //Contenedores horizontales
        hEcuacion=new HBox(lblEcuacion,txtEcuacion);
        hEcuacion.setSpacing(10);
        hEcuacion.setAlignment(Pos.CENTER);
        hError=new HBox(lblError,txtError);
        hError.setSpacing(10);
        hError.setAlignment(Pos.CENTER);
        hVInicialAnt=new HBox(lblVInicialAnt,txtVInicialAnt);
        hVInicialAnt.setSpacing(10);
        hVInicialAnt.setAlignment(Pos.CENTER);
        hVInicial=new HBox(lblVInicial,txtVInicial);
        hVInicial.setSpacing(10);
        hVInicial.setAlignment(Pos.CENTER);
        hBotones=new HBox(btnCalcular, btnBorrar);
        hBotones.setSpacing(25);
        hBotones.setAlignment(Pos.CENTER);

        //Contenedor principal
        vPrincipal=new VBox(hEcuacion,lblValidacion,hError,lblValidacionError,hVInicialAnt,lblValidacionValorAnt,hVInicial,lblValidacionValor,hBotones,btnSalir);
        vPrincipal.setAlignment(Pos.CENTER);
        vPrincipal.setSpacing(10);
    }
    private void Secante(){//x^4-2x^3-12x^2+16x-40
        String ecuacion=txtEcuacion.getText();
        Funtion objFuncion=new Funtion();
        if(objFuncion.m_cargFuncion(ecuacion)){
            lblValidacion.setText("Ecuación Validada!");
            if(ValidarError()){
                lblValidacionError.setText("Valor válido");
                if(ValidarValorInicialAnt()){
                    lblValidacionValorAnt.setText("Valor válido");
                    if(ValidarValorInicial()){
                        lblValidacionValor.setText("Valor válido");
                        new Iteraciones(objFuncion,porError,valorInicialAnt,valorInicial);//Habrá que pasarle cosas al TableView
                    }else{lblValidacionValor.setText("Valor no válido");}
                }else{lblValidacionValorAnt.setText("Valor no válido");}
            }else{lblValidacionError.setText("Valor no válido");}
        }else{lblValidacion.setText("Ecuación no válida!");}
    }
    private void clearTextFields(){
        txtEcuacion.setText("");
        lblValidacion.setText("");
        txtError.setText("");
        txtVInicial.setText("");
    }
    private boolean ValidarError(){
        boolean flag=true;
        try{porError=Double.parseDouble(txtError.getText());}catch(Exception e){flag=false;}
        return flag;
    }
    private boolean ValidarValorInicialAnt(){
        boolean flag=true;
        try{valorInicialAnt=Double.parseDouble(txtVInicialAnt.getText());}catch (Exception e){flag=false;}
        return flag;
    }
    private boolean ValidarValorInicial(){
        boolean flag=true;
        try{valorInicial=Double.parseDouble(txtVInicial.getText());}catch(Exception e){flag=false;}
        return flag;
    }
}
