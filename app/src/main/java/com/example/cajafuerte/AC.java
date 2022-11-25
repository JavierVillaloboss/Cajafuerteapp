package com.example.cajafuerte;

public class AC {
    String accion;
    String fecha;

    public AC() {
    }

    public AC(String accion, String fecha) {
        this.accion = accion;
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }
    public String getFecha() {
        return fecha;
    }

}
