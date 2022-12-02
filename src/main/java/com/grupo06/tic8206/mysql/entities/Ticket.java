package com.grupo06.tic8206.mysql.entities;

import org.springframework.data.annotation.PersistenceConstructor;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ticket database table.
 *
 */
@Entity
@Table(name="ticket")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceConstructor
    public Ticket(BigDecimal precio, String tipo) {
        this.precio = precio;
        this.tipo = tipo;
    }

    @Id
    @Column(name="num_referencia")
    private int numReferencia;

    private BigDecimal precio;

    private String tipo;

 /*   //bi-directional many-to-one association to Evento
    @ManyToOne
    @JoinColumn(name="evento")
    private Evento eventoBean;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuarioBean;
*/
    public Ticket() {
    }

    public int getNumReferencia() { return this.numReferencia; }

    public void setNumReferencia(int numReferencia) { this.numReferencia = numReferencia; }

    public BigDecimal getPrecio() { return this.precio; }

    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

 /*   public Evento getEventoBean() {
        return this.eventoBean;
    }

    public void setEventoBean(Evento eventoBean) {
        this.eventoBean = eventoBean;
    }

    public Usuario getUsuarioBean() {
        return this.usuarioBean;
    }

    public void setUsuarioBean(Usuario usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

  */

}