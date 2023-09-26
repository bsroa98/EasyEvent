package com.ucatolica.easyevent.easyevent.persitencia;

import jakarta.persistence.*;

@Entity
@Table(name = "relacion_proveedor_evento")
public class RelacionProveedorEvento {
    @EmbeddedId
    private RelacionProveedorEventoId id;

    @MapsId("eventoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    public RelacionProveedorEventoId getId() {
        return id;
    }

    public void setId(RelacionProveedorEventoId id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

}