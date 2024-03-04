//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v2.3.7 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.01.23 à 07:28:04 PM CET 
//


package livres.wsdl;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="listeEmpruntsByMembreId" type="{http://www.bibliotheque.com/livres-ws}empruntType" maxOccurs="unbounded"/&gt;
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
    "listeEmpruntsByMembreId"
})
@XmlRootElement(name = "getAllEmpruntsByMembreIdResponse")
public class GetAllEmpruntsByMembreIdResponse {

    @XmlElement(required = true)
    protected List<EmpruntType> listeEmpruntsByMembreId;

    /**
     * Gets the value of the listeEmpruntsByMembreId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listeEmpruntsByMembreId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListeEmpruntsByMembreId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmpruntType }
     * 
     * 
     */
    public List<EmpruntType> getListeEmpruntsByMembreId() {
        if (listeEmpruntsByMembreId == null) {
            listeEmpruntsByMembreId = new ArrayList<EmpruntType>();
        }
        return this.listeEmpruntsByMembreId;
    }

}
