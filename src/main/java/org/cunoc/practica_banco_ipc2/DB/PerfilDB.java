package org.cunoc.practica_banco_ipc2.DB;

import jakarta.servlet.http.HttpSession;
import org.cunoc.practica_banco_ipc2.Usuario.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilDB {

    private Conector conector;

    public PerfilDB(Conector conector) {
        this.conector = conector;
    }

    public Usuario encontrarUsuario(String password) throws SQLException {
        String findQuery = String.format("SELECT * FROM usuario WHERE password = MD5(%s)",password);
        ResultSet resultSet = conector.selectFrom(findQuery);
        Usuario usuario = new Usuario();
        if (resultSet.next()){
            usuario.setCodigo(resultSet.getInt("codigo"));
            usuario.setCui(resultSet.getString("noIdentificacion"));
            usuario.setDireccion(resultSet.getString("direccion"));
            usuario.setTipoUsuario(TipoUsuario.clasificarTipoUsuario(resultSet.getInt("tipoUsuario")));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setGenero(Genero.clasificarGenero(resultSet.getString("sexo")));
        }
        return usuario;
    }

    public Usuario definirUsuarioPorNivel(Usuario usuario, HttpSession session) throws SQLException {
        TipoUsuario tipo = usuario.getTipoUsuario();
        switch (tipo){
            case CLIENTE:
                Cliente cliente = buscarCliente(usuario);
                session.setAttribute("cliente",cliente);
                return cliente;
            case EMPLEADO:
                Empleado empleado = buscarEmpleado(usuario);
                session.setAttribute("empleado",empleado);
                return empleado;
            case GERENTE:
                Empleado gerente = buscarEmpleado(usuario);
                session.setAttribute("gerente",gerente);
                return gerente;
            default:
                return null;
        }
    }

    public Cliente buscarCliente(Usuario usuario) throws SQLException {
        String findQuery = String.format("SELECT * FROM cliente WHERE codigoUsuario = %s",usuario.getCodigo());
        ResultSet resultSet = conector.selectFrom(findQuery);
        Cliente cliente = new Cliente();
        if (resultSet.next()){
            cliente.setDOB(resultSet.getDate("birth"));
            cliente.setPdfDPI(resultSet.getBlob("pdfDPI"));
            cliente.setCodigo(usuario.getCodigo());
            cliente.setCui(usuario.getCui());
            cliente.setDireccion(usuario.getDireccion());
            cliente.setTipoUsuario(usuario.getTipoUsuario());
            cliente.setNombre(usuario.getNombre());
            cliente.setGenero(usuario.getGenero());
        } else {
            throw new SQLException();
        }
        return cliente;
    }

    public Empleado buscarEmpleado(Usuario usuario) throws SQLException {
        String empleadoQuery = String.format("SELECT * FROM empleado WHERE codigoUsuario = %s",usuario.getCodigo());
        ResultSet resultSet = conector.selectFrom(empleadoQuery);
        Empleado empleado = new Empleado();
        if (resultSet.next()){
            empleado.setIdTurno(TipoTurno.clasificarTurno(resultSet.getInt("idTurno")));
            empleado.setCodigo(usuario.getCodigo());
            empleado.setCui(usuario.getCui());
            empleado.setDireccion(usuario.getDireccion());
            empleado.setTipoUsuario(usuario.getTipoUsuario());
            empleado.setNombre(usuario.getNombre());
            empleado.setGenero(usuario.getGenero());
        }else {
            throw new SQLException();
        }
        return empleado;
    }
}
