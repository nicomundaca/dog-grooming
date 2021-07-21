package com.patan.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patan.app.dao.ClientDAO;
import com.patan.app.models.Client;

@RestController
@RequestMapping("/client")
public class ClientRest {
	
	@Autowired
	private ClientDAO clientDAO;
	
	@PostMapping("/save")
	public void save(@RequestBody Client client) {
		System.out.println("guardando usuario");
		clientDAO.save(client);
	}
	
	@GetMapping("/list")
	public List<Client>listAll(){
		System.out.println("listando usuarios");
		return clientDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Integer id) {
		System.out.println("borrando usuario con id !!!!"+ id);
		clientDAO.deleteById(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Client client) {
		System.out.println("actualizando usuario");
		clientDAO.save(client);
	}
}