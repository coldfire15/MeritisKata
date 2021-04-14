package com.ammouri.meritis.Bank.rest;

import com.ammouri.meritis.Bank.entities.Account;
import com.ammouri.meritis.Bank.entities.Client;
import com.ammouri.meritis.Bank.exceptions.InvalidEntityToPersistException;
import com.ammouri.meritis.Bank.servicesImpl.AccountService;
import com.ammouri.meritis.Bank.servicesImpl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestService {
    @Autowired
    AccountService accountService;
    @Autowired
    ClientService clientService;
    @PostMapping(value = "/createAccount/{id}")
    public ResponseEntity<?> createAccount(@RequestBody Account account, @PathVariable("id") Long userId){
        Client client = clientService.clients_map.get(userId);
        System.out.println(client);
        List<Account> clientAccounts= new ArrayList<>();
        if(userId == null){
            return new ResponseEntity<>("There is no user id in the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
       if(!clientService.clients_map.containsKey(userId)){
            return new ResponseEntity<>("There is no user with this id", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
           if(client.getClient_accounts()!=null){
               clientAccounts=client.getClient_accounts();
               if(clientAccounts.contains(account)){
                   return new ResponseEntity<>("This account already exists",HttpStatus.CONFLICT);
               }else{
                   try {
                       account.setAccount_owner(client);
                       accountService.save(account);
                       return new ResponseEntity<>("The account has been succefully created",HttpStatus.OK);
                   } catch (InvalidEntityToPersistException e) {
                       e.printStackTrace();
                       return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                   }

               }

           }else{
               try {
                   account.setAccount_owner(client);
                   accountService.save(account);
                   return new ResponseEntity<>("The account has been succefully created",HttpStatus.OK);
               } catch (InvalidEntityToPersistException e) {
                   e.printStackTrace();
                   return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
               }


           }
       }

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok().body(accountService.getAll());
    }

    @PostMapping("/saveMoney/{clientid}/{accountid}/{amount}")
    public ResponseEntity<?> saveMoney(@PathVariable("clientid") Long clientId,@PathVariable("accountid") Long accountId,
                                       @PathVariable("amount") Long amount){
        Client client = clientService.clients_map.get(clientId);
        Account account = accountService.accounts_map.get(accountId);
        if(clientId == null || accountId == null){
            return new ResponseEntity<>("There is a missing id in the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!clientService.clients_map.containsKey(clientId) || !accountService.accounts_map.containsKey(accountId)){
            return new ResponseEntity<>("There is no client or account with this id", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            accountService.saveMoney(client,account,amount);
            String history= client.getHistory()+"\n Operation: Save Money \n Account: "+accountId +"\nAmount: "+amount + "\n Balance: "+ account.getBalance()+"\n" +
                    "------------------------------------------------\n";
            client.setHistory(history);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }

    }
    //the code does not manage the overdraft
    @PostMapping("/withdrawal/{clientid}/{accountid}/{amount}")
    public ResponseEntity<?> withdrawal (@PathVariable("clientid") Long clientId,@PathVariable("accountid") Long accountId,
                                       @PathVariable("amount") Long amount){
        Client client = clientService.clients_map.get(clientId);
        Account account = accountService.accounts_map.get(accountId);
        if(clientId == null || accountId == null){
            return new ResponseEntity<>("There is a missing id in the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!clientService.clients_map.containsKey(clientId) || !accountService.accounts_map.containsKey(accountId)){
            return new ResponseEntity<>("There is no client or account with this id", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            accountService.withdrawal(client,account,amount);
            String history= client.getHistory()+"\n Operation: Withdrawal \n Account: "+accountId +"\nAmount: "+amount + "\n Balance: "+ account.getBalance()+"\n " +
                    "------------------------------------------------\n";
            client.setHistory(history);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }

    }
}
