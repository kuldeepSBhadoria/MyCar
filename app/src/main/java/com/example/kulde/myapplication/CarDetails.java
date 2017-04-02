package com.example.kulde.myapplication;

/**
 * Created by kulde on 10/14/2016.
 */
public class CarDetails {
        int id;
        String Carname;

    int  Year;
    double MilesCA;
    String  UTC;
    double  MilesFA;
    double FuelQty;
    double  Amount;

    public CarDetails(){

    }

    public CarDetails( String carname, int year, double milesCA,  String UTC,double milesFA,double fuelQty,double amount ) {
        this.Amount = amount;
        this.FuelQty = fuelQty;
        this.MilesFA = milesFA;
        this.UTC = UTC;
        this.MilesCA = milesCA;
        this.Year = year;
        this.Carname = carname;
    }
    public CarDetails( int id,String carname, int year, double milesCA,  String UTC,double milesFA,double fuelQty,double amount ) {
        Amount = amount;
        FuelQty = fuelQty;
        MilesFA = milesFA;
        this.UTC = UTC;
        MilesCA = milesCA;
        Year = year;
        this.id = id;
        Carname = carname;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarname() {
        return Carname;
    }

    public void setCarname(String carname) {
        Carname = carname;
    }

    public double getMilesCA() {
        return MilesCA;
    }

    public void setMilesCA(double milesCA) {
        MilesCA = milesCA;
    }

    public String getUTC() {
        return UTC;
    }

    public void setUTC(String UTC) {
        this.UTC = UTC;
    }

    public double getMilesFA() {
        return MilesFA;
    }

    public void setMilesFA(double milesFA) {
        MilesFA = milesFA;
    }

    public double getFuelQty() {
        return FuelQty;
    }

    public void setFuelQty(double fuelQty) {
        FuelQty = fuelQty;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }



}
