package org.cunoc.practica_banco_ipc2.Servlets.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cunoc.practica_banco_ipc2.Cuenta.Cuenta;
import org.cunoc.practica_banco_ipc2.DB.BancoDB;
import org.cunoc.practica_banco_ipc2.DB.Conector;
import org.cunoc.practica_banco_ipc2.Usuario.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InicioClienteServlet", urlPatterns = "/cliente/inicio-servlet")
public class InicioClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
        Conector conector = (Conector) req.getSession().getAttribute("conector");
        BancoDB bancoDB = new BancoDB(conector);
        List<Cuenta> cuentas = null;
        try {
             cuentas = bancoDB.listarCuentasUsuario(String.valueOf(cliente.getCodigo()));
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            String alerta = "Cuentas no encontradas";
        }
        req.setAttribute("cuentas",cuentas);
        req.getRequestDispatcher("/areas/cliente/inicio.jsp").forward(req,resp);
    }
}
