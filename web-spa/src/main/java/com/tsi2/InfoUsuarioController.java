package com.tsi2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InfoUsuarioController", urlPatterns = {"/info"}, value = "/info")
public class InfoUsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        if(req.getSession().getAttribute("username") != null) resp.getWriter().write(req.getSession().getAttribute("rol").toString());
    }
    
}
