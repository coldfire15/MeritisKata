package com.ammouri.meritis.Bank.servicesImpl;

import com.ammouri.meritis.Bank.entities.Account;
import com.ammouri.meritis.Bank.entities.Client;
import com.ammouri.meritis.Bank.exceptions.InvalidEntityToPersistException;
import com.ammouri.meritis.Bank.exceptions.NoSuchEntityException;
import com.ammouri.meritis.Bank.services.IAccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService implements IAccountService {
    //Simulate the persistance
    public static Map<Long,Account> accounts_map = new HashMap<>();
    @Override
    public Account save(Account account) throws InvalidEntityToPersistException {
        Long key = account.getAccount_id();
        if(key == null){
            throw new InvalidEntityToPersistException("The account must have an ID");
        }else{
            if(account.getAccount_owner()==null){
                throw new InvalidEntityToPersistException("The account must have a valid owner");
            }
            accounts_map.put(key,account);

        }
        return account;

    }

    @Override
    public Account update(Account account) throws NoSuchEntityException, InvalidEntityToPersistException {
        Long key = account.getAccount_id();
        Account old_account = accounts_map.get(key);
        if(old_account != null) {
            old_account.setAccount_owner(account.getAccount_owner());
            old_account.setAmount(account.getAmount());
            old_account.setBalance(account.getBalance());
            old_account.setDate(account.getDate());
            accounts_map.put(key, old_account);
            return old_account;
        }else{

            throw new NoSuchEntityException(
                    "The account with the id"+ key + "doesn't exist");
        }
    }

    @Override
    public void delete(Account account) throws NoSuchEntityException {
        Long key = account.getAccount_id();
        if(accounts_map.containsKey(key)){
            accounts_map.remove(key);
        }else{
            throw new NoSuchEntityException("The account with the id "+ key +" doesn't exist");
        }
    }

    @Override
    public void deleteById(Long key) throws NoSuchEntityException {
        if(accounts_map.containsKey(key)){
            accounts_map.remove(key);
        }else{
            throw new NoSuchEntityException("The account with the id "+ key +" doesn't exist");
        }
    }

    @Override
    public Account getById(Long key) throws NoSuchEntityException {
        if(accounts_map.containsKey(key)){
            return accounts_map.get(key);
        }else{
            throw new NoSuchEntityException("The user doesn't exist");
        }

    }

    @Override
    public List<Account> getAll() {
        List<Account> allAccounts = new ArrayList<>();
        if(!accounts_map.isEmpty()){
            for(Map.Entry<Long,Account> entry : accounts_map.entrySet()){
                allAccounts.add(entry.getValue());
            }
            return allAccounts;
        }else{
            return null;
        }
    }
    /**
     * @param client
     * @param account_to_modify to chosse wich account the client wants to modify in case
     *                    he has multiple accounts
     * @param amount
     * @return new balance
     */
    public Account saveMoney(Client client, Account account_to_modify, Long amount){
        List<Account> clientAccounts = client.getClient_accounts();
        Long new_balance = account_to_modify.getBalance() + amount;
        account_to_modify.setBalance(new_balance);
        try {
            save(account_to_modify);
            return account_to_modify;
        } catch (InvalidEntityToPersistException e) {
            e.printStackTrace();
        }


        return account_to_modify;
    }

    public Account withdrawal(Client client, Account account_to_modify, Long amount){
        List<Account> clientAccounts = client.getClient_accounts();
        Long new_balance = account_to_modify.getBalance() - amount;
        account_to_modify.setBalance(new_balance);
        try {
            save(account_to_modify);
            return account_to_modify;
        } catch (InvalidEntityToPersistException e) {
            e.printStackTrace();
        }


        return account_to_modify;
    }

}
