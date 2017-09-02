package com.tsi2;

import com.tsi2.entidades.Rol;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public void create() {
        Rol r = new Rol(nombre, descripcion);
        em.persist(r);
    }

    public void update() {
        Rol r = find();
        if (r != null) {
            r.setName(nombre);
            r.setDescription(descripcion);
            em.persist(r);
        }
    }

    public void delete() {
        Rol r = find();
        if (r != null) {
            em.remove(r);
        }
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
