package fr.micropole.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.micropole.dao.DAOCategory;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Category;
import fr.micropole.service.ServiceCategory;

@Service
public class ServiceImplCategory implements ServiceCategory {

    private final static Logger LOGGER = Logger.getLogger( ServiceImplCategory.class );

    @Autowired
    DAOCategory                 dAOCategory;

    @Override
    @Transactional
    public Category create( Category category ) throws ServiceException {

        try {
            category = dAOCategory.create( category );
        } catch ( DAOException e ) {

            LOGGER.error( "La création foire au niveau du service de création de Category", e );
        }

        return category;
    }

    @Override
    public Category findByName( String name ) throws ServiceException, DAOException {

        return dAOCategory.findByName( name );
    }

    @Override
    public List<Category> readAll() throws ServiceException, DAOException {

        return dAOCategory.readAll();
    }

    @Override
    @Transactional
    public void remove( Integer id ) throws ServiceException, DAOException {

        Category category = dAOCategory.findById( id );
        dAOCategory.remove( category );

    }

    @Override
    public Category findById( Integer id ) throws ServiceException, DAOException {
        return dAOCategory.findById( id );
    }

}
