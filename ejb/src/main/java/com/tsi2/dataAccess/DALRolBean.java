package com.tsi2.dataAccess;

import com.tsi2.entidades.Rol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DALRolBean implements DALRolBeanLocal {

    @PersistenceContext(unitName = "com.tsi2_ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    public boolean save(Rol r) {
        em.persist(r);
        return exists(r);
    }

    @Override
    public Rol find(int id) {
        return em.find(Rol.class, id);
    }

    @Override
    public List<Rol> findAll() {
        return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
    }

    @Override
    public boolean exists(Rol r) {
        return em.contains(r);
    }

    @Override
    public boolean delete(Rol r) {
        em.remove(r);
        return exists(r);
    }
}
