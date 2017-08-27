package com.tsi2.controllers;

import com.tsi2.businessLogic.BLLRolBeanLocal;
import com.tsi2.entidades.Rol;
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
@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RolController {

    @Inject
    private BLLRolBeanLocal roles;
    
    @POST
    public boolean addRol(@QueryParam("nombre") String nombre, @QueryParam("descripcion") String descripcion){
        return roles.addRol(nombre, descripcion);
    }
    
    @PUT
    public boolean updateRol(@QueryParam("id") int id, @QueryParam("nombre") String nombre, @QueryParam("descripcion") String descripcion){
        return roles.updateRol(id, nombre, descripcion);
    }
    
    @DELETE
    @Path("{id}")
    public boolean deleteRol(@PathParam("id") int id){
        return roles.deleteRol(id);
    }
    
    @GET
    @Path("{id}")
    public Rol getRol(@PathParam("id") int id){
        return roles.getRol(id);
    }
    
    @GET
    public List<Rol> getRoles(){
        return roles.getRoles();
    }
    
}
