package com.ucatolica.easyevent.easyevent.persitencia;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelacionProveedorEventoId implements Serializable {
    private static final long serialVersionUID = -778036857955978311L;
    @Column(name = "proveedor_id", nullable = false)
    private Integer proveedorId;

    @Column(name = "evento_id", nullable = false)
    private Integer eventoId;

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
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
        RelacionProveedorEventoId entity = (RelacionProveedorEventoId) o;
        return Objects.equals(this.eventoId, entity.eventoId) &&
                Objects.equals(this.proveedorId, entity.proveedorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, proveedorId);
    }

}