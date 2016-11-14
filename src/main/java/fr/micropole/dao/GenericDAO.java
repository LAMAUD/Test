package fr.micropole.dao;

import java.util.List;

import fr.micropole.exception.DAOException;

public interface GenericDAO<T> {

    public T create( T t ) throws DAOException;

    public List<T> readAll() throws DAOException;

    public void remove( T t ) throws DAOException;

    public T findById( Integer id ) throws DAOException;

}
