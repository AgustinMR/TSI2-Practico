package com.tsi2.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.bson.types.ObjectId;

public class Sesion implements Serializable {

    private ObjectId id;
    private String username;
    private String rol;
    private Date fecha;
    private String hora;
    private String userAgent;
    private String so;
    
    public Sesion() {
    }

    public Sesion(ObjectId id, String username, String rol, Date fecha, String hora, String userAgent, String so) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.fecha = fecha;
        this.hora = hora;
        this.userAgent = userAgent;
        this.so = so;
        this.fecha = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE)).getTime();
        this.hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
    }

    public Sesion(String username, String rol, String userAgent, String so) {
        this.username = username;
        this.rol = rol;
        this.userAgent = userAgent;
        this.so = so;
        this.fecha = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE)).getTime();
        this.hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }
    
}
