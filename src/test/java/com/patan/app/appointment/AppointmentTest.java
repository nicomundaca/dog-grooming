package com.patan.app.shift;

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
    private Appointment shift = new Appointment(new Client(), new Pet(), null, new Batting(), null, null);

    @Test
    public void getPriceTest() {
        shift.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(700), shift.getPrice());
        shift.setHairCut(new Batting());
        shift.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(800),shift.getPrice());
        shift.setHairCut(new Batting());
        shift.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(900),shift.getPrice());
        shift.setHairCut(new SanitaryCut());
        shift.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(600),shift.getPrice());
        shift.setHairCut(new SanitaryCut());
        shift.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(700),shift.getPrice());
        shift.setHairCut(new SanitaryCut());
        shift.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(800),shift.getPrice());
        shift.setHairCut(new HairCutAndBath());
        shift.getPet().setPetSize(new SmallPet());
        Assert.assertEquals(BigDecimal.valueOf(800),shift.getPrice());
        shift.setHairCut(new HairCutAndBath());
        shift.getPet().setPetSize(new MediumPet());
        Assert.assertEquals(BigDecimal.valueOf(900),shift.getPrice());
        shift.setHairCut(new HairCutAndBath());
        shift.getPet().setPetSize(new BigPet());
        Assert.assertEquals(BigDecimal.valueOf(1000),shift.getPrice());
    }


}
