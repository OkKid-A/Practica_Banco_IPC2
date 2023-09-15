package org.cunoc.practica_banco_ipc2.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cunoc.practica_banco_ipc2.DB.Conector;
import org.cunoc.practica_banco_ipc2.DB.PerfilDB;
import org.cunoc.practica_banco_ipc2.Usuario.Cliente;
import org.cunoc.practica_banco_ipc2.Usuario.Usuario;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet (name = "LoginServlet", urlPatterns = "/login/inicio-servlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Conector conector = new Conector();
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(3600);
        session.setAttribute("conector",conector);
        PerfilDB perfil = new PerfilDB(conector);
        Usuario user = null;
        String password = req.getParameter("password");
        String error = null;
        try {
            user = perfil.encontrarUsuario(password);
            Usuario finalUser = perfil.definirUsuarioPorNivel(user,session);
            switch (finalUser.getTipoUsuario()){
                case CLIENTE:
                    resp.sendRedirect("/cliente/inicio-servlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = "No se encontro el usuario";
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Conector conector = new Conector();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("conector",conector);
        request.getSession().removeAttribute("currentUser");
        request.getSession().removeAttribute("cliente");
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
