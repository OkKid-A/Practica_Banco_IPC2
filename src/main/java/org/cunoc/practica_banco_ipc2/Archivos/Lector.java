package org.cunoc.practica_banco_ipc2.Archivos;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class Lector {

    public Lector() {
    }

    public File convertirBlob(Blob blob) throws SQLException, FileNotFoundException {
        File pdf = new File("");
        InputStream in = blob.getBinaryStream();
        OutputStream out = new FileOutputStream(pdf);
        return pdf;
    }
}
