package com.tsi2.controllers;

import com.tsi2.businessLogic.BLLUsuarioBeanLocal;
import com.tsi2.entidades.Usuario;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    private BLLUsuarioBeanLocal usuarios;

    @POST
    public boolean addUsuario(@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("rol") int rolId) {
        return usuarios.addUsuario(nombre, apellido, username, email, password, rolId);
    }

    @PUT
    public boolean updateUsuario(@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("rol") int rolId) {
        return usuarios.updateUsuario(nombre, apellido, username, email, password, rolId);
    }

    @DELETE
    @Path("{username}")
    public boolean deleteUsuario(@PathParam("username") String username) {
        return usuarios.deleteUsuario(username);
    }

    @GET
    @Path("{username}")
    public Usuario getUsuario(@PathParam("username") String username) {
        return usuarios.getUsuario(username);
    }

    @GET
    public List<Usuario> getUsuarios(@QueryParam("filtro") String filtro, @QueryParam("pagina") int pagina) {
        return usuarios.findAll(filtro, pagina);
    }

    @GET
    @Path("/cualquiera/{pagina}")
    public List<Usuario> getUsuarios_cualquiera(@PathParam("pagina") int pagina, @QueryParam("filtro") String filtro) {
        return usuarios.findAll(filtro, pagina);
    }

}
