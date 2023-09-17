package org.cunoc.practica_banco_ipc2.Servlets.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cunoc.practica_banco_ipc2.Cuenta.Cuenta;
import org.cunoc.practica_banco_ipc2.DB.BancoDB;
import org.cunoc.practica_banco_ipc2.DB.CajeroDB;
import org.cunoc.practica_banco_ipc2.DB.Conector;
import org.cunoc.practica_banco_ipc2.Usuario.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TransferirServlet", urlPatterns = "/cliente/transferir-servlet")
public class TransferirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
        Conector conector = (Conector) req.getSession().getAttribute("conector");
        String cuentaNO = req.getParameter("cuenta");
        String receptora = req.getParameter("receptora");
        String error = req.getParameter("error");
        String success = req.getParameter("success");
        BancoDB bancoDB = new BancoDB(conector);
        List<Cuenta> cuentas = null;
        String alerta = null;
        if (error!=null){
            alerta = "Hubo un error al realizar la transferencia";
            req.setAttribute("alerta",alerta);
        } else if (success!=null) {
            success = "Se realizo la transferencia con exito";
            req.setAttribute("success",success);
        }
         if (receptora!=null){
                try {
                    Cuenta cuentaReceptora = bancoDB.seleCuentaTerceros(receptora);
                    req.setAttribute("receptor",cuentaReceptora);
                } catch (SQLException e) {
                    e.printStackTrace();
                    alerta = "No se encuentra la cuenta receptora";
                }
            }
            if (cuentaNO!=null){
                try {
                    cuentas = bancoDB.listarCuentasUsuario(String.valueOf(cliente.getCodigo()),cuentaNO);
                    Cuenta cuenta = bancoDB.seleCuenta(cuentaNO);
                    req.setAttribute("cuenta",cuenta);
                    List<Cuenta> cuentasTerceros = bancoDB.selecCuentasTerceros(String.valueOf(cliente.getCodigo()));
                    req.setAttribute("cuentasTerceros",cuentasTerceros);
                } catch (SQLException e) {
                    e.printStackTrace();
                    alerta = "No hay cuenta";
                }
            }else {
                try {
                    cuentas = bancoDB.listarCuentasUsuario(String.valueOf(cliente.getCodigo()));
                } catch (SQLException | NullPointerException e) {
                    e.printStackTrace();
                    alerta = "Cuentas no encontradas";
                }
            }
            req.setAttribute("alerta",alerta);
            req.setAttribute("cuentasPropias",cuentas);
        req.getRequestDispatcher("/areas/cliente/transferir.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
        Conector conector = (Conector) req.getSession().getAttribute("conector");
        String cuentaNo = req.getParameter("cuenta");
        String receptora = req.getParameter("receptora");
        String action = req.getParameter("action");
        String monto = req.getParameter("nuevoSaldo");
        CajeroDB cajeroDB = new CajeroDB(conector);
        if (action!=null) {
            try {
                cajeroDB.transferirEnLinea(Float.parseFloat(monto),cuentaNo,receptora);
                resp.sendRedirect("../cliente/transferir-servlet?success="+1);
            } catch (SQLException |NullPointerException e) {
                e.printStackTrace();
                resp.sendRedirect("../cliente/transferir-servlet?error=transferencia");
            }
        } else if (receptora!=null){
            resp.sendRedirect("../cliente/transferir-servlet?cuenta="+cuentaNo+"&receptora="+receptora);
        }else if (cuentaNo!=null){
            resp.sendRedirect("../cliente/transferir-servlet?cuenta="+cuentaNo);
        }
    }
}
