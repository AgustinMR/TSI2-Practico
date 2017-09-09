package com.tsi2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.tsi2.entidades.Sesion;
import com.tsi2.entidades.Usuario;
import eu.bitwalker.useragentutils.UserAgent;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@WebServlet(name = "LoginController", urlPatterns = {"/login"}, value = "/login")
public class LoginController extends HttpServlet {

    @PersistenceContext(unitName = "com.tsi2_web-spa_war_1.0PU")
    private EntityManager em;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        if (req.getSession().getAttribute("username") == null) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if (username != null && password != null) {
                Usuario u = em.find(Usuario.class, username);
                if (u != null) {
                    if (u.getPassword().equals(password)) {
                        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
                        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
                        MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
                        MongoCollection<Sesion> sesiones = mongo.getDatabase("practico1").getCollection("sesiones", Sesion.class);
                        sesiones.insertOne(new Sesion(username, u.getRoleid().getName(), userAgent.getBrowser().getName(), userAgent.getOperatingSystem().getName()));
                        req.getSession().setAttribute("username", u.getUsername());
                        if (u.getRoleid().getId() == 1) {
                            req.getSession().setAttribute("rol", "Admin");
                        } else {
                            req.getSession().setAttribute("rol", "Usuario");
                        }
                        resp.getWriter().write("true");
                    } else {
                        resp.getWriter().write("false4");
                    }
                } else {
                    resp.getWriter().write("false3");
                }
            } else {
                resp.getWriter().write("false2");
            }
        } else {
            resp.getWriter().write("false1");
        }
    }

}
