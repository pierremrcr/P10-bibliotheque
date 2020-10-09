package com.bibliotheque.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class ReservationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_resa")
    private Date dateResa;

    @Column(name = "num_position_resa")
    private Integer numPositionResa;

    @Column(name = "statut")
    private String statut;

    @Column(name = "livreid")
    private Integer livreId;

    @Column(name = "membreid")
    private Integer membreid;

    @ManyToOne
    @JoinColumn(name = "livreid", insertable = false, updatable = false)
    private LivreEntity livreReservation;

    public ReservationEntity(){
    }

    public ReservationEntity(Date dateResa, Integer numPositionResa, String statut, Integer livreId, Integer membreid, LivreEntity livreReservation) {
        this.dateResa = dateResa;
        this.numPositionResa = numPositionResa;
        this.statut = statut;
        this.livreId = livreId;
        this.membreid = membreid;
        this.livreReservation = livreReservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateResa() {
        return dateResa;
    }

    public void setDateResa(Date dateResa) {
        this.dateResa = dateResa;
    }

    public Integer getNumPositionResa() {
        return numPositionResa;
    }

    public void setNumPositionResa(Integer numPositionResa) {
        this.numPositionResa = numPositionResa;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getLivreId() {
        return livreId;
    }

    public void setLivreId(Integer livreId) {
        this.livreId = livreId;
    }

    public Integer getMembreid() {
        return membreid;
    }

    public void setMembreid(Integer membreid) {
        this.membreid = membreid;
    }

    public LivreEntity getLivreReservation() {
        return livreReservation;
    }

    public void setLivreReservation(LivreEntity livreReservation) {
        this.livreReservation = livreReservation;
    }
}