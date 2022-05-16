package com.patan.app.appointment;

import com.patan.app.domains.Client;
import com.patan.app.domains.Pet;
import com.patan.app.domains.Appointment;
import com.patan.app.models.petsize.BigPet;
import com.patan.app.models.petsize.MediumPet;
import com.patan.app.models.petsize.SmallPet;
import com.patan.app.models.treatment.Batting;
import com.patan.app.models.treatment.HairCutAndBath;
import com.patan.app.models.treatment.SanitaryCut;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AppointmentTest {
    private Appointment appointment = new Appointment(new Client(), new Pet(), null, new Batting(), null, null);

    @Test
    public void getPriceTest() {
        appointment.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(700), appointment.getPrice());
        appointment.setHairCut(new Batting());
        appointment.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(800), appointment.getPrice());
        appointment.setHairCut(new Batting());
        appointment.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(900), appointment.getPrice());
        appointment.setHairCut(new SanitaryCut());
        appointment.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(600), appointment.getPrice());
        appointment.setHairCut(new SanitaryCut());
        appointment.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(700), appointment.getPrice());
        appointment.setHairCut(new SanitaryCut());
        appointment.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(800), appointment.getPrice());
        appointment.setHairCut(new HairCutAndBath());
        appointment.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(800), appointment.getPrice());
        appointment.setHairCut(new HairCutAndBath());
        appointment.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(900), appointment.getPrice());
        appointment.setHairCut(new HairCutAndBath());
        appointment.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(1000), appointment.getPrice());
    }


}
