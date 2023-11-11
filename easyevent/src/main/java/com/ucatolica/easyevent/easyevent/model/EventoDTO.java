package com.ucatolica.easyevent.easyevent.model;

import org.antlr.v4.runtime.misc.NotNull;

public class EventoDTO {
    @NotNull
    private Evento evento;
    private Integer Idproveedor;

    public EventoDTO(Evento evento, Integer idproveedor) {
        this.evento = evento;
        Idproveedor = idproveedor;
    }

    public EventoDTO() {
    }

    public Evento getEvento() {
        return evento;
    }


    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getIdproveedor() {
        return Idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        Idproveedor = idproveedor;
    }
}
