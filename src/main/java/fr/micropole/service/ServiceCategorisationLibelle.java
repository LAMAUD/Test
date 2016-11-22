package fr.micropole.service;

import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.CategorisationLibelle;

public interface ServiceCategorisationLibelle {

    public CategorisationLibelle create( CategorisationLibelle CategorisationLibelle ) throws ServiceException,
            DAOException;

    public List<CategorisationLibelle> readAll() throws ServiceException, DAOException;

    public void remove( Integer id ) throws ServiceException, DAOException;

    public CategorisationLibelle findById( Integer id ) throws ServiceException, DAOException;

}
