package com.ucatolica.easyevent.easyevent.persitencia;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelacionClienteEventoId implements Serializable {
    private static final long serialVersionUID = 5310442601186965295L;
    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    @Column(name = "evento_id", nullable = false)
    private Integer eventoId;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RelacionClienteEventoId entity = (RelacionClienteEventoId) o;
        return Objects.equals(this.eventoId, entity.eventoId) &&
                Objects.equals(this.clienteId, entity.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, clienteId);
    }

}