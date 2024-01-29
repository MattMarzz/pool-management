package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;

import java.sql.SQLException;

public interface StdProcedureDAO<T> {

    public T execute(Object... params) throws ItemNotFoundException;
}