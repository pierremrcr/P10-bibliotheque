package com.bibliotheque.batch.batch;

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
                //Si un exemplaire est dispo, on envoie un message à l'utilisateur ayant fait la réservation en premier
                if (exemplaire.isDisponibilite()) {

                    if (!delaiExpired(reservation)) {
                        MembreType membre = reservation.getMembreEntity();
                        LivreType livre = reservation.getLivreEntity();
                        XMLGregorianCalendar dateDispo = reservation.getDateDispo();
                        Date dateDispoConverted = dateFormat.parse(dateDispo.toString());
                        String subject = "Info réservation : Mediathèque de La Rochelle";
                        String text = messagesMail.textMailLivreDisponible(dateDispoConverted, membre, livre);
                        sendingMail.sendMessage(subject, text, membre.getAdresseMail());
                        reservation.setStatut("terminé");
                        reservationService.updateReservation(reservation);

                    } else if (delaiExpired(reservation)) {
                        MembreType membre = reservation.getMembreEntity();
                        LivreType livre = reservation.getLivreEntity();
                        String subject = "Info réservation : Mediathèque de La Rochelle";
                        String text = messagesMail.textMailDelaiExpirer(membre, livre);
                        sendingMail.sendMessage(subject, text, membre.getAdresseMail());
                        reservation.setStatut("expiré");
                        reservationService.updateReservation(reservation);
                    }
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
            Date today = Calendar.getInstance().getTime();
            Date dateDispo = dateFormat.parse(reservationType.getDateDispo().toString());
            if (dateDispo.before(addDays(today, 2))) {
                return true;
            } else {
                return false;
            }
        }

        // date dispo : 11-11-2020  < date today : 12-11-2020 --> date expiré
        // date dispo : 13-11-2020  > date today : 12-11-2020 --> date non expiré


    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }




}



