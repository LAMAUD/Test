package fr.micropole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.micropole.dao.DAOCategorisationLibelle;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.CategorisationLibelle;
import fr.micropole.service.ServiceCategorisationLibelle;

@Service
public class ServiceImplCategorisationLibelle implements ServiceCategorisationLibelle {

    @Autowired
    DAOCategorisationLibelle dAOCategorisationLibelle;

    @Override
    @Transactional
    public CategorisationLibelle create( CategorisationLibelle CategorisationLibelle ) throws ServiceException,
            DAOException {

        return dAOCategorisationLibelle.create( CategorisationLibelle );
    }

    @Override
    public List<CategorisationLibelle> readAll() throws ServiceException, DAOException {

        return dAOCategorisationLibelle.readAll();
    }

    @Override
    @Transactional
    public void remove( Integer id ) throws ServiceException, DAOException {

        CategorisationLibelle categorisationLibelle = dAOCategorisationLibelle.findById( id );
        dAOCategorisationLibelle.remove( categorisationLibelle );

    }

    @Override
    public CategorisationLibelle findById( Integer id ) throws ServiceException, DAOException {

        return dAOCategorisationLibelle.findById( id );
    }

}
