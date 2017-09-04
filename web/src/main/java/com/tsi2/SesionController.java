package com.tsi2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@WebServlet(name = "SesionController", urlPatterns = {"/sesiones"}, value = "/sesiones")
public class SesionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        if (req.getSession().getAttribute("username") != null) {
            if (req.getSession().getAttribute("rol").equals("Admin")) {
                ctx.setVariable("username", req.getSession().getAttribute("username").toString());
                ctx.setVariable("rol", req.getSession().getAttribute("rol").toString());
                templateEngine.process("sesion", ctx, resp.getWriter());
            } else {
                ctx.setVariable("error", "permiso");
                templateEngine.process("index", ctx, resp.getWriter());
            }
        } else {
            ctx.setVariable("error", "sesion");
            templateEngine.process("index", ctx, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        if (req.getSession().getAttribute("username") != null) {
            if (req.getSession().getAttribute("rol").equals("Admin")) {
                ctx.setVariable("username", req.getSession().getAttribute("username").toString());
                ctx.setVariable("rol", req.getSession().getAttribute("rol").toString());
                templateEngine.process("sesion", ctx, resp.getWriter());
            } else {
                ctx.setVariable("error", "permiso");
                templateEngine.process("index", ctx, resp.getWriter());
            }
        } else {
            ctx.setVariable("error", "sesion");
            templateEngine.process("index", ctx, resp.getWriter());
        }
    }
    
}
