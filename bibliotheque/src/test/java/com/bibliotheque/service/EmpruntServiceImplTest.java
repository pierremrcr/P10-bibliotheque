package com.bibliotheque.service;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.repository.EmpruntEntityRepository;
import com.bibliotheque.service.contract.EmpruntEntityService;
import com.bibliotheque.service.impl.EmpruntEntityServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpruntServiceImplTest {

    @InjectMocks
    private EmpruntEntityServiceImpl empruntEntityService;

    @Mock
    private EmpruntEntityRepository empruntEntityRepositoryMock;

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

    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


}
