package com.tsi2.dataAccess;

import com.tsi2.entidades.Rol;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DALRolBeanLocal {

    public boolean save(Rol r);

    public Rol find(int id);

    public List<Rol> findAll();

    public boolean exists(Rol r);

    public boolean delete(Rol r);
}
