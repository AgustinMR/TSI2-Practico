package com.tsi2.businessLogic;

import com.tsi2.dataAccess.DALRolBeanLocal;
import com.tsi2.dataAccess.DALUsuarioBeanLocal;
import com.tsi2.entidades.Rol;
import com.tsi2.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BLLUsuarioBean implements BLLUsuarioBeanLocal {

    @Inject
    private DALUsuarioBeanLocal usuarios;
    @Inject
    private DALRolBeanLocal roles;

    @Override
    public boolean addUsuario(String nombre, String apellido, String username, String email, String password, int rolId) {
        Usuario x = usuarios.find(username);
        if (x != null) {
            return false;
        }
        Rol r = roles.find(rolId);
        if (r == null) {
            return false;
        }
        return usuarios.save(new Usuario(username, password, nombre, apellido, email, r));
    }

    @Override
    public boolean updateUsuario(String nombre, String apellido, String username, String email, String password, int rolId) {
        Usuario u = usuarios.find(username);
        if (u == null) {
            return false;
        }
        Rol r = roles.find(rolId);
        if (r == null) {
            return false;
        }
        u.setName(nombre);
        u.setLastname(apellido);
        u.setEmail(email);
        u.setPassword(password);
        u.setRoleid(r);
        return usuarios.save(u);
    }

    @Override
    public boolean deleteUsuario(String username) {
        Usuario u = usuarios.find(username);
        if (u == null) {
            return false;
        }
        return usuarios.delete(u);
    }

    @Override
    public Usuario getUsuario(String username) {
        return usuarios.find(username);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarios.findAll();
    }

    @Override
    public List<Usuario> findAll(String filtro, int pagina) {
        if(pagina <= 0) pagina = 1;
        return usuarios.findAll(filtro, pagina);
    }

}
