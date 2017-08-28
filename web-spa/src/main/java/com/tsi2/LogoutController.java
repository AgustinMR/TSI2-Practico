package com.tsi2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout"}, value = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        if (req.getSession().getAttribute("username") != null) {
            req.getSession().removeAttribute("username");
            req.getSession().removeAttribute("rol");
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

}
