package org.cunoc.practica_banco_ipc2.Usuario;

public class Empleado extends Usuario{

    private int codigoUsuario;
    private TipoTurno idTurno;

    public Empleado() {
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public TipoTurno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(TipoTurno idTurno) {
        this.idTurno = idTurno;
    }
}
