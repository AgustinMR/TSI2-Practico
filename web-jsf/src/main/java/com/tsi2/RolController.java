package com.tsi2;

import com.tsi2.entidades.Rol;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "rolController")
@ViewScoped
public class RolController {

    @PersistenceContext(unitName = "com.tsi2_web-jsf_war_1.0PU")
    private EntityManager em;

    private int id;
    private String nombre;
    private String descripcion;

    public RolController() {
    }

    public void create(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        Rol r = new Rol(nombre, descripcion);
        em.persist(r);
        context.addCallbackParam("exito", true);
    }

    public Rol find() {
        return em.find(Rol.class, id);
    }

    public List<Rol> findAll() {
        return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
