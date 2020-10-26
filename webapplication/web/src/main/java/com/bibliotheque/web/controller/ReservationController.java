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

import java.util.ArrayList;
import java.util.List;

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
                                 @RequestParam(name="compteId") Integer compteId){

        MembreType membre = membreService.membreById(compteId);
        ReservationType reservation = new ReservationType();
        LivreType livre = livreService.livreById(livreId);

        reservation.setStatut("en cours");
        reservation.setLivreid(livreId);
        reservation.setMembreid(compteId);
        reservationService.addReservation(reservation);

        return "confirmationReservation";

    }
}
