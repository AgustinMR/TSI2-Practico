package com.tsi2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.tsi2.entidades.Sesion;
import com.tsi2.entidades.Usuario;
import eu.bitwalker.useragentutils.UserAgent;
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
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@WebServlet(name = "LoginController", urlPatterns = {"/login"}, value = "/login")
public class LoginController extends HttpServlet {

    @PersistenceContext(unitName = "com.tsi2_web_war_1.0PU")
    private EntityManager em;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username != null && password != null) {
            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setTemplateMode(TemplateMode.HTML);
            templateResolver.setPrefix("");
            templateResolver.setSuffix(".html");
            templateResolver.setCacheTTLMs(3600000L);
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            WebContext ctx = new WebContext(req, resp, getServletConfig().getServletContext(), req.getLocale());
            resp.setContentType("text/html;charset=UTF-8");
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 1000);
            Usuario u = em.find(Usuario.class, username);
            if (u != null) {
                if (u.getPassword().equals(password)) {
                    UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
                    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
                    MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
                    MongoCollection<Sesion> sesiones = mongo.getDatabase("practico1").getCollection("sesiones", Sesion.class);
                    sesiones.insertOne(new Sesion(username, u.getRoleid().getName(), userAgent.getBrowser().getName(), userAgent.getOperatingSystem().getName()));
                    req.getSession().setAttribute("username", username);
                    if (u.getRoleid().getId() == 1) {
                        req.getSession().setAttribute("rol", "Admin");
                        req.getRequestDispatcher("/admin").forward(req, resp);
                    } else {
                        req.getSession().setAttribute("rol", "Usuario");
                        req.getRequestDispatcher("/usuario").forward(req, resp);
                    }
                } else {
                    ctx.setVariable("error", "usuario");
                    templateEngine.process("index", ctx, resp.getWriter());
                }
            } else {
                ctx.setVariable("error", "usuario");
                templateEngine.process("index", ctx, resp.getWriter());
            }
        } else {
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
    }

}
