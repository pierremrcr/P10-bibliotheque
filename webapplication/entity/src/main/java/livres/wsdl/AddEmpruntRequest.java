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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="empruntType" type="{http://www.bibliotheque.com/livres-ws}empruntType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "empruntType"
})
@XmlRootElement(name = "addEmpruntRequest")
public class AddEmpruntRequest {

    @XmlElement(required = true)
    protected EmpruntType empruntType;

    /**
     * Obtient la valeur de la propriété empruntType.
     * 
     * @return
     *     possible object is
     *     {@link EmpruntType }
     *     
     */
    public EmpruntType getEmpruntType() {
        return empruntType;
    }

    /**
     * Définit la valeur de la propriété empruntType.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpruntType }
     *     
     */
    public void setEmpruntType(EmpruntType value) {
        this.empruntType = value;
    }

}
