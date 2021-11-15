package com.patan.app.models.petsize;

import com.patan.app.models.treatment.HairCut;

import java.math.BigDecimal;

public class SmallPet extends PetSize{

    @Override
    public Integer priceForBatting(HairCut hairCut) {
        return hairCut.getBasePrice();
    }

    @Override
    public Integer priceForHaircutAndBatting(HairCut hairCut) {
        return hairCut.getBasePrice();
    }

    @Override
    public Integer priceForSanitaryCut(HairCut hairCut) {
        return hairCut.getBasePrice();
    }
}
