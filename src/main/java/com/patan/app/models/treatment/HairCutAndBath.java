package com.patan.app.models.treatment;

import com.patan.app.models.petsize.PetSize;

import java.math.BigDecimal;

public class HairCutAndBath extends HairCut {
    @Override
    public Integer getBasePrice() {
        return 800;
    }

    @Override
    public Integer price(PetSize petSize) {
        return petSize.priceForHaircutAndBatting(this);
    }
}
