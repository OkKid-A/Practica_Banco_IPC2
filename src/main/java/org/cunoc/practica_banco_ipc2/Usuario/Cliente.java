package org.cunoc.practica_banco_ipc2.Usuario;

import java.sql.Blob;
import java.util.Date;

public class Cliente extends Usuario{

    private Date DOB;
    private Blob pdfDPI;

    public Cliente() {
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Blob getPdfDPI() {
        return pdfDPI;
    }

    public void setPdfDPI(Blob pdfDPI) {
        this.pdfDPI = pdfDPI;
    }
}
