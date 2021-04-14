package com.ammouri.meritis.Bank.servicesImpl;

import com.ammouri.meritis.Bank.entities.Account;
import com.ammouri.meritis.Bank.entities.Client;
import com.ammouri.meritis.Bank.exceptions.InvalidEntityToPersistException;
import com.ammouri.meritis.Bank.exceptions.NoSuchEntityException;
import com.ammouri.meritis.Bank.services.IClientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService implements IClientService {
    //Simulate the persistance
    public static Map<Long,Client> clients_map = new HashMap<>();


    @Override
    public Client save(Client client) throws InvalidEntityToPersistException {
        if(client.getClient_id() == null){
            throw new InvalidEntityToPersistException("The client must have an ID");
        }else{

            clients_map.put(client.getClient_id(),client);
            return client;
        }

    }

    @Override
    public Client update(Client client) throws NoSuchEntityException, InvalidEntityToPersistException {
        Long key = client.getClient_id();
        Client old_client = clients_map.get(key);
        if(old_client != null){
            old_client.setClient_accounts(client.getClient_accounts());
            old_client.setBirthday(client.getBirthday());
            old_client.setLast_name(client.getLast_name());
            old_client.setName(client.getName());
            old_client.setHistory(client.getHistory());
            clients_map.put(key,old_client);
            return old_client;
        }else{
            throw new NoSuchEntityException(
                    "The client with the id"+ client.getClient_id() + "doesn't exist");
        }

    }

    @Override
    public void delete(Client client) throws NoSuchEntityException {
        Long key = client.getClient_id();
        if(clients_map.containsKey(key)){
            clients_map.remove(key);
        }else{
            throw new NoSuchEntityException("The client with the id "+ key +" doesn't exist");
        }
    }

    @Override
    public void deleteById(Long key) throws NoSuchEntityException {
        if(clients_map.containsKey(key)){
            clients_map.remove(key);
        }else{
            throw new NoSuchEntityException("The client with the id "+ key +" doesn't exist");
        }
    }

    @Override
    public Client getById(Long key) throws NoSuchEntityException {
        if(clients_map.containsKey(key)){
            return clients_map.get(key);
        }else{
            throw new NoSuchEntityException("The user doesn't exist");
        }

    }

    @Override
    public List<Client> getAll() {
        return null;
    }


}
