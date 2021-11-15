package com.patan.app.models.treatment;

import com.patan.app.models.petsize.PetSize;

import java.math.BigDecimal;

public abstract class HairCut {

   public abstract Integer getBasePrice();

   public abstract Integer price(PetSize petSize);



}
