package org.cunoc.practica_banco_ipc2.DB;

import org.cunoc.practica_banco_ipc2.Cuenta.TipoTransaccion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CajeroDB {

    private Conector conector;

    public CajeroDB(Conector conector) {
        this.conector = conector;
    }

    public void transferirEnLinea(float monto, String cuentaOrigen, String cuentaReceptora) throws SQLException {
        String addSaldo = "UPDATE cuenta SET saldo = saldo + ? WHERE codigo = ?";
        String restarSaldo = "UPDATE cuenta SET saldo = saldo - ? WHERE codigo = ?";
        String transfer = "INSERT INTO transaccion (codCuenta,tipo,fecha,hora,monto,codCajero,saldoCuenta) " +
                "VALUES (?,?,CURDATE(),CURRENT_TIME(),?,?,?)";
        String selectSaldo = "SELECT saldo FROM cuenta WHERE codigo = %s";
        conector.updateWithException(addSaldo,new String[]{
                String.valueOf(monto),cuentaReceptora
        });
        conector.updateWithException(restarSaldo,new String[]{
                String.valueOf(monto),cuentaOrigen
        });
        ResultSet montoOrigen = conector.selectFrom(String.format(selectSaldo,cuentaOrigen));
        if (montoOrigen.next()) {
            conector.updateWithException(transfer, new String[]{
                    cuentaOrigen, String.valueOf(TipoTransaccion.DEBITO),
                    String.valueOf(monto), "101", String.valueOf(montoOrigen.getFloat("saldo"))
            });
        }
        montoOrigen.close();
        ResultSet montoFinal = conector.selectFrom(String.format(selectSaldo,cuentaReceptora));
        if (montoFinal.next()) {
            conector.updateWithException(transfer, new String[]{
                    cuentaReceptora, String.valueOf(TipoTransaccion.CREDITO),
                    String.valueOf(monto), "101", String.valueOf(montoFinal.getFloat("saldo"))
            });
        }
        montoFinal.close();
    }
}
