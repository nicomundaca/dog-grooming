package com.patan.app.models;

import com.patan.app.models.treatment.HairCut;

public class MediumPet extends PetSize{

    @Override
    public Integer priceForBatting(HairCut hairCut) {
        return 800;
    }


}
