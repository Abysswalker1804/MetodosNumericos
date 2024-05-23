package org.example.metodosnumericos1;

import org.example.metodosnumericos1.Models.Funtion;
import org.example.metodosnumericos1.Renglon;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class Iteraciones extends Stage {
    private Scene escena;
    private Funtion objFuntion;
    private TableView tbvIteraciones;
    private BorderPane bdpPrincipal;
    private ToolBar tlbMenu;
    private Button btnCerrar;
    private double porcentajeError, valorInicialAnterior, valorInicial;

    //double xi_1=valorInicialAnterior,xi=valorInicial,fxi_1,fxi,xi1,error=100;
    public Iteraciones(Funtion objFuntion, double porcentajeError, double valorInicialAnterior, double valorInicial){
        this.objFuntion=objFuntion;
        this.porcentajeError=porcentajeError;
        this.valorInicialAnterior=valorInicialAnterior;
        this.valorInicial=valorInicial;
        CrearUI(this);
        this.setTitle("Iteraciones");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(Stage stage){
        btnCerrar=new Button("Cerrar");
        btnCerrar.setOnAction(event -> stage.close());
        tlbMenu=new ToolBar(btnCerrar);
        tbvIteraciones=new TableView();
        bdpPrincipal=new BorderPane();
        bdpPrincipal.setTop(tlbMenu);
        bdpPrincipal.setCenter(tbvIteraciones);
        TableColumn<Renglon,Integer> tbcNo=new TableColumn<>("No.");
        tbcNo.setCellValueFactory(new PropertyValueFactory<>("NO"));
        tbcNo.setReorderable(false);

        TableColumn<Renglon,Double> tbcXi_1=new TableColumn<>("Xi-1");
        tbcXi_1.setCellValueFactory(new PropertyValueFactory<>("Xi_1"));
        tbcXi_1.setReorderable(false);

        TableColumn<Renglon,Double> tbcXi=new TableColumn<>("Xi");
        tbcXi.setCellValueFactory(new PropertyValueFactory<>("Xi"));
        tbcXi.setReorderable(false);

        TableColumn<Renglon,Double> tbcFxi_1=new TableColumn<>("F(Xi-1)");
        tbcFxi_1.setCellValueFactory(new PropertyValueFactory<>("FXi_1"));
        tbcFxi_1.setReorderable(false);

        TableColumn<Renglon,Double> tbcFxi=new TableColumn<>("F(Xi)");
        tbcFxi.setCellValueFactory(new PropertyValueFactory<>("FXi"));
        tbcFxi.setReorderable(false);

        TableColumn<Renglon,Double> tbcXi1=new TableColumn<>("Xi+1");
        tbcXi1.setCellValueFactory(new PropertyValueFactory<>("Xi1"));
        tbcXi1.setReorderable(false);

        TableColumn<Renglon,Double> tbcError=new TableColumn<>("% Error");
        tbcError.setCellValueFactory(new PropertyValueFactory<>("Error"));
        tbcError.setReorderable(false);

        tbvIteraciones.getColumns().addAll(tbcNo,tbcXi_1,tbcXi,tbcFxi_1,tbcFxi,tbcXi1,tbcError);
        tbvIteraciones.setItems(setRenglon());

        escena=new Scene(bdpPrincipal,500,350);
    }
    private ObservableList<Renglon> setRenglon(){
        ArrayList<Renglon> arrReng=new ArrayList<>();
        ObservableList<Renglon> renglon=FXCollections.observableArrayList();
        int no=1;
        double xi_1=RedondearA6(valorInicialAnterior),xi=RedondearA6(valorInicial),fxi_1,fxi,xi1,error=100;
        while(error>porcentajeError){
            Renglon renglonIteracion=new Renglon();
            fxi_1=RedondearA6(Double.parseDouble(objFuntion.m_evaluar((float)(xi_1))));
            fxi=RedondearA6(Double.parseDouble(objFuntion.m_evaluar((float)(xi))));
            xi1=RedondearA6((xi-(fxi*(xi_1-xi))/(fxi_1-fxi)));
            error=RedondearA6((Math.abs((xi1-xi)/xi1)*100));
            renglonIteracion.setNO(no);
            renglonIteracion.setXi_1(xi_1);
            renglonIteracion.setXi(xi);
            renglonIteracion.setFXi_1(fxi_1);
            renglonIteracion.setFXi(fxi);
            renglonIteracion.setXi1(xi1);
            renglonIteracion.setError(RedondearA6(error));
            renglon.add(renglonIteracion);
            xi_1=RedondearA6(xi);
            xi=RedondearA6(xi1);
            no++;
        }
        return renglon;
    }
    private double RedondearA6(double num){
        double res= (double) Math.round(num * 1000000) /1000000;
        return res;
    }
}