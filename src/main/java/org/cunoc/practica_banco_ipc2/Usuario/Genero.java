package org.cunoc.practica_banco_ipc2.Usuario;

public enum Genero {

    MASCULINO,
    FEMENINO,
    OTRO;

    public static Genero clasificarGenero(String genero){
        switch (genero){
            case "MASCULINO":
                return MASCULINO;
            case "FEMENINO":
                return FEMENINO;
            default:
                return null;
        }
    }
}
