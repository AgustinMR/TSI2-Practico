package com.tsi2.controllers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import com.tsi2.entidades.Fecha;
import com.tsi2.entidades.Sesion;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

@Stateless
@LocalBean
@Path("/sesiones")
@Produces(MediaType.APPLICATION_JSON)
public class SesionController {

    @GET
    public List<Sesion> listarSesiones(@QueryParam("filtro") String filtro, @QueryParam("pagina") int pagina, @QueryParam("desde") String desde, @QueryParam("hasta") String hasta) {
        int skip = ((pagina * 10) - 10);
        final Morphia m = new Morphia();
        m.map(Sesion.class);
        //m.mapPackage("com.tsi2.entidades", true);
        Fecha d = new Fecha(desde);
        Fecha h = new Fecha(hasta);
        final Datastore ds = m.createDatastore(new MongoClient(), "practico1");
        Query<Sesion> query = ds.createQuery(Sesion.class);
        if(filtro != null && !filtro.isEmpty()) query.field("username").equal(filtro);
        if(desde != null && !desde.isEmpty()) query.field("fecha").greaterThanOrEq(new GregorianCalendar(d.getAnio(), (d.getMes() -1), d.getDia()).getTime());
        if(hasta != null && !hasta.isEmpty()) query.field("fecha").lessThanOrEq(new GregorianCalendar(h.getAnio(), (h.getMes()-1), h.getDia()).getTime());
        return query.asList(new FindOptions().skip(skip).limit(10));
    }

}
