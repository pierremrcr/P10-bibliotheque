package com.bibliotheque.web.controller;

import com.bibliotheque.service.EmpruntService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.MembreService;
import com.bibliotheque.service.ReservationService;
import livres.wsdl.EmpruntType;
import livres.wsdl.LivreType;
import livres.wsdl.MembreType;
import livres.wsdl.ReservationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.util.*;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MembreService membreService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private EmpruntService empruntService;

    @RequestMapping(value ="/addReservation", method = RequestMethod.GET)
    public String addReservation(Model model,
                                 @RequestParam(name="livreId") Integer livreId,
                                 @RequestParam(name="compteId") Integer compteId) throws ParseException, DatatypeConfigurationException {

        List<EmpruntType> listeEmprunts = empruntService.getAllEmpruntsByLivreId(livreId);
        XMLGregorianCalendar dateFin = null;

        if(listeEmprunts.size() >0){

            List<Long> joursRestantsEmprunts = empruntService.joursRestantsEmprunt(listeEmprunts);

            listeEmprunts = empruntService.trieEmpruntsParDateDeFin(listeEmprunts, joursRestantsEmprunts);
            dateFin = listeEmprunts.get(0).getDateFin();

        }


        MembreType membre = membreService.membreById(compteId);
        ReservationType reservation = new ReservationType();
        LivreType livre = livreService.livreById(livreId);

        GregorianCalendar calendar = dateFin.toGregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        XMLGregorianCalendar dateDispo = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        reservation.setDateDispo(dateDispo);
        reservation.setStatut("réservé");
        reservation.setLivreid(livreId);
        reservation.setMembreid(compteId);
        reservationService.addReservation(reservation);

        return "confirmationReservation";

    }

    @RequestMapping(value="/annulerReservation", method = RequestMethod.GET)
    public String annulerReservation(Model model, @RequestParam(name="reservationId") Integer reservationId){

        reservationService.deleteReservation(reservationId);

        return "confirmationAnnulation";
    }

}
