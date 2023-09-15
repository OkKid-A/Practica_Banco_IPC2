package org.cunoc.practica_banco_ipc2.Usuario;

public enum TipoUsuario {
    CLIENTE(0),
    EMPLEADO(2),
    GERENTE(1);


    private final int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }

    public static TipoUsuario clasificarTipoUsuario(int tipo){
        switch (tipo){
            case 0:
                return CLIENTE;
            case 2:
                return EMPLEADO;
            case 1:
                return GERENTE;
            default:
                return null;
        }
    }

    public int getTipo() {
        return tipo;
    }
}
