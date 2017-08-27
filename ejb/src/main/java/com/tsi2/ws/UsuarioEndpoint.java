package com.tsi2.ws;

import com.tsi2.businessLogic.BLLUsuarioBeanLocal;
import com.tsi2.entidades.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "UsuarioEndpoint")
@Stateless()
public class UsuarioEndpoint {

    @EJB
    private BLLUsuarioBeanLocal ejbRef;

    @WebMethod(operationName = "addUsuario")
    public boolean addUsuario(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "username") String username, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "rolId") int rolId) {
        return ejbRef.addUsuario(nombre, apellido, username, email, password, rolId);
    }

    @WebMethod(operationName = "updateUsuario")
    public boolean updateUsuario(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "username") String username, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "rolId") int rolId) {
        return ejbRef.updateUsuario(nombre, apellido, username, email, password, rolId);
    }

    @WebMethod(operationName = "deleteUsuario")
    public boolean deleteUsuario(@WebParam(name = "username") String username) {
        return ejbRef.deleteUsuario(username);
    }

    @WebMethod(operationName = "getUsuario")
    public Usuario getUsuario(@WebParam(name = "username") String username) {
        return ejbRef.getUsuario(username);
    }

    @WebMethod(operationName = "getUsuarios")
    public List<Usuario> getUsuarios() {
        return ejbRef.getUsuarios();
    }

    @WebMethod(operationName = "findAll")
    public List<Usuario> findAll(@WebParam(name = "filtro") String filtro, @WebParam(name = "pagina") int pagina) {
        return ejbRef.findAll(filtro, pagina);
    }
    
}
