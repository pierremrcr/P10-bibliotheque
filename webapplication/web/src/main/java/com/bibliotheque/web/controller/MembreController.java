package com.bibliotheque.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bibliotheque.service.EmpruntService;
import com.bibliotheque.service.ExemplaireService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.MembreService;
import com.bibliotheque.service.ReservationService;

import livres.wsdl.EmpruntType;
import livres.wsdl.LivreType;
import livres.wsdl.MembreType;
import livres.wsdl.ReservationType;

@Controller
public class MembreController {

    @Autowired
    private MembreService service;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private ExemplaireService exemplaireService;
    
    @Autowired
    private ReservationService reservationService;


    @Autowired
    public MembreController(MembreService service, EmpruntService empruntService) {
        this.service = service;
        this.empruntService = empruntService;
    }

    @RequestMapping(value="/detail-membre", method= RequestMethod.GET)
    public String membreDetail(Model model, HttpSession session, MembreType membreType, @RequestParam(name="compteId") int id) throws ParseException {

        membreType = this.service.membreById(id);
        List<LivreType> listeLivres = this.livreService.getAllLivresEmpruntes(id);
        List<EmpruntType> listeEmprunts = membreType.getListeEmprunts();
        List<ReservationType> listeReservations = membreType.getListeReservation();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("dateFormat", dateFormat);
        Date dateToday = dateFormat.parse(dateFormat.format(new Date()));
        model.addAttribute("dateToday", dateToday);
        model.addAttribute("membreType", membreType);
        model.addAttribute("dateFormat", dateFormat);
        model.addAttribute("listeLivres", listeLivres);
        model.addAttribute("listeEmprunts", listeEmprunts);
        model.addAttribute("listeReservations", listeReservations);
        return "detailMembre";

    }


    @RequestMapping(value="/addmembre", method = RequestMethod.GET)
    public String addMembre(Model model){

        MembreType membreType = new MembreType();
        model.addAttribute("membreType", membreType);
        return "formulaireMembre";

    }



    @RequestMapping(value = "/addmembre", method = RequestMethod.POST)
    public String saveMembre(@Valid @ModelAttribute("membreType") MembreType membreType, BindingResult result, ModelMap model){

        if(result.hasErrors()){
            return "error";
        }else{
            this.service.addMembre(membreType);
            return "confirmationInscription";
        }
    }


    @RequestMapping(value="/updatemembre", method = RequestMethod.POST)
    public String confirmUpdate(@Valid MembreType membreType, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "error";
        } else{
            this.service.updateMembre(membreType);
            return "detailMembre";
        }
    }

    @RequestMapping(value="/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam(name="compteId") int id, HttpSession session){
        this.service.deleteMembreById(id);
        session.removeAttribute("user");
        return "home";
    }
}
