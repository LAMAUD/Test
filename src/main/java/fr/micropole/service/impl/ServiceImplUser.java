package fr.micropole.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.micropole.dao.DAOUser;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.User;
import fr.micropole.service.ServiceUser;

@Service
public class ServiceImplUser implements ServiceUser {

    private final static Logger LOGGER = Logger.getLogger( ServiceImplUser.class );

    @Autowired
    DAOUser                     dAOUser;

    @Transactional
    public User create( User user ) {
        try {
            user = dAOUser.create( user );
        } catch ( DAOException e ) {
            LOGGER.error( "La création de l'utilisateur ne se fait pas au niveau de la DAO", e );
        }

        return user;
    }

    @Override
    public List<User> readAll() throws ServiceException, DAOException {

        return dAOUser.readAll();
    }

    @Transactional
    @Override
    public void remove( Integer id ) throws ServiceException, DAOException {
        User user = dAOUser.findById( id );
        dAOUser.remove( user );

    }

}
