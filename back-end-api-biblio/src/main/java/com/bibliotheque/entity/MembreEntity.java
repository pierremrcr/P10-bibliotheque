package com.bibliotheque.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="membre")
public class MembreEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    @Size(min = 1, max = 50)
    private String nom;

    @Column(name = "prenom")
    @Size(min = 1, max = 50)
    private String prenom;

    @Column(name = "adresse_mail")
    @Size(min = 1, max = 50)
    private String adresseMail;

    @Column(name = "mot_de_passe")
    @Size(min = 1, max = 10)
    private String motDePasse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    @Size(min = 1, max = 50)
    private String ville;

    @OneToMany(mappedBy = "membreEntity", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<EmpruntEntity> listeEmprunts;
    
    @OneToMany(mappedBy = "membreEntity", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ReservationEntity> listeReservations;

    public MembreEntity() {
    }
    

    public MembreEntity(@Size(min = 1, max = 50) String nom, @Size(min = 1, max = 50) String prenom,
			@Size(min = 1, max = 50) String adresseMail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
	}



	public MembreEntity(int id, @Size(min = 1, max = 50) String adresseMail,
			@Size(min = 1, max = 10) String motDePasse) {
		super();
		this.id = id;
		this.adresseMail = adresseMail;
		this.motDePasse = motDePasse;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<EmpruntEntity> getListeEmprunts() {
        return listeEmprunts;
    }

    public void setListeEmprunts(List<EmpruntEntity> listeEmprunts) {
        this.listeEmprunts = listeEmprunts;
        
    }
    
    public Set<ReservationEntity> getListeReservations() {
        return listeReservations;
    }

    public void setListeReservations(Set<ReservationEntity> listeReservations) {
        this.listeReservations = listeReservations;
    }
}
