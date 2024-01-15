package com.example.demo.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.ProprietaireRepository;
import com.example.demo.Service.ProprietaireService;
import com.example.demo.entities.LoginRequest;
import com.example.demo.entities.Proprietaire;

@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:8081/"})	
@RestController
@RequestMapping("/proprietaire")
public class ProprietaireController {
	
	@Autowired
	ProprietaireService proprietaireService;
	
	@Autowired
	private ProprietaireRepository proprietaireRepository;
	

	
	@PostMapping("/save")
	public <S extends Proprietaire> S save(@RequestBody S entity) {
		return proprietaireService.save(entity);
	}

	@GetMapping("/findAll")
	public List<Proprietaire> findAll(Sort sort) {
		return proprietaireService.findAll(sort);
	}
	
	@GetMapping("/findById/{id}")
	public Optional<Proprietaire> findById(@PathVariable Integer id) {
		return proprietaireService.findById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable Integer id) {
		proprietaireService.deleteById(id);
	}

	@GetMapping("/findByCin/{CIN}")
	public Proprietaire findByCin(@PathVariable String CIN) {
		return proprietaireService.findByCIN(CIN);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Proprietaire> update(@PathVariable int id, @RequestBody Proprietaire proprietaire){
		return proprietaireService.updateProprietaire(id, proprietaire);
	}
	
	/*@PostMapping("/login")
    public boolean login(@RequestBody Proprietaire credentials) {
        Proprietaire existingProprietaire = proprietaireRepository.findByUsername(credentials.getUsername());
        return existingProprietaire != null && existingProprietaire.getPassword().equals(credentials.getPassword());
    }*/
	 @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	        String username = loginRequest.getUsername();
	        String password = loginRequest.getPassword();

	        boolean isAuthenticated = proprietaireService.authenticate(username, password);

	        if (isAuthenticated) {
	            return new ResponseEntity<>("Request triggered successfully!", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
	        }
	    }
	
}
