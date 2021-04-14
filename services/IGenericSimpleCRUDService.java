package com.ammouri.meritis.Bank.services;

import com.ammouri.meritis.Bank.exceptions.InvalidEntityToPersistException;
import com.ammouri.meritis.Bank.exceptions.NoSuchEntityException;

import java.util.List;

public interface IGenericSimpleCRUDService <Entity,ID> {
    public Entity save(Entity entity) throws InvalidEntityToPersistException;

    public Entity update(Entity entity) throws NoSuchEntityException, InvalidEntityToPersistException;

    public void delete(Entity entity) throws NoSuchEntityException;

    public void deleteById(ID id) throws NoSuchEntityException;

    public Entity getById(ID id) throws NoSuchEntityException;

    public List<Entity> getAll();
}
