package com.tsi2.entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class Fecha implements Serializable {
    private int anio;
    private int mes;
    private int dia;

    public Fecha(int anio, int mes, int dia) {
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
    }

    public Fecha() {
        this.anio = Calendar.YEAR;
        this.mes = Calendar.MONTH;
        this.dia = Calendar.DATE;
    }

    @Override
    public String toString() {
        return (anio + "/" + mes + "/" + dia);
    }

    //Contruye la fecha con un string de esta forma "dd/mm/yyyy"
    public Fecha(String bdformato) {
        try {
            String[] parts = bdformato.split("/");
            this.dia = Integer.parseInt(parts[0]);
            this.mes = Integer.parseInt(parts[1]);
            this.anio = Integer.parseInt(parts[2]);
        } catch (NumberFormatException ex) {
            String[] parts = bdformato.split("-");
            this.dia = Integer.parseInt(parts[0]);
            this.mes = Integer.parseInt(parts[1]);
            this.anio = Integer.parseInt(parts[2]);
        }

    }

    public int getAnio() {
        return this.anio;
    }

    public int getMes() {
        return this.mes;
    }

    public int getDia() {
        return this.dia;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

}