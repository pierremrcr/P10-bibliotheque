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
 *         &lt;element name="serviceStatus" type="{http://www.bibliotheque.com/livres-ws}serviceStatus"/&gt;
 *         &lt;element name="reservationType" type="{http://www.bibliotheque.com/livres-ws}reservationType"/&gt;
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
    "serviceStatus",
    "reservationType"
})
@XmlRootElement(name = "updateReservationResponse")
public class UpdateReservationResponse {

    @XmlElement(required = true)
    protected ServiceStatus serviceStatus;
    @XmlElement(required = true)
    protected ReservationType reservationType;

    /**
     * Obtient la valeur de la propriété serviceStatus.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Définit la valeur de la propriété serviceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setServiceStatus(ServiceStatus value) {
        this.serviceStatus = value;
    }

    /**
     * Obtient la valeur de la propriété reservationType.
     * 
     * @return
     *     possible object is
     *     {@link ReservationType }
     *     
     */
    public ReservationType getReservationType() {
        return reservationType;
    }

    /**
     * Définit la valeur de la propriété reservationType.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationType }
     *     
     */
    public void setReservationType(ReservationType value) {
        this.reservationType = value;
    }

}
