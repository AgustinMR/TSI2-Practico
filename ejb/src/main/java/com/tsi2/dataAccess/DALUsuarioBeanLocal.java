package com.tsi2.dataAccess;

import com.tsi2.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DALUsuarioBeanLocal {

    public boolean save(Usuario u);

    public boolean delete(Usuario u);

    public Usuario find(String username);

    public boolean exists(Usuario u);

    public List<Usuario> findAll();

    public List<Usuario> findAll(String filtro, int pagina);
}
