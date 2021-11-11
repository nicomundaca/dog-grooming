package com.patan.app.client;

import com.patan.app.models.ClientEntity;
import com.patan.app.models.User;
import com.patan.app.services.ClientService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientEntityTest {

    private ClientService clientService = new ClientService(null);

    @Test
    public void showClientsStartWithTest() {
        User user = createUser();
        List<ClientEntity> clientEntityList = clientService.getFilteredClients("m", user);
        Assert.assertEquals(3, clientEntityList.size());
        List<ClientEntity> filteredClientEntities = clientService.getFilteredClients(null, user);
        Assert.assertEquals(17, filteredClientEntities.size());
        List<ClientEntity> filteredClients1 = clientService.getFilteredClients("asdasdasd", user);
        Assert.assertEquals(0, filteredClients1.size());


    }

    public User createUser() {

        User user = new User();
        List<ClientEntity> clientEntityList = new ArrayList<>();

        ClientEntity clientEntity1 = new ClientEntity("Armand", "Duplantis", "44 y 12", "221-4776235", "475-9346");
        ClientEntity clientEntity2 = new ClientEntity("Elaine", "Thompson", "522 y centenario", "221-4345345", "480-7346");
        ClientEntity clientEntity3 = new ClientEntity("Ruud", "Gullit", "448 y 19", "221-6545335", "475-4234");
        ClientEntity clientEntity4 = new ClientEntity("Michael", "Phelps", "472 y 21 bis", "221-4643535", "480-4467");
        ClientEntity clientEntity5 = new ClientEntity("Katie", "Ledecky", "442 y belgrano", "221-5634532", "475-3453");
        ClientEntity clientEntity6 = new ClientEntity("Serena", "Williams", "489 y 27", "221-4535355", "475-9235");
        ClientEntity clientEntity7 = new ClientEntity("Mo", "Farah", "54 y 13", "221-5654534", "475-0544");
        ClientEntity clientEntity8 = new ClientEntity("Simone", "Biles", "34 y 138", "221-32643353", "475-9356");
        ClientEntity clientEntity9 = new ClientEntity("Luis", "Scola", "508 y 17", "221-6754345", "475-3468");
        ClientEntity clientEntity10 = new ClientEntity("Allyson", "Felix", "472 y 16", "221-7345345", "474-6754");
        ClientEntity clientEntity11 = new ClientEntity("Lionel", "Messi", "482 y centenario", "221-3453453", "475-7654");
        ClientEntity clientEntity12 = new ClientEntity("Cristiano", "Ronaldo", "452 y 26", "221-8235873", "475-3453");
        ClientEntity clientEntity13 = new ClientEntity("Erling", "Halland", "462 y 17", "221-83453678", "475-9875");
        ClientEntity clientEntity14 = new ClientEntity("Eden", "Hazard", "492 y 22", "221-345346678", "472-7845");
        ClientEntity clientEntity15 = new ClientEntity("Robert", "Lewandowski", "495 y 29", "221-8345675", "475-7345");
        ClientEntity clientEntity16 = new ClientEntity("Kylian", "Mbappe", "502 y 23", "221-9345783", "477-6544");
        ClientEntity clientEntity17 = new ClientEntity("Memphis", "Depay", "506 y 28", "221-4634563", "476-4534");

        clientEntityList.add(clientEntity15);
        clientEntityList.add(clientEntity11);
        clientEntityList.add(clientEntity6);
        clientEntityList.add(clientEntity1);
        clientEntityList.add(clientEntity7);
        clientEntityList.add(clientEntity8);
        clientEntityList.add(clientEntity2);
        clientEntityList.add(clientEntity9);
        clientEntityList.add(clientEntity17);
        clientEntityList.add(clientEntity5);
        clientEntityList.add(clientEntity13);
        clientEntityList.add(clientEntity10);
        clientEntityList.add(clientEntity16);
        clientEntityList.add(clientEntity12);
        clientEntityList.add(clientEntity14);
        clientEntityList.add(clientEntity3);
        clientEntityList.add(clientEntity4);

        user.setClientEntities(clientEntityList);
        return user;
    }
}

