package org.example.metodosnumericos1.Models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Gaussiana{
    private VBox vPrincipal;
    private HBox hDimensiones;
    private GridPane gdpMatriz;
    private TextField txtDimensiones;
    private TextField[][] arrMatriz;
    private Label lblDimensiones, lblResultados;
    private Button btnGenerar, btnIniciar, btnLimpiar;
    private int rows, columns;

    public Gaussiana (){
        CrearUI();
        Stage stage=new Stage();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(vPrincipal, 600, 400);
        stage.setTitle("Eliminación Gaussiana");
        stage.setScene(scene);
        stage.show();
    }

    private void CrearUI() {
        //TextFields
        txtDimensiones = new TextField("3x4");
        txtDimensiones.setPrefWidth(40);
        //Labels
        lblDimensiones = new Label("Dimensión de la matriz");
        lblResultados = new Label();
        lblResultados.setId("resultado");
        //Botones
        btnGenerar = new Button("Generar");
        btnGenerar.setOnAction(event -> CrearMatriz());
        btnIniciar = new Button("Calcular");
        btnIniciar.setOnAction(event -> Gaussiana());
        btnLimpiar = new Button("Limpiar campos");
        btnLimpiar.setOnAction(event -> Limpiar());
        //HBox
        hDimensiones = new HBox(lblDimensiones, txtDimensiones, btnGenerar, btnIniciar, btnLimpiar);
        hDimensiones.setPadding(new Insets(10));
        hDimensiones.setSpacing(10);
        //GridPane
        gdpMatriz = new GridPane();
        //VBox
        vPrincipal = new VBox(hDimensiones, gdpMatriz, lblResultados);
        vPrincipal.setAlignment(Pos.CENTER);
        vPrincipal.setSpacing(10);
        vPrincipal.setPadding(new Insets(50));
    }

    private void CrearMatriz() {
        if (ValidarDimensiones()) {
            arrMatriz = new TextField[rows][columns];
            for (int i = 0; i < arrMatriz.length; i++) {
                for (int j = 0; j < arrMatriz[0].length; j++) {
                    arrMatriz[i][j] = new TextField("0");
                    arrMatriz[i][j].setPrefWidth(40);
                    gdpMatriz.add(arrMatriz[i][j], j, i);
                }
            }
        } else {
            txtDimensiones.setText("Entrada no válida");
        }
    }

    private boolean ValidarDimensiones() {
        boolean flag = true;
        String cad = txtDimensiones.getText(), cadNum = "";
        char opc;
        try {
            for (int i = 0; i < cad.length(); i++) {
                opc = cad.charAt(i);
                switch (opc) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        cadNum = cadNum + opc;
                        break;
                    case 'x':
                        rows = Integer.parseInt(cadNum);
                        cadNum = "";
                }
            }
            columns = Integer.parseInt(cadNum);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    private void Gaussiana() {
        double[][] matriz = CapturarMatriz();
        int n = matriz.length;
        double[] solucion = new double[n];
        String resultado;

        // Eliminación hacia adelante
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                double factor = matriz[k][i] / matriz[i][i];
                for (int j = i; j < n + 1; j++) {
                    matriz[k][j] -= factor * matriz[i][j];
                }
            }
        }

        // Sustitución hacia atrás
        for (int i = n - 1; i >= 0; i--) {
            solucion[i] = matriz[i][n];
            for (int j = i + 1; j < n; j++) {
                solucion[i] -= matriz[i][j] * solucion[j];
            }
            solucion[i] /= matriz[i][i];
        }

        // Mostrar solución
        resultado = "Solución:\n";
        for (int i = 0; i < n; i++) {
            resultado = resultado + "x[" + i + "] = " + solucion[i] + "\n";
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

            }
        }
        lblResultados.setText(resultado);
    }

    private double[][] CapturarMatriz() {
        double[][] mat = new double[rows][columns];
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    mat[i][j] = Double.parseDouble(arrMatriz[i][j].getText());
                }
            }
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensaje");
            alerta.setHeaderText("ERROR!");
            alerta.setContentText("Algún coeficiente en la matriz no es un número o está mal escrito");
            Optional<ButtonType> result = alerta.showAndWait();
        }
        return mat;
    }

    private void Limpiar() {
        txtDimensiones.setText("");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arrMatriz[i][j].setText("");
            }
        }
        lblResultados.setText("");
    }
}
