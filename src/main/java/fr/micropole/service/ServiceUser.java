package fr.micropole.service;

import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.User;

public interface ServiceUser {

    public User create( User user ) throws ServiceException;

    public List<User> readAll() throws ServiceException, DAOException;

    public void remove( Integer id ) throws ServiceException, DAOException;

}
