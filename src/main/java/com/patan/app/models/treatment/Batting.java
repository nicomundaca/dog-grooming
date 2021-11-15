package com.patan.app.models.treatment;

import com.patan.app.models.petsize.PetSize;

import java.math.BigDecimal;

public class Batting extends HairCut {

    @Override
    public Integer getBasePrice() {
        return 700;
    }

    @Override
    public Integer price(PetSize petSize) {
        return petSize.priceForBatting(this);
    }
}
