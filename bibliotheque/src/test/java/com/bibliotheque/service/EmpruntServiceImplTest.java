package com.bibliotheque.service;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.repository.EmpruntEntityRepository;
import com.bibliotheque.repository.ExemplaireEntityRepository;
import com.bibliotheque.service.impl.EmpruntEntityServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpruntServiceImplTest {

    @InjectMocks
    private EmpruntEntityServiceImpl empruntEntityService;

    @Mock
    private EmpruntEntityRepository empruntEntityRepositoryMock;

    @Mock
    private ExemplaireEntityRepository exemplaireEntityRepositoryMock;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEmpruntByIdTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        EmpruntEntity empruntEntityMock = new EmpruntEntity();

        empruntEntityMock.setId(1);
        Date debut = dateFormat.parse("2020-11-02");
        Date fin = dateFormat.parse("2020-11-28");

        empruntEntityMock.setDate_debut(debut);
        empruntEntityMock.setDate_fin(fin);
        empruntEntityMock.setExemplaireid(1);
        empruntEntityMock.setMembreid(1);


        when(empruntEntityRepositoryMock.findById(1)).thenReturn(empruntEntityMock);

        EmpruntEntity empruntEntity = this.empruntEntityService.getEmpruntById(1);
        Assert.assertEquals(1, empruntEntity.getId());
        Assert.assertEquals(dateFormat.parse("2020-11-02"), empruntEntity.getDate_debut());

    }

    @Test
    public void getAllEmpruntsTest(){

        List<EmpruntEntity> empruntEntityList =
                new ArrayList<>(Arrays.asList(new EmpruntEntity(),
                        new EmpruntEntity(), new EmpruntEntity()));

        when(empruntEntityRepositoryMock.findAll()).thenReturn(empruntEntityList);

        List<EmpruntEntity> listEmprunts = this.empruntEntityService.getAllEmprunts();
        Assert.assertEquals(3,listEmprunts.size());

    }

    @Test
    public void addEmpruntTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EmpruntEntity empruntEntity = new EmpruntEntity();
        Date debut = dateFormat.parse("2020-11-02");
        Date fin = dateFormat.parse("2020-11-28");
        empruntEntity.setDate_debut(debut);
        empruntEntity.setDate_fin(fin);
        empruntEntity.setExemplaireid(1);
        empruntEntity.setMembreid(1);
        empruntEntity.setTermine(false);
        empruntEntity.setProlongation(false);
        empruntEntity.setRelance(false);

        ExemplaireEntity exemplaireEntity = new ExemplaireEntity();

        when(empruntEntityRepositoryMock.save(any(EmpruntEntity.class))).thenReturn(empruntEntity);
        when(exemplaireEntityRepositoryMock.findById(1)).thenReturn(exemplaireEntity);
        EmpruntEntity empruntEntity1 = this.empruntEntityService.addEmprunt(empruntEntity);
        Assert.assertEquals(empruntEntity.getDate_debut(), empruntEntity1.getDate_debut());

    }

    @Test
    public void updateEmpruntTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EmpruntEntity empruntEntity = new EmpruntEntity();
        Date debut = dateFormat.parse("2020-11-02");
        Date fin = dateFormat.parse("2020-11-28");
        empruntEntity.setDate_debut(debut);
        empruntEntity.setDate_fin(fin);
        empruntEntity.setExemplaireid(1);
        empruntEntity.setMembreid(1);
        empruntEntity.setTermine(false);
        empruntEntity.setProlongation(false);
        empruntEntity.setRelance(false);

        ExemplaireEntity exemplaireEntity = new ExemplaireEntity();

        when(empruntEntityRepositoryMock.save(any(EmpruntEntity.class))).thenReturn(empruntEntity);
        when(exemplaireEntityRepositoryMock.findById(1)).thenReturn(exemplaireEntity);

        Assert.assertEquals(empruntEntity.getDate_debut(), dateFormat.parse("2020-11-02"));

        empruntEntity.setDate_debut(dateFormat.parse("2020-11-03"));

        boolean updateEmprunt = this.empruntEntityService.updateEmprunt(empruntEntity);

        Assert.assertTrue(updateEmprunt);
        Assert.assertEquals(empruntEntity.getDate_debut(), dateFormat.parse("2020-11-03"));

    }

    @Test
    public void deleteEmpruntByIdTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EmpruntEntity empruntEntity = new EmpruntEntity();
        Date debut = dateFormat.parse("2020-11-02");
        Date fin = dateFormat.parse("2020-11-28");
        empruntEntity.setDate_debut(debut);
        empruntEntity.setDate_fin(fin);
        empruntEntity.setExemplaireid(1);
        empruntEntity.setMembreid(1);
        empruntEntity.setTermine(false);
        empruntEntity.setProlongation(false);
        empruntEntity.setRelance(false);

        when(empruntEntityRepositoryMock.save(any(EmpruntEntity.class))).thenReturn(empruntEntity);

        Assert.assertEquals(empruntEntity.getDate_debut(), dateFormat.parse("2020-11-02"));

        boolean deleteEmprunt = this.empruntEntityService.deleteEmpruntById(empruntEntity.getId());

        Assert.assertTrue(deleteEmprunt);


    }

    @Test
    public void updateEmpruntTermineTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EmpruntEntity empruntEntity = new EmpruntEntity();
        Date debut = dateFormat.parse("2020-11-02");
        Date fin = dateFormat.parse("2020-11-28");
        empruntEntity.setDate_debut(debut);
        empruntEntity.setDate_fin(fin);
        empruntEntity.setExemplaireid(1);
        empruntEntity.setMembreid(1);
        empruntEntity.setTermine(false);
        empruntEntity.setProlongation(false);
        empruntEntity.setRelance(false);

        ExemplaireEntity exemplaireEntity = new ExemplaireEntity();

        when(empruntEntityRepositoryMock.save(any(EmpruntEntity.class))).thenReturn(empruntEntity);
        when(exemplaireEntityRepositoryMock.findById(1)).thenReturn(exemplaireEntity);

        Assert.assertEquals(false, exemplaireEntity.isDisponibilite());

        boolean updateEmpruntTermine = this.empruntEntityService.updateEmpruntTermine(empruntEntity);
        Assert.assertTrue(updateEmpruntTermine);
        Assert.assertEquals(true, exemplaireEntity.isDisponibilite());

    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


}
