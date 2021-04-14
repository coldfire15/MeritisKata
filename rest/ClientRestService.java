package com.ammouri.meritis.Bank.rest;

import com.ammouri.meritis.Bank.entities.Client;
import com.ammouri.meritis.Bank.exceptions.InvalidEntityToPersistException;
import com.ammouri.meritis.Bank.exceptions.NoSuchEntityException;
import com.ammouri.meritis.Bank.servicesImpl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientRestService {
    @Autowired
    ClientService clientService;
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody Client c){

        try {
            clientService.save(c);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (InvalidEntityToPersistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @GetMapping("/history/{id}")
    public ResponseEntity<?> getHistory(@PathVariable("id") Long clientid){
        try {
            Client client = clientService.getById(clientid);
            return new ResponseEntity<>(client.getHistory(),HttpStatus.OK);
        } catch (NoSuchEntityException e) {

            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
