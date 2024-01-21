package com.bibliotheque.batch;

import com.bibliotheque.batch.mail.MessagesMail;
import com.bibliotheque.batch.mail.SendingMail;
import com.bibliotheque.service.ReservationService;
import livres.wsdl.ExemplaireType;
import livres.wsdl.LivreType;
import livres.wsdl.MembreType;
import livres.wsdl.ReservationType;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class BatchReservation implements Tasklet, StepExecutionListener {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MessagesMail messagesMail;

    @Autowired
    private SendingMail sendingMail;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        //On récupère La liste des réservations ayant le num position 1
        List<ReservationType> listeReservations = getListeReservationsFirstPosition();

        for (ReservationType reservation : listeReservations) {
            //On vérifie si un exemplaire est disponible parmi les ouvrages ayant été réservé
            for (ExemplaireType exemplaire : reservation.getLivreEntity().getListeExemplaires()) {

                if (!exemplaire.isDisponibilite()) {
                    break;
                }

                //Si un exemplaire est disponible, on envoie un message à l'utilisateur ayant fait la réservation en premier
                if (!delaiExpired(reservation)) {
                    // le délai n'a pas expiré --> On envoit un mail à l'utilisateur pour l'avertir que le livre est disponible
                    MembreType membre = reservation.getMembreEntity();
                    LivreType livre = reservation.getLivreEntity();
                    XMLGregorianCalendar dateDispo = reservation.getDateDispo();
                    Date dateDispoConverted = dateFormat.parse(dateDispo.toString());
                    String subject = "Info réservation : Mediathèque de La Rochelle";
                    String text = messagesMail.textMailLivreDisponible(dateDispoConverted, membre, livre);
                    sendingMail.sendMessage(subject, text, membre.getAdresseMail());
                    reservationService.updateReservation(reservation);

                } else {
                    // Le délai a expiré --> On envoit un mail à l'utilisateur pour l'avertir que le livre n'est plus disponible
                    MembreType membre = reservation.getMembreEntity();
                    LivreType livre = reservation.getLivreEntity();
                    String subject = "Info réservation : Mediathèque de La Rochelle";
                    String text = messagesMail.textMailDelaiExpirer(membre, livre);
                    sendingMail.sendMessage(subject, text, membre.getAdresseMail());
                    reservationService.updateReservation(reservation);
                }
            }
        }

        return RepeatStatus.FINISHED;
    }

    private List<ReservationType> getListeReservationsFirstPosition (){

        List<ReservationType> listeReservations = reservationService.getAllReservations();
        List<ReservationType> listeResaFirstPosition = new ArrayList<>();

        for (ReservationType reservation : listeReservations) {
            if (reservation.getNumPositionResa() == 1 && reservation.getStatut().equals("réservé")) {
                listeResaFirstPosition.add(reservation);
            }
        }

        return listeResaFirstPosition;

    }

    private boolean delaiExpired(ReservationType reservationType) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int dayOfMonthToday = LocalDate.now().getDayOfMonth();
        Date dateDispo = dateFormat.parse(reservationType.getDateDispo().toString());
        int dayOfMontDateDispo = reservationType.getDateDispo().getDay();

        if (dayOfMonthToday == dayOfMontDateDispo) {
            return false;
        } else if ((dayOfMonthToday - dayOfMontDateDispo < 2) || (dayOfMonthToday - dayOfMontDateDispo == 2)) {
            return false;
        } else {
            return true;
        }
    }





    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }




}