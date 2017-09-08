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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

@Stateless
@LocalBean
@Path("/sesiones")
@Produces(MediaType.APPLICATION_JSON)
public class SesionController {

    @GET
    public List<Sesion> listarSesiones(@QueryParam("filtro") String filtro, @QueryParam("pagina") int pagina, @QueryParam("desde") String desde, @QueryParam("hasta") String hasta) {
        Document params = new Document();
        if(filtro != null && !filtro.isEmpty()) params.append("username", new Document("$eq", filtro));
        if(desde != null && !desde.isEmpty()){
            Fecha d = new Fecha(desde);
            params.append("fecha", new Document("$gte", new GregorianCalendar(d.getAnio(), d.getMes(), d.getDia()).getTime()));
        }
        if(hasta != null && !hasta.isEmpty()){
            Fecha h = new Fecha(hasta);
            params.append("fecha", new Document("$lte", new GregorianCalendar(h.getAnio(), h.getMes(), h.getDia()).getTimeInMillis()));
        }
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoCollection<Sesion> sesiones = mongo.getDatabase("practico1").getCollection("sesiones", Sesion.class);
        int skip = ((pagina * 10) - 10);
        List<Sesion> ret = new ArrayList<>();
        sesiones.find(params).skip(skip).limit(10).sort(Sorts.descending("fecha")).into(ret).toArray();
        return ret;
    }

}
