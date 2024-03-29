//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v2.3.7 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.01.23 à 07:28:04 PM CET 
//


package livres.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour reservationType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateDispo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numPositionResa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="statut" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="livreid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="livreEntity" type="{http://www.bibliotheque.com/livres-ws}livreType"/&gt;
 *         &lt;element name="membreid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="membreEntity" type="{http://www.bibliotheque.com/livres-ws}membreType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationType", propOrder = {
    "id",
    "dateDispo",
    "numPositionResa",
    "statut",
    "livreid",
    "livreEntity",
    "membreid",
    "membreEntity"
})
public class ReservationType {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDispo;
    protected int numPositionResa;
    @XmlElement(required = true)
    protected String statut;
    protected int livreid;
    @XmlElement(required = true)
    protected LivreType livreEntity;
    protected int membreid;
    @XmlElement(required = true)
    protected MembreType membreEntity;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété dateDispo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDispo() {
        return dateDispo;
    }

    /**
     * Définit la valeur de la propriété dateDispo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDispo(XMLGregorianCalendar value) {
        this.dateDispo = value;
    }

    /**
     * Obtient la valeur de la propriété numPositionResa.
     * 
     */
    public int getNumPositionResa() {
        return numPositionResa;
    }

    /**
     * Définit la valeur de la propriété numPositionResa.
     * 
     */
    public void setNumPositionResa(int value) {
        this.numPositionResa = value;
    }

    /**
     * Obtient la valeur de la propriété statut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de la propriété statut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatut(String value) {
        this.statut = value;
    }

    /**
     * Obtient la valeur de la propriété livreid.
     * 
     */
    public int getLivreid() {
        return livreid;
    }

    /**
     * Définit la valeur de la propriété livreid.
     * 
     */
    public void setLivreid(int value) {
        this.livreid = value;
    }

    /**
     * Obtient la valeur de la propriété livreEntity.
     * 
     * @return
     *     possible object is
     *     {@link LivreType }
     *     
     */
    public LivreType getLivreEntity() {
        return livreEntity;
    }

    /**
     * Définit la valeur de la propriété livreEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link LivreType }
     *     
     */
    public void setLivreEntity(LivreType value) {
        this.livreEntity = value;
    }

    /**
     * Obtient la valeur de la propriété membreid.
     * 
     */
    public int getMembreid() {
        return membreid;
    }

    /**
     * Définit la valeur de la propriété membreid.
     * 
     */
    public void setMembreid(int value) {
        this.membreid = value;
    }

    /**
     * Obtient la valeur de la propriété membreEntity.
     * 
     * @return
     *     possible object is
     *     {@link MembreType }
     *     
     */
    public MembreType getMembreEntity() {
        return membreEntity;
    }

    /**
     * Définit la valeur de la propriété membreEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link MembreType }
     *     
     */
    public void setMembreEntity(MembreType value) {
        this.membreEntity = value;
    }

}
