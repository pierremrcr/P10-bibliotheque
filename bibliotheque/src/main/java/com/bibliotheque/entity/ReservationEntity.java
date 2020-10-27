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

    @Column(name = "date_dispo")
    private Date dateDispo;

    @Column(name = "num_position_resa")
    private Integer numPositionResa;

    @Column(name = "statut")
    private String statut;

    @Column(name = "livreid")
    private Integer livreId;

    @Column(name = "membreid")
    private Integer membreid;

    @ManyToOne
    @JoinColumn(name = "membreid", insertable = false, updatable = false)
    private MembreEntity membreEntity;

    @ManyToOne
    @JoinColumn(name = "livreid", insertable = false, updatable = false)
    private LivreEntity livreEntity;

    public ReservationEntity(){
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDispo() {
        return dateDispo;
    }

    public void setDateDispo(Date dateDispo) {
        this.dateDispo = dateDispo;
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

    public MembreEntity getMembreEntity() {
        return membreEntity;
    }

    public void setMembreEntity(MembreEntity membreEntity) {
        this.membreEntity = membreEntity;
    }

    public LivreEntity getLivreEntity() {
        return livreEntity;
    }

    public void setLivreEntity(LivreEntity livreEntity) {
        this.livreEntity = livreEntity;
    }

}