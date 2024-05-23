package org.example.metodosnumericos1;

import org.example.metodosnumericos1.Models.Gaussiana;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vContenedor;
    private Button btnGausSeider, btnGaussiana, btnSecante;
    private Label lblNombres;
    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gausSeider-view.fxml"));
        ///Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene=new Scene(vContenedor,400,400);
        stage.setTitle("Métodos Numéricos");
        stage.setScene(scene);
        stage.show();
    }
    public void CrearUI(){
        //Buttons
        btnGausSeider=new Button("Gauss Seider");
        btnGausSeider.setOnAction(event -> {
            try {
                m_starGaussSeider();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnGaussiana=new Button("Eliminación Gaussiana");
        btnGaussiana.setOnAction(event -> new Gaussiana());
        btnSecante=new Button("Secante");
        btnSecante.setOnAction(event -> new org.example.metodosnumericos1.Secante());
        //Labels
        lblNombres=new Label("\nEquipo:\nAlberto Emilio Solis Santos\nPablo Torrecillas\nJose Brayan Saldaña Aguado\nTadeo Tamachtiani Palma Avila\nEsteban de Jesus Parra Hernández");
        //VBox
        vContenedor=new VBox(btnGausSeider,btnGaussiana,btnSecante,lblNombres);
        vContenedor.setAlignment(Pos.CENTER);
        vContenedor.setSpacing(10);
    }
    private void m_starGaussSeider() throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gausSeider-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}