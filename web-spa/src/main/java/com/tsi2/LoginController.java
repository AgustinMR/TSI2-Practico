package com.tsi2;

import com.tsi2.entidades.Usuario;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            if (username != null && password == null) {
                Usuario u = em.find(Usuario.class, username);
                if (u != null) {
                    if (u.getPassword().equals(password)) {
                        req.getSession().setAttribute("username", u.getUsername());
                        if (u.getRoleid().getId() == 1) {
                            req.getSession().setAttribute("rol", "Admin");
                        } else {
                            req.getSession().setAttribute("rol", "Usuario");
                        }
                        resp.getWriter().write("true");
                    } else {
                        resp.getWriter().write("false");
                    }
                } else {
                    resp.getWriter().write("false");
                }
            } else {
                resp.getWriter().write("false");
            }
        } else {
            resp.getWriter().write("false");
        }
    }

}
