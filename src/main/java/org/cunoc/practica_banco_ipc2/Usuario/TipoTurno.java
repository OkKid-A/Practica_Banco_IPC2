package org.cunoc.practica_banco_ipc2.Usuario;

public enum TipoTurno {

    MATUTINO(1),
    VESPERTINO(2),
    GLOBAL(3);


    private final int i;

    TipoTurno(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static TipoTurno clasificarTurno(int tipo){
        switch (tipo){
            case 1:
                return MATUTINO;
            case 2:
                return VESPERTINO;
            case 3:
                return GLOBAL;
            default:
                return null;
        }
    }
}
