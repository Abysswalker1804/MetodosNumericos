package org.example.metodosnumericos1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Renglon implements Serializable {
    public int NO;
    public double Xi_1;
    public double Xi;
    public double FXi_1;
    public double FXi;
    public double Xi1;
    public double Error;

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public double getXi_1() {
        return Xi_1;
    }

    public void setXi_1(double xi_1) {
        Xi_1 = xi_1;
    }

    public double getXi() {
        return Xi;
    }

    public void setXi(double xi) {
        Xi = xi;
    }

    public double getFXi_1() {
        return FXi_1;
    }

    public void setFXi_1(double FXi_1) {
        this.FXi_1 = FXi_1;
    }

    public double getFXi() {
        return FXi;
    }

    public void setFXi(double FXi) {
        this.FXi = FXi;
    }

    public double getXi1() {
        return Xi1;
    }

    public void setXi1(double xi1) {
        Xi1 = xi1;
    }

    public double getError() {
        return Error;
    }

    public void setError(double error) {
        DecimalFormat ft=new DecimalFormat("0.######");
        Error =Double.parseDouble(ft.format(error));;
    }

    public ObservableList<Renglon> getRenglon(int no, double xi_1, double xi, double fxi_1, double fxi, double xi1, double error){
        ObservableList<Renglon> listaRenglon= FXCollections.observableArrayList();
        Renglon objReng=new Renglon();
        objReng.setNO(no);
        objReng.setXi_1(xi_1);
        objReng.setXi(xi);
        objReng.setFXi_1(fxi_1);
        objReng.setFXi(fxi);
        objReng.setXi1(xi1);
        objReng.setError(error);
        listaRenglon.add(objReng);
        return listaRenglon;
    }
}

