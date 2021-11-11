package com.patan.app.models.petsize;

import com.patan.app.models.treatment.HairCut;

public class BigPet extends PetSize {

    @Override
    public Integer priceForBatting(HairCut hairCut) {
        return hairCut.getBasePrice() + 200;
    }

    @Override
    public Integer priceForHaircutAndBatting(HairCut hairCut) {
        return hairCut.getBasePrice() + 200;
    }

    @Override
    public Integer priceForSanitaryCut(HairCut hairCut) {
        return hairCut.getBasePrice() + 200;
    }
}
