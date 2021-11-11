package com.patan.app.models.treatment;

import com.patan.app.models.petsize.PetSize;

import java.math.BigDecimal;

public class SanitaryCut extends HairCut {
    @Override
    public Integer getBasePrice() {
        return 600;
    }

    @Override
    public Integer price(PetSize petSize) {
        return petSize.priceForSanitaryCut(this);
    }
}
