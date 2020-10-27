package com.bibliotheque.batch.batch;

import com.bibliotheque.service.ReservationService;
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

import java.util.ArrayList;
import java.util.List;

@Component
public class BatchReservation implements Tasklet, StepExecutionListener {

    @Autowired
    private ReservationService reservationService;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        List<ReservationType> listeReservations = getListeReservationsFirstPosition();



        return null;
    }

    private List<ReservationType> getListeReservationsFirstPosition(){

        List<ReservationType> listeReservations = reservationService.getAllReservations();
        List<ReservationType> listeResaFirstPosition = new ArrayList<>();

        for (ReservationType reservation : listeReservations){
            if(reservation.getNumPositionResa()==1 && reservation.getStatut().equals("En cours")){
                listeResaFirstPosition.add(reservation);
            }
        }

        return listeResaFirstPosition;

    }


}
