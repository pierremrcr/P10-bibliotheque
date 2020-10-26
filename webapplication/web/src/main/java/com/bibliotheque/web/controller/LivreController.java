package com.bibliotheque.web.controller;

import com.bibliotheque.service.EmpruntService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.ReservationService;
import livres.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value="/livres", method = RequestMethod.GET)
    public String livres(final Model model){

        //On récupère la liste de tous les livres
        List<LivreType> livreTypeList = livreService.livreTypeList();

        //Méthode qui permet de récupérer la liste des exemplaires dispo pour chaque livre
        livreTypeList = livreService.exemplairesDispoParLivre(livreTypeList);

        model.addAttribute("listeLivres", livreTypeList);

        return "livres";

    }

    @RequestMapping(value="/livre", method = RequestMethod.GET)
    public String livreDetail(HttpSession session, Model model, @RequestParam(name="id") Integer id) throws ParseException {

        LivreType livre = livreService.livreById(id);

        MembreType user = (MembreType) session.getAttribute("user");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("dateFormat", dateFormat);

        //Méthode qui permet de récupérer la liste des exemplaires dispo en fonction d'un livre et de ses exemplaires
        List<ExemplaireType> exemplairesListe = livreService.nombreExemplaireDispo(livre.getListeExemplaires());

        model.addAttribute("exemplairesDispo",exemplairesListe);

        for (ExemplaireType exemplaireType : exemplairesListe){
            model.addAttribute("exemplaire", exemplaireType);
        }

        List<EmpruntType> listeEmpruntsByOuvrage = empruntService.getAllEmpruntsByLivreId(id);


        if(listeEmpruntsByOuvrage.size()>0){

            List<Long> joursRestantsEmprunts = empruntService.joursRestantsEmprunt(listeEmpruntsByOuvrage);
            model.addAttribute("joursRestantsEmprunts", joursRestantsEmprunts.get(0));

            listeEmpruntsByOuvrage = empruntService.trieEmpruntsParDateDeFin(listeEmpruntsByOuvrage, joursRestantsEmprunts);
            Date dateRetour = dateFormat.parse(listeEmpruntsByOuvrage.get(0).getDateFin().toString());
            model.addAttribute("dateRetour", dateFormat.format(dateRetour));

        }

        List<ReservationType> listeReservationsEnCours = reservationService.reservationTypeListEnCours(reservationService.getReservationsByLivre(id));

        Boolean dejaReservé = new Boolean(false);
        Boolean dejaEmprunté = new Boolean(false);

        for (ReservationType reservation : listeReservationsEnCours){
            if(reservation.getMembreid() == user.getId()){
                dejaReservé = true;
            }
        }

        for(EmpruntType emprunt : listeEmpruntsByOuvrage){
            if(emprunt.getMembreid() == user.getId()){
                dejaEmprunté = true;
            }
        }

        model.addAttribute("livre", livre);
        model.addAttribute("dejaReserve", dejaReservé);
        model.addAttribute("dejaEmprunte", dejaEmprunté);
        model.addAttribute("listeReservation", listeReservationsEnCours);

        return "livre";


    }


}
