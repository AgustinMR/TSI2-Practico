package com.tsi2.businessLogic;

import com.tsi2.dataAccess.DALRolBeanLocal;
import com.tsi2.entidades.Rol;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BLLRolBean implements BLLRolBeanLocal {

    @Inject
    private DALRolBeanLocal roles;

    @Override
    public boolean addRol(String nombre, String descripcion) {
        return roles.save(new Rol(nombre, descripcion));
    }

    @Override
    public boolean updateRol(int id, String nombre, String descripcion) {
        Rol r = roles.find(id);
        if (r == null) return false;
        r.setName(nombre);
        r.setDescription(descripcion);
        return roles.save(r);
    }

    @Override
    public Rol getRol(int id) {
        return roles.find(id);
    }

    @Override
    public List<Rol> getRoles() {
        return roles.findAll();
    }

    @Override
    public boolean deleteRol(int id) {
        Rol r = roles.find(id);
        if (r != null) return roles.delete(r);
        return false;
    }
}
