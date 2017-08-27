package com.tsi2.ws;

import com.tsi2.businessLogic.BLLRolBeanLocal;
import com.tsi2.entidades.Rol;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "RolEndpoint")
@Stateless()
public class RolEndpoint {

    @EJB
    private BLLRolBeanLocal roles;

    @WebMethod(operationName = "addRol")
    public boolean addRol(@WebParam(name = "nombre") String nombre, @WebParam(name = "descripcion") String descripcion) {
        return roles.addRol(nombre, descripcion);
    }

    @WebMethod(operationName = "updateRol")
    public boolean updateRol(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "descripcion") String descripcion) {
        return roles.updateRol(id, nombre, descripcion);
    }

    @WebMethod(operationName = "getRol")
    public Rol getRol(@WebParam(name = "id") int id) {
        return roles.getRol(id);
    }

    @WebMethod(operationName = "getRoles")
    public List<Rol> getRoles() {
        return roles.getRoles();
    }

    @WebMethod(operationName = "deleteRol")
    public boolean deleteRol(@WebParam(name = "id") int id) {
        return roles.deleteRol(id);
    }
    
}
