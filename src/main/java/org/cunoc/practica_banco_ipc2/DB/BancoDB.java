package org.cunoc.practica_banco_ipc2.DB;

import org.cunoc.practica_banco_ipc2.Cuenta.Cuenta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDB {

    private Conector conector;

    public BancoDB(Conector conector) {
        this.conector = conector;
    }

    public List<Cuenta> listarCuentasUsuario(String id) throws SQLException {
        String cuentasQuery = String.format("SELECT * FROM cuenta WHERE codigoCliente = %s",id);
        ResultSet resultSet = conector.selectFrom(cuentasQuery);
        List<Cuenta> cuentas = null;
        if (resultSet.next()){
            cuentas = listarCuentas(resultSet);
        }
        return cuentas;
    }

    public List<Cuenta> listarCuentasUsuario(String id, String cuentaLista) throws SQLException {
        String cuentasQuery = String.format("SELECT * FROM cuenta WHERE codigoCliente = %s AND codigo <> %s",id,cuentaLista);
        ResultSet resultSet = conector.selectFrom(cuentasQuery);
        List<Cuenta> cuentas = null;
        if (resultSet.next()){
            cuentas = listarCuentas(resultSet);
        }
        return cuentas;
    }

    public List<Cuenta> listarCuentas(ResultSet resultSet) throws SQLException {
        List<Cuenta> cuentas = new ArrayList<>();
        do {
            Cuenta cuenta = new Cuenta();
            cuenta.setCodigoCliente(resultSet.getInt("codigoCliente"));
            cuenta.setCodigo(resultSet.getInt("codigo"));
            cuenta.setFechaCreacion(resultSet.getDate("fechaCreacion"));
            cuenta.setSaldo(resultSet.getFloat("saldo"));
            cuentas.add(cuenta);
        }while (resultSet.next());
        return cuentas;
    }

    public Cuenta seleCuenta(String codigo) throws SQLException {
        String cuentaQuery = String.format("SELECT * FROM cuenta WHERE codigo = %s",codigo);
        ResultSet resultSet = conector.selectFrom(cuentaQuery);
        Cuenta cuenta = null;
        if (resultSet.next()){
            cuenta = new Cuenta();
            cuenta.setCodigoCliente(resultSet.getInt("codigoCliente"));
            cuenta.setCodigo(resultSet.getInt("codigo"));
            cuenta.setFechaCreacion(resultSet.getDate("fechaCreacion"));
            cuenta.setSaldo(resultSet.getFloat("saldo"));
        }
        return cuenta;
    }

    public Cuenta seleCuentaTerceros(String codigo) throws SQLException {
        String cuenta3pQuery = String.format("SELECT u.nombre,c.* FROM cuentaAsociada a " +
                "                    INNER JOIN cuenta c on a.codCuenta = c.codigo " +
                "                    INNER JOIN usuario u ON c.codigoCliente = u.codigo " +
                "WHERE a.codCuenta = %s ",codigo);
        ResultSet resultSet = conector.selectFrom(cuenta3pQuery);
        Cuenta cuenta = null;
        if (resultSet.next()){
            cuenta = new Cuenta();
            cuenta.setCodigoCliente(resultSet.getInt("codigoCliente"));
            cuenta.setCodigo(resultSet.getInt("codigo"));
            cuenta.setFechaCreacion(resultSet.getDate("fechaCreacion"));
            cuenta.setSaldo(resultSet.getFloat("saldo"));
            cuenta.setNombreCliente(resultSet.getString("nombre"));
        }
        return cuenta;
    }

    public List<Cuenta> selecCuentasTerceros(String codigoCliente) throws SQLException {
        List<Cuenta> cuentasTerceros = new ArrayList<>();
        String tercerosQuery = String.format("SELECT u.nombre,c.* FROM cuentaAsociada a " +
                "                    INNER JOIN cuenta c on a.codCuenta = c.codigo " +
                "                    INNER JOIN usuario u ON c.codigoCliente = u.codigo " +
                "WHERE a.codCliente = %s",codigoCliente);
        ResultSet resultSet = conector.selectFrom(tercerosQuery);
        if (resultSet.next()){
            cuentasTerceros = listarCuentasTerceros(resultSet);
        }
        return cuentasTerceros;
    }

    public List<Cuenta> listarCuentasTerceros(ResultSet resultSet) throws SQLException {
        List<Cuenta> cuentas = new ArrayList<>();
        do {
            Cuenta cuenta = new Cuenta();
            cuenta.setCodigoCliente(resultSet.getInt("codigoCliente"));
            cuenta.setCodigo(resultSet.getInt("codigo"));
            cuenta.setFechaCreacion(resultSet.getDate("fechaCreacion"));
            cuenta.setSaldo(resultSet.getFloat("saldo"));
            cuenta.setNombreCliente(resultSet.getString("nombre"));
            cuentas.add(cuenta);
        }while (resultSet.next());
        return cuentas;
    }
}
