package fr.micropole.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.micropole.dao.DAOcsv;
import fr.micropole.exception.DAOException;
import fr.micropole.helper.ImportationHelper;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.service.ServiceCsv;

@Service
public class ServiceImplCSV implements ServiceCsv {

    private final static Logger LOGGER = Logger.getLogger( ServiceImplCSV.class );

    @Autowired
    DAOcsv                      dAOcsv;

    @Override
    @Transactional
    public ImportCSVTransaction create( ImportCSVTransaction importCSVTransaction ) throws DAOException {

        return dAOcsv.create( importCSVTransaction );
    }

    @Override
    public List<ImportCSVTransaction> readAll() throws DAOException {

        return dAOcsv.readAll();
    }

    @Override
    @Transactional
    public void remove( ImportCSVTransaction importCSVTransaction ) throws DAOException {
        dAOcsv.remove( importCSVTransaction );

    }

    @Override
    public ImportCSVTransaction findById( Integer id ) throws DAOException {

        return dAOcsv.findById( id );
    }

    public List<ImportCSVTransaction> readTransactionsFromImportation( String path ) throws DAOException, IOException,
            ParseException {

        List<ImportCSVTransaction> importCSVTransactions = ImportationHelper.importationCSV( path );

        return importCSVTransactions;
    }

    @Override
    public List<ImportCSVTransaction> readTransactionBetweenDate( String dateDebut, String dateFin )
            throws DAOException, ParseException {

        return dAOcsv.readTransactionBetweenDate( dateDebut, dateFin );
    }

}
