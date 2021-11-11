package com.patan.app.models.petsize;

import com.patan.app.models.treatment.HairCut;

import java.math.BigDecimal;

public abstract class PetSize {

    public abstract Integer priceForBatting(HairCut hairCut);

    public abstract Integer priceForHaircutAndBatting(HairCut hairCut);

    public abstract Integer priceForSanitaryCut(HairCut hairCut);
}
