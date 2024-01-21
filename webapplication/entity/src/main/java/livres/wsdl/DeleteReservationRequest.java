//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v2.3.7 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.01.21 à 10:39:21 AM CET 
//


package livres.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "reservationId"
})
@XmlRootElement(name = "deleteReservationRequest")
public class DeleteReservationRequest {

    protected int reservationId;

    /**
     * Obtient la valeur de la propriété reservationId.
     * 
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Définit la valeur de la propriété reservationId.
     * 
     */
    public void setReservationId(int value) {
        this.reservationId = value;
    }

}
