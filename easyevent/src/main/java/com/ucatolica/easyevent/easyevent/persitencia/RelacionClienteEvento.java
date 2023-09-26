package com.ucatolica.easyevent.easyevent.persitencia;

import jakarta.persistence.*;

@Entity
@Table(name = "relacion_cliente_evento")
public class RelacionClienteEvento {
    @EmbeddedId
    private RelacionClienteEventoId id;

    @MapsId("eventoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

    public RelacionClienteEventoId getId() {
        return id;
    }

    public void setId(RelacionClienteEventoId id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}