package com.tsi2.businessLogic;

import com.tsi2.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BLLUsuarioBeanLocal {

    public boolean addUsuario(String nombre, String apellido, String username, String email, String password, int rolId);

    public boolean updateUsuario(String nombre, String apellido, String username, String email, String password, int rolId);

    public boolean deleteUsuario(String username);

    public Usuario getUsuario(String username);

    public List<Usuario> getUsuarios();
    
    public List<Usuario> findAll(String filtro, int pagina);
}
