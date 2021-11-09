package com.patan.app.client;

import com.patan.app.models.Client;
import com.patan.app.models.User;
import com.patan.app.services.ClientService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    private ClientService clientService = new ClientService(null);

    @Test
    public void showClientsStartWithTest() {
        User user = createUser();
        List<Client> clientList = clientService.getFilteredClients("m", user);
        Assert.assertEquals(3, clientList.size());
        List<Client> filteredClients = clientService.getFilteredClients(null, user);
        Assert.assertEquals(17, filteredClients.size());
        List<Client> filteredClients1 = clientService.getFilteredClients("asdasdasd", user);
        Assert.assertEquals(0, filteredClients1.size());


    }

    public User createUser() {

        User user = new User();
        List<Client> clientList = new ArrayList<>();

        Client client1 = new Client("Armand", "Duplantis", "44 y 12", "221-4776235", "475-9346");
        Client client2 = new Client("Elaine", "Thompson", "522 y centenario", "221-4345345", "480-7346");
        Client client3 = new Client("Ruud", "Gullit", "448 y 19", "221-6545335", "475-4234");
        Client client4 = new Client("Michael", "Phelps", "472 y 21 bis", "221-4643535", "480-4467");
        Client client5 = new Client("Katie", "Ledecky", "442 y belgrano", "221-5634532", "475-3453");
        Client client6 = new Client("Serena", "Williams", "489 y 27", "221-4535355", "475-9235");
        Client client7 = new Client("Mo", "Farah", "54 y 13", "221-5654534", "475-0544");
        Client client8 = new Client("Simone", "Biles", "34 y 138", "221-32643353", "475-9356");
        Client client9 = new Client("Luis", "Scola", "508 y 17", "221-6754345", "475-3468");
        Client client10 = new Client("Allyson", "Felix", "472 y 16", "221-7345345", "474-6754");
        Client client11 = new Client("Lionel", "Messi", "482 y centenario", "221-3453453", "475-7654");
        Client client12 = new Client("Cristiano", "Ronaldo", "452 y 26", "221-8235873", "475-3453");
        Client client13 = new Client("Erling", "Halland", "462 y 17", "221-83453678", "475-9875");
        Client client14 = new Client("Eden", "Hazard", "492 y 22", "221-345346678", "472-7845");
        Client client15 = new Client("Robert", "Lewandowski", "495 y 29", "221-8345675", "475-7345");
        Client client16 = new Client("Kylian", "Mbappe", "502 y 23", "221-9345783", "477-6544");
        Client client17 = new Client("Memphis", "Depay", "506 y 28", "221-4634563", "476-4534");

        clientList.add(client15);
        clientList.add(client11);
        clientList.add(client6);
        clientList.add(client1);
        clientList.add(client7);
        clientList.add(client8);
        clientList.add(client2);
        clientList.add(client9);
        clientList.add(client17);
        clientList.add(client5);
        clientList.add(client13);
        clientList.add(client10);
        clientList.add(client16);
        clientList.add(client12);
        clientList.add(client14);
        clientList.add(client3);
        clientList.add(client4);

        user.setClients(clientList);
        return user;
    }
}

