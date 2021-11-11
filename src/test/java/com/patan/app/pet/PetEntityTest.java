package com.patan.app.pet;

import com.patan.app.dto.requests.RequestPet;
import com.patan.app.models.*;
import com.patan.app.services.PetService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetEntityTest {


    private PetService petService = new PetService(null);


    @Test
    public void showPetsStartWithTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setStartwith("t");
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(2, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
        requestPet.setStartwith("asdadada");
        List<PetEntity> petEntityList2 = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(0, petEntityList2.size());

    }

    @Test
    public void showPetsTypeTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setPetType(PetType.DOG);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(5, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
    }

    @Test
    public void showPetsSizeTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setSize(Size.MEDIUM);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(4, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
    }

    @Test
    public void showPetsBehaviorTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setBehavior(Behavior.AGGRESSIVE);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(6, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
    }

    @Test
    public void showPetsBreedTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setBreed(Breed.MONGREL);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(10, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
    }

    @Test
    public void showPetsCastratedTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setCastrated(true);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(8, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());
    }

    @Test
    public void showPetsGenderTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setGender(Gender.FEMALE);
        ClientEntity clientEntity = createClient();
        List<PetEntity> petEntityList = petService.getFilteredPets(requestPet, clientEntity);
        Assert.assertEquals(6, petEntityList.size());
        List<PetEntity> petEntityList1 = petService.getFilteredPets(null, clientEntity);
        Assert.assertEquals(14, petEntityList1.size());

    }


    private ClientEntity createClient() {

        ClientEntity clientEntity = new ClientEntity();

        PetEntity petEntity1 = new PetEntity("patan", Size.MEDIUM, Breed.BEAGLE, "blanco", Behavior.RESTLESS, true, Gender.MALE, PetType.DOG);
        PetEntity petEntity2 = new PetEntity("tito", Size.MEDIUM, Breed.BICHON_MALTES, "rubio", Behavior.QUIET, false, Gender.MALE, PetType.DOG);
        PetEntity petEntity3 = new PetEntity("ñata", Size.MEDIUM, Breed.BOXER, "marron  ", Behavior.AGGRESSIVE, true, Gender.FEMALE, PetType.DOG);
        PetEntity petEntity4 = new PetEntity("oso", Size.SMALL, Breed.SHIHTZU, "gris", Behavior.AGGRESSIVE, false, Gender.MALE, PetType.DOG);
        PetEntity petEntity5 = new PetEntity("jason", Size.BIG, Breed.MONGREL, "black", Behavior.RESTLESS, true, Gender.MALE, PetType.DOG);
        PetEntity petEntity6 = new PetEntity("tina", Size.SMALL, Breed.MONGREL, "gris", Behavior.RESTLESS, true, Gender.FEMALE, PetType.CAT);
        PetEntity petEntity7 = new PetEntity("micha", Size.SMALL, Breed.MONGREL, "colorada", Behavior.AGGRESSIVE, true, Gender.FEMALE, PetType.CAT);
        PetEntity petEntity8 = new PetEntity("xena", Size.SMALL, Breed.MONGREL, "gris", Behavior.QUIET, false, Gender.FEMALE, PetType.CAT);
        PetEntity petEntity9 = new PetEntity("shaka", Size.SMALL, Breed.MONGREL, "gris", Behavior.AGGRESSIVE, false, Gender.FEMALE, PetType.CAT);
        PetEntity petEntity10 = new PetEntity("bartolo", Size.SMALL, Breed.MONGREL, "colorado", Behavior.QUIET, true, Gender.MALE, PetType.CAT);
        PetEntity petEntity11 = new PetEntity("pegazus", Size.BIG, Breed.MONGREL, "negro", Behavior.AGGRESSIVE, true, Gender.MALE, PetType.HORSE);
        PetEntity petEntity12 = new PetEntity("furiosoD", Size.BIG, Breed.MONGREL, "blanco", Behavior.AGGRESSIVE, false, Gender.MALE, PetType.HORSE);
        PetEntity petEntity13 = new PetEntity("apestoso", Size.MEDIUM, Breed.MONGREL, "negro", Behavior.RESTLESS, true, Gender.MALE, PetType.HORSE);
        PetEntity petEntity14 = new PetEntity("zombie", Size.SMALL, Breed.MONGREL, "marron", Behavior.QUIET, false, Gender.FEMALE, PetType.HORSE);


        List<PetEntity> listPetEntity = new ArrayList<>();
        listPetEntity.add(petEntity2);
        listPetEntity.add(petEntity5);
        listPetEntity.add(petEntity4);
        listPetEntity.add(petEntity1);
        listPetEntity.add(petEntity3);
        listPetEntity.add(petEntity10);
        listPetEntity.add(petEntity6);
        listPetEntity.add(petEntity14);
        listPetEntity.add(petEntity13);
        listPetEntity.add(petEntity12);
        listPetEntity.add(petEntity8);
        listPetEntity.add(petEntity7);
        listPetEntity.add(petEntity9);
        listPetEntity.add(petEntity11);


        clientEntity.setPetEntities(listPetEntity);
        return clientEntity;
    }
}
