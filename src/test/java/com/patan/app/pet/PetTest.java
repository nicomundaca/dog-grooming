package com.patan.app.pet;

import com.patan.app.dto.requests.RequestPet;
import com.patan.app.models.*;
import com.patan.app.services.PetService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetTest {


    private PetService petService = new PetService(null);


    @Test
    public void showPetsStartWithTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setStartwith("t");
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(2, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
        requestPet.setStartwith("asdadada");
        List<Pet> petList2 = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(0, petList2.size());

    }

    @Test
    public void showPetsTypeTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setPetType(PetType.DOG);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(5, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
    }

    @Test
    public void showPetsSizeTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setSize(Size.MEDIUM);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(4, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
    }

    @Test
    public void showPetsBehaviorTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setBehavior(Behavior.AGGRESSIVE);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(6, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
    }

    @Test
    public void showPetsBreedTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setBreed(Breed.MONGREL);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(10, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
    }

    @Test
    public void showPetsCastratedTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setCastrated(true);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(8, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());
    }

    @Test
    public void showPetsGenderTest() {
        RequestPet requestPet = new RequestPet();
        requestPet.setGender(Gender.FEMALE);
        Client client = createClient();
        List<Pet> petList = petService.getFilteredPets(requestPet, client);
        Assert.assertEquals(6, petList.size());
        List<Pet> petList1 = petService.getFilteredPets(null, client);
        Assert.assertEquals(14, petList1.size());

    }


    private Client createClient() {

        Client client = new Client();

        Pet pet1 = new Pet("patan", Size.MEDIUM, Breed.BEAGLE, "blanco", Behavior.RESTLESS, true, Gender.MALE, PetType.DOG);
        Pet pet2 = new Pet("tito", Size.MEDIUM, Breed.BICHON_MALTES, "rubio", Behavior.QUIET, false, Gender.MALE, PetType.DOG);
        Pet pet3 = new Pet("ñata", Size.MEDIUM, Breed.BOXER, "marron  ", Behavior.AGGRESSIVE, true, Gender.FEMALE, PetType.DOG);
        Pet pet4 = new Pet("oso", Size.SMALL, Breed.SHIHTZU, "gris", Behavior.AGGRESSIVE, false, Gender.MALE, PetType.DOG);
        Pet pet5 = new Pet("jason", Size.BIG, Breed.MONGREL, "black", Behavior.RESTLESS, true, Gender.MALE, PetType.DOG);
        Pet pet6 = new Pet("tina", Size.SMALL, Breed.MONGREL, "gris", Behavior.RESTLESS, true, Gender.FEMALE, PetType.CAT);
        Pet pet7 = new Pet("micha", Size.SMALL, Breed.MONGREL, "colorada", Behavior.AGGRESSIVE, true, Gender.FEMALE, PetType.CAT);
        Pet pet8 = new Pet("xena", Size.SMALL, Breed.MONGREL, "gris", Behavior.QUIET, false, Gender.FEMALE, PetType.CAT);
        Pet pet9 = new Pet("shaka", Size.SMALL, Breed.MONGREL, "gris", Behavior.AGGRESSIVE, false, Gender.FEMALE, PetType.CAT);
        Pet pet10 = new Pet("bartolo", Size.SMALL, Breed.MONGREL, "colorado", Behavior.QUIET, true, Gender.MALE, PetType.CAT);
        Pet pet11 = new Pet("pegazus", Size.BIG, Breed.MONGREL, "negro", Behavior.AGGRESSIVE, true, Gender.MALE, PetType.HORSE);
        Pet pet12 = new Pet("furiosoD", Size.BIG, Breed.MONGREL, "blanco", Behavior.AGGRESSIVE, false, Gender.MALE, PetType.HORSE);
        Pet pet13 = new Pet("apestoso", Size.MEDIUM, Breed.MONGREL, "negro", Behavior.RESTLESS, true, Gender.MALE, PetType.HORSE);
        Pet pet14 = new Pet("zombie", Size.SMALL, Breed.MONGREL, "marron", Behavior.QUIET, false, Gender.FEMALE, PetType.HORSE);


        List<Pet> listPet = new ArrayList<>();
        listPet.add(pet2);
        listPet.add(pet5);
        listPet.add(pet4);
        listPet.add(pet1);
        listPet.add(pet3);
        listPet.add(pet10);
        listPet.add(pet6);
        listPet.add(pet14);
        listPet.add(pet13);
        listPet.add(pet12);
        listPet.add(pet8);
        listPet.add(pet7);
        listPet.add(pet9);
        listPet.add(pet11);


        client.setPets(listPet);
        return client;
    }
}
