package fr.micropole.service;

import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Category;

public interface ServiceCategory {

    public Category create( Category category ) throws ServiceException;

    public Category findByName( String name ) throws ServiceException, DAOException;

    public List<Category> readAll() throws ServiceException, DAOException;

    public void remove( Integer id ) throws ServiceException, DAOException;

    public Category findById( Integer id ) throws ServiceException, DAOException;

}
